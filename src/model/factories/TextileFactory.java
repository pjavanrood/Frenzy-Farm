package model.factories;

import controller.GameProcessor;
import controller.Position;
import model.Factory;
import model.Product;
import model.products.Cloth;
import model.products.Feather;


public class TextileFactory extends Factory {
    public TextileFactory() {
        super("textile factory", Feather.class, Cloth.class, 250, 5);
    }

    public static int checkFeatherExists(){
        for (Product product : GameProcessor.storage.getProducts()) {
            if(product.getName().equalsIgnoreCase("feather"))
                return GameProcessor.storage.getProducts().indexOf(product);
        }
        return -1;
    }

    public void deliver(){
        setWorking(false);
        Product product = new Cloth();
        Position position = Position.randomPosition();
        GameProcessor.gameMap.productions[position.getY()][position.getX()].add(product);    }
}
