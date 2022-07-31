package model.factories;

import controller.GameProcessor;
import controller.Position;
import model.Factory;
import model.Product;
import model.products.Cloth;
import model.products.Egg;
import model.products.Flour;


public class Mill extends Factory {
    public Mill() {
        super("mill", Egg.class, Cloth.class, 150, 4);
    }

    public static int checkEggExists(){
        for (Product product : GameProcessor.storage.getProducts()) {
            if(product.getName().equalsIgnoreCase("egg"))
                return GameProcessor.storage.getProducts().indexOf(product);
        }
        return -1;
    }

    public void deliver(){
        setWorking(false);
        Product product = new Flour();
        Position position = Position.randomPosition();
        GameProcessor.gameMap.productions[position.getY()][position.getX()].add(product);
    }
}
