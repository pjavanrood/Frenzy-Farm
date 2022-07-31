package model.domesticAnimals;

import model.DomesticAnimals;
import model.products.Feather;

public class Buffalo extends DomesticAnimals {
    public Buffalo() {
        super("buffalo", 4, Feather.class, 1, 400);
    }
}
