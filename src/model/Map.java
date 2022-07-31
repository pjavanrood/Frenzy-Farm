package model;

import controller.GameProcessor;
import controller.Position;
import view.OutputProcessor;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    public int[][] grass;
    public ArrayList<AliveCreature>[][] creature;
    public ArrayList<Product>[][] productions;
    public ArrayList<Position> grasses;
    public ArrayList<Position> onMapProducts;

    public Map() {
        this.creature = new ArrayList[6][6];
        this.grass = new int[6][6];
        this.productions = new ArrayList[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++) {
                this.creature[i][j] = new ArrayList<>();
                this.grass[i][j] = 0;
                this.productions[i][j] = new ArrayList<>();
            }
        }
        setGrasses();
        setOnMapProducts();
    }

    public void setGrasses() {
        ArrayList<Position> Grasses = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++) {
                if (grass[i][j] != 0) {
                    Position grassposition = new Position(j, i);
                    Grasses.add(grassposition);
                }
            }
        this.grasses = Grasses;
    }

    public void setOnMapProducts() {
        ArrayList<Position> Products = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++) {
                if (productions[i][j].size() != 0) {
                    Products.add(new Position(j, i));
                }
            }
        this.onMapProducts = Products;
    }

    public void updateMap(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++) {
                this.creature[i][j].clear();
            }
        }

        for (AliveCreature allCreature : GameProcessor.allCreatures) {
            creature[allCreature.getPosition().getY()][allCreature.getPosition().getX()].add(allCreature);
        }

        updateErasedProductsAndAnimals();
        setGrasses();
        setOnMapProducts();
    }

    public boolean checkEmptyGrass(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++) {
                if(grass[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    public void emptyGrassAlert(){
        if(checkEmptyGrass())
            OutputProcessor.noGrassAlert();
    }

    public void eatGrass(Position position){
        grass[position.getY()][position.getX()]--;
    }

    public DomesticAnimals hungryAnimal(Position position){
        int life = 10;
        DomesticAnimals hungryAnimal = null;
        for (Object o : creature[position.getY()][position.getX()]) {
            if(o instanceof DomesticAnimals){
                int animalLife = ((DomesticAnimals) o).getLife();
                if(animalLife < life && ((DomesticAnimals) o).alive){
                    life = animalLife;
                    hungryAnimal = (DomesticAnimals) o;
                }
            }
        }
        return hungryAnimal;
    }

    public void updateErasedProductsAndAnimals(){ //Erase products which have been expired or stored from the map.
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++) {
                int productsNum = productions[i][j].size();
                int animalsNum = creature[i][j].size();

                for(int k = productsNum - 1; k > -1; k--){
                    Product product = productions[i][j].get(k);
                    product.updateProduct();
                    if(product.expired)
                        productions[i][j].remove(k);
                }

                for(int k = animalsNum - 1; k > -1; k--){
                    AliveCreature aliveCreature = creature[i][j].get(k);
                    if(aliveCreature instanceof WildAnimals && ((WildAnimals) aliveCreature).checkEscape()){
                        GameProcessor.allCreatures.remove(creature[i][j].get(k));
                        creature[i][j].remove(k);
                    }
                }
            }
        }
    }

    public static HashMap<String, Integer> productCounter(ArrayList<Product> products){
        HashMap<String, Integer> productsHashmap = new HashMap<>();
        for (Product product : products) {
            if(productsHashmap.containsKey(product.name))
                productsHashmap.put(product.name, productsHashmap.get(product.name) + 1);


            else
                productsHashmap.put(product.name, 1);

        }
        return productsHashmap;
    }


    public static HashMap<String, Integer> creatureCounter(ArrayList<AliveCreature> creatures){
        HashMap<String, Integer> creaturesHashmap = new HashMap<>();
        for (AliveCreature creature : creatures) {
            String name = creature.name;
            if(creature instanceof WildAnimals && ((WildAnimals) creature).isCaged)
                name = creature.name + "*";

            if(creaturesHashmap.containsKey(name))
                creaturesHashmap.put(name, creaturesHashmap.get(name) + 1);


            else
                creaturesHashmap.put(name, 1);

        }
        return creaturesHashmap;
    }

}
