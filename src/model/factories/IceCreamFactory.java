package model.factories;

import controller.GameProcessor;
import controller.Position;
import model.Factory;
import model.Product;
import model.products.IceCream;
import model.products.PasteurizedMilk;

public class IceCreamFactory extends Factory {
    public IceCreamFactory() {
        super("ice cream factory", PasteurizedMilk.class, IceCream.class, 550, 7);
    }

    public static int checkPackedMilkExists(){
        for (Product product : GameProcessor.storage.getProducts()) {
            if(product.getName().equalsIgnoreCase("packed milk"))
                return GameProcessor.storage.getProducts().indexOf(product);
        }
        return -1;
    }

    public void deliver(){
        setWorking(false);
        Product product = new IceCream();
        Position position = Position.randomPosition();
        GameProcessor.gameMap.productions[position.getY()][position.getX()].add(product);    }
}
