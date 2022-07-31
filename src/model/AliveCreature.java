package model;

import controller.GameProcessor;
import controller.Position;
import model.specialAnimals.*;


import java.util.ArrayList;
import java.util.Random;

public class AliveCreature {
    String name;
    private final Position position;
    private final int velocity;
    private int previousDirection;
    private final int price;
    boolean alive;

    public AliveCreature(String name, int velocity, int price) {
        this.name = name;
        Random random = new Random();
        this.position = new Position(random.nextInt(6), random.nextInt(6));
        this.velocity = velocity;
        this.price = price;
        this.alive = true;
        this.previousDirection = -1;
    }

    public int getPrice() {
        return price;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public int getPreviousDirection() {
        return previousDirection;
    }

    public void updatePosition(){
        //Domestic animals find nearest grass
        if(this instanceof DomesticAnimals){
            ((DomesticAnimals) this).updateDomesticPosition();
            return;
        }

        //Cats find nearest product
        if(this instanceof Cat){
            ((Cat) this).updateCatPosition();
            return;
        }

        this.moveRandom();

    }

    public void moveRandom(){
        Random random = new Random();
        //0 right - 1 up - 2 left - 3 down
        int move = random.nextInt(4);
        int x = getPosition().getX();
        int y = getPosition().getY();
        switch (move) {
            case 0 : x += velocity;
                break;
            case 1 : y -= velocity;
                break;
            case 2 : x -= velocity;
                break;
            case 3 : y += velocity;
                break;
        }
        while (x < 0 || x > 5 || y < 0 || y > 5){
            move = random.nextInt(4);
            x = getPosition().getX();
            y = getPosition().getY();
            switch (move) {
                case 0 : x += velocity;
                    break;
                case 1 : y -= velocity;
                    break;
                case 2 : x -= velocity;
                    break;
                case 3 : y += velocity;
                    break;
            }
        }
        previousDirection = move;
        this.position.setPos(x, y);
    }

    public static void removeDead(){
        int len = GameProcessor.allCreatures.size();

        for(int i = len - 1; i > -1; i--){
            if(!GameProcessor.allCreatures.get(i).isAlive())
                GameProcessor.allCreatures.remove(i);
        }
    }

    public static void updateAliveCreatures(){
        //update Positions
        for (AliveCreature allCreature : GameProcessor.allCreatures) {

            if(allCreature instanceof DomesticAnimals) {
                allCreature.updatePosition();
                ((DomesticAnimals) allCreature).updateLife();
                ((DomesticAnimals) allCreature).updateProduction();
            }

            else if(allCreature instanceof WildAnimals && !((WildAnimals) allCreature).isCaged){
                allCreature.updatePosition();
            }
        }

        //apply position changes
        GameProcessor.gameMap.updateMap();

        //update Domestic Animals Eating
        for (AliveCreature allCreature : GameProcessor.allCreatures) {
            if(allCreature instanceof DomesticAnimals)
                ((DomesticAnimals) allCreature).checkEating();
        }

        //update Wild Animals Eating
        for (AliveCreature allCreature : GameProcessor.allCreatures) {
            if(allCreature instanceof WildAnimals && !((WildAnimals) allCreature).isCaged)
                ((WildAnimals) allCreature).checkEating();
        }

        for (AliveCreature allCreature : GameProcessor.allCreatures) {
            if(allCreature instanceof Hound)
                ((Hound) allCreature).killWildAnimal();

            else if(allCreature instanceof Cat)
                ((Cat) allCreature).pickupProduct();
        }


        //update Dead Creatures
        removeDead();
    }

    public boolean checkOverlap(){
        if(GameProcessor.gameMap.creature[getPosition().getY()][getPosition().getX()].size() > 1)
            return true;
        return false;
    }

    public static double distance(Position position1, Position position2){
        double distance = Math.sqrt(Math.pow((position1.getX() - position2.getX()), 2) + Math.pow((position1.getY() - position2.getY()), 2));
        return distance;
    }

    public static int findMin(ArrayList<Double> list) {
        double min = 0;
        int ans = -1;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                min = list.get(i);
            } else if (list.get(i) <= min) {
                min = list.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == min) {
                ans = i;
            }
        }
        return ans;
    }

    public static Position findClosestGrass(Position animalposition) {
        Position closestgrass;
        ArrayList<Double> distances = new ArrayList<>();
        for (int i = 0; i < GameProcessor.gameMap.grasses.size(); i++) {
            distances.add(distance(GameProcessor.gameMap.grasses.get(i), animalposition));
        }

        closestgrass = GameProcessor.gameMap.grasses.get(findMin(distances));
        return closestgrass;
    }

    public static Position findClosestProduct(Position animalposition) {
        Position closestProduct;
        ArrayList<Double> distances = new ArrayList<>();
        for (int i = 0; i < GameProcessor.gameMap.onMapProducts.size(); i++) {
            distances.add(distance(GameProcessor.gameMap.onMapProducts.get(i), animalposition));
        }
        closestProduct = GameProcessor.gameMap.onMapProducts.get(findMin(distances));
        return closestProduct;
    }

    public void goToPosition(Position destination){
        int x = getPosition().getX();
        int y = getPosition().getY();
        if (destination.getX() > getPosition().getX()) {
            x++;
            this.position.setPos(x, y);
            return;
        } else if (destination.getX() < getPosition().getX()) {
            x--;
            this.position.setPos(x, y);
            return;
        }
        if (destination.getY() > getPosition().getY()) {
            y++;
            this.position.setPos(x, y);
            return;
        } else if (destination.getY() < getPosition().getY()) {
            y--;
            this.position.setPos(x, y);
        }

    }

}
