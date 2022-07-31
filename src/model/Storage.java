package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    final int CAPACITY = 30;
    ArrayList<Product> products;
    ArrayList<WildAnimals> wildAnimals;
    boolean isFull;
    int availableSpace;
    ArrayList<String> productsname;

    public Storage() {
        products = new ArrayList<>();
        wildAnimals = new ArrayList<>();
        isFull = false;
        availableSpace = CAPACITY;
        productsname = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<String> getProductsname() {
        return productsname;
    }

    public ArrayList<WildAnimals> getWildAnimals() {
        return wildAnimals;
    }

    public boolean checkAvailableSpace(Product product){
        if(isFull)
            return false;

        if(availableSpace < product.space)
            return false;

        return true;
    }

    public boolean checkAvailableSpace(){
        if(availableSpace < WildAnimals.getSTORAGE_SPACE())
            return false;
        return true;
    }

    public void addToStorage(Product product){
        products.add(product);
        availableSpace -= product.space;
        setFull();
        setProductsname();
    }

    public void addToStorage(WildAnimals wildAnimal){
        wildAnimals.add(wildAnimal);
        availableSpace -= WildAnimals.getSTORAGE_SPACE();
        setFull();
        setProductsname();
    }

    public void setFull(){
        if(availableSpace == 0)
            isFull = true;

        if(availableSpace > 0 && isFull)
            isFull = false;
    }

    public Product loadToTruck(String name){
        Product product = null;
        for (Product product1 : products) {
            if(product1.name.equalsIgnoreCase(name)){
                product = product1;
                break;
            }
        }

        return product;
    }

    public void loadToTruck(Product product){
        products.remove(product);
        availableSpace += product.space;
        setFull();
        setProductsname();
    }

    public WildAnimals loadWildToTruck(String name){
        WildAnimals wildAnimal = null;

        for (WildAnimals wildAnimal1 : wildAnimals) {
            if(wildAnimal1.name.equalsIgnoreCase(name)){
                wildAnimal = wildAnimal1;
                break;
            }
        }

        return wildAnimal;
    }

    public void loadWildToTruck(WildAnimals wildAnimal){
        wildAnimals.remove(wildAnimal);
        availableSpace += WildAnimals.getSTORAGE_SPACE();
        setFull();
    }

    public void checkWildAnimalEscape(){
        int len = wildAnimals.size();

        for(int i = len - 1; i > -1; i--){
            if(wildAnimals.get(i).checkEscape()) {
                wildAnimals.remove(i);
                availableSpace += WildAnimals.getSTORAGE_SPACE();
                setFull();
            }
        }
    }

    public void giveProductToFactory(int index){
        Product product = products.get(index);
        products.remove(index);
        availableSpace -= product.space;
        setFull();
        setProductsname();
    }

    public void setProductsname (){
        for (int i = 0; i < products.size(); i++) {
            if (!productsname.contains(products.get(i).getName())) {
                productsname.add(products.get(i).getName());
            }
        }
    }


    public static HashMap<String, Integer> productCounter(ArrayList<Product> products){
        HashMap<String, Integer> productsHashmap = new HashMap<>();
        for (Product product : products) {
            if(productsHashmap.containsKey(product.name))
                productsHashmap.put(product.name, productsHashmap.get(product.name) + product.space);


            else
                productsHashmap.put(product.name, product.space);

        }
        return productsHashmap;
    }


    public static HashMap<String, Integer> cagedAnimalCounter(ArrayList<WildAnimals> creatures){
        HashMap<String, Integer> creaturesHashmap = new HashMap<>();
        for (AliveCreature creature : creatures) {

            String name = creature.name + "*";

            if(creaturesHashmap.containsKey(name))
                creaturesHashmap.put(name, creaturesHashmap.get(name) + WildAnimals.STORAGE_SPACE);


            else
                creaturesHashmap.put(name, WildAnimals.STORAGE_SPACE);

        }
        return creaturesHashmap;
    }
}
