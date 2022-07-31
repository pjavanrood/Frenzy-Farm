package model.domesticAnimals;

import model.DomesticAnimals;
import model.products.Egg;

public class Hen extends DomesticAnimals {
    public Hen() {
        super("hen", 2, Egg.class, 1, 100);
    }
}
