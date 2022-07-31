package model.specialAnimals;

import controller.GameProcessor;
import model.AliveCreature;
import model.Product;

import java.util.ArrayList;

public class Cat extends AliveCreature {
    public Cat() {
        super("cat", 1, 150);
    }

    public void pickupProduct(){
        ArrayList<Product> positionProducts = GameProcessor.gameMap.productions[getPosition().getY()][getPosition().getX()];
        if(positionProducts.size() == 0)
            return;

        positionProducts.get(0).pickUp();
        positionProducts.remove(0);
    }

    public void updateCatPosition(){
        if(GameProcessor.gameMap.onMapProducts.size() == 0){
            this.moveRandom();
            return;
        }
        goToPosition(findClosestProduct(getPosition()));}

}
