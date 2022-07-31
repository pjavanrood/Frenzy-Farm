package model.domesticAnimals;

import model.DomesticAnimals;
import model.products.Feather;

public class Turkey extends DomesticAnimals {
    public Turkey() {
        super("turkey", 3, Feather.class, 1, 200);
    }
}
