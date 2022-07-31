package model.factories;

import controller.GameProcessor;
import controller.Position;
import model.Factory;
import model.Product;
import model.products.Cloth;
import model.products.Shirt;

public class TailorShop extends Factory {
    public TailorShop() {
        super("tailor shop", Cloth.class, Shirt.class, 400, 6);
    }

    public static int checkClothExists(){
        for (Product product : GameProcessor.storage.getProducts()) {
            if(product.getName().equalsIgnoreCase("cloth"))
                return GameProcessor.storage.getProducts().indexOf(product);
        }
        return -1;
    }

    public void deliver(){
        setWorking(false);
        Product product = new Shirt();
        Position position = Position.randomPosition();
        GameProcessor.gameMap.productions[position.getY()][position.getX()].add(product);    }
}
