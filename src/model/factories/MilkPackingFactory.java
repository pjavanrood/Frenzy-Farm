package model.factories;

import controller.GameProcessor;
import controller.Position;
import model.Factory;
import model.Product;
import model.products.Milk;
import model.products.PasteurizedMilk;

public class MilkPackingFactory extends Factory {
    public MilkPackingFactory() {
        super("milk packing factory", Milk.class, PasteurizedMilk.class, 400, 6);
    }

    public static int checkMilkExists(){
        for (Product product : GameProcessor.storage.getProducts()) {
            if(product.getName().equalsIgnoreCase("milk"))
                return GameProcessor.storage.getProducts().indexOf(product);
        }
        return -1;
    }

    public void deliver(){
        setWorking(false);
        Product product = new PasteurizedMilk();
        Position position = Position.randomPosition();
        GameProcessor.gameMap.productions[position.getY()][position.getX()].add(product);    }
}
