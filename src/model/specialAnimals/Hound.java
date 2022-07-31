package model.specialAnimals;

import controller.GameProcessor;
import model.AliveCreature;
import model.WildAnimals;

import java.util.ArrayList;

public class Hound extends AliveCreature {
    public Hound() {
        super("hound", 1, 100);
    }

    public void killWildAnimal(){
        ArrayList<AliveCreature> positionCreatures = GameProcessor.gameMap.creature[getPosition().getY()][getPosition().getX()];

        for (AliveCreature positionCreature : positionCreatures) {
            if(positionCreature instanceof WildAnimals){
                ((WildAnimals) positionCreature).getAttackedByDog();
                setAlive(false);
                return;
            }
        }
    }
}
