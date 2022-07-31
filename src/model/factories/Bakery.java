package model.factories;

import controller.GameProcessor;
import controller.Position;
import model.Factory;
import model.Product;
import model.products.Bread;
import model.products.Flour;

public class Bakery extends Factory {
    public Bakery() {
        super("bakery", Flour.class, Bread.class, 250, 5);
    }

    public static int checkFlourExists(){
        for (Product product : GameProcessor.storage.getProducts()) {
            if(product.getName().equalsIgnoreCase("flour"))
                return GameProcessor.storage.getProducts().indexOf(product);
        }
        return -1;
    }

    public void deliver(){
        setWorking(false);
        Product product = new Bread();
        Position position = Position.randomPosition();
        GameProcessor.gameMap.productions[position.getY()][position.getX()].add(product);    }
}
