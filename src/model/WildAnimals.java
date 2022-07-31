package model;

import controller.GameProcessor;
import model.wildAnimals.Bear;
import model.wildAnimals.Lion;
import model.wildAnimals.Tiger;

import java.util.ArrayList;
import java.util.Random;

public class WildAnimals extends AliveCreature {
    final static String[] wildAnimals = {"bear", "lion", "tiger"};
    int minimumCageCommands;
    final static int STORAGE_SPACE = 15;
    final int ELIMINATION_TIME = 5;
    public boolean isCaged;
    boolean isInStorage;
    boolean isLoaded;
    TimeUnit cagedTime;
    ArrayList<TimeUnit> cageCommands;


    public WildAnimals(String name, int velocity, int price, int minimumCageCommands) {
        super(name, velocity, price);
        this.minimumCageCommands = minimumCageCommands;
        this.isCaged = false;
        this.isInStorage = false;
        this.isLoaded = false;
        this.cagedTime = new TimeUnit();
        this.cageCommands = new ArrayList<>();
    }

    public static int getSTORAGE_SPACE() {
        return STORAGE_SPACE;
    }

    public boolean isCaged() {
        return isCaged;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public static int isWildAnimal(String name){
        for(int i = 0; i < 3; i++){
            if(wildAnimals[i].equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }

    public static void generateWildAnimal(){
        if(GameProcessor.date.getUnit() != 0 && GameProcessor.date.getUnit() % GameProcessor.wildAnimalAppearanceTime == 0){
            WildAnimals wildAnimal = null;


            //45% Lion - 35% Bear - 20% Tiger
            Random random = new Random();
            int randomNum = random.nextInt(100);

            if(randomNum < 45)
                wildAnimal = new Lion();

            else if(randomNum < 80)
                wildAnimal = new Bear();

            else
                wildAnimal = new Tiger();

            GameProcessor.allCreatures.add(wildAnimal);
            GameProcessor.gameMap.creature[wildAnimal.getPosition().getY()][wildAnimal.getPosition().getX()].add(wildAnimal);
        }
    }

    public void checkEating(){
        ArrayList<AliveCreature> positionCreatures = GameProcessor.gameMap.creature[getPosition().getY()][getPosition().getX()];
        if(positionCreatures.size() < 2)
            return;

        for (AliveCreature positionCreature : positionCreatures) {
            if(positionCreature instanceof DomesticAnimals) {
                ((DomesticAnimals) positionCreature).getHaunted();
                break;
            }
        }
        
        if(!(this instanceof Tiger))
            return;
        
        //Tiger's velocity is 2
        int previousDirection = getPreviousDirection();
        
        switch (previousDirection){
            case 0 : positionCreatures = GameProcessor.gameMap.creature[getPosition().getY()][getPosition().getX() - 1];
                break;
            case 1 : positionCreatures = GameProcessor.gameMap.creature[getPosition().getY() + 1][getPosition().getX()];
                break;
            case 2 : positionCreatures = GameProcessor.gameMap.creature[getPosition().getY()][getPosition().getX() + 1];
                break;
            case 3 : positionCreatures = GameProcessor.gameMap.creature[getPosition().getY() - 1][getPosition().getX()];
                break;
        }

        for (AliveCreature positionCreature : positionCreatures) {
            if(positionCreature instanceof DomesticAnimals) {
                ((DomesticAnimals) positionCreature).getHaunted();
                break;
            }
        }
    }

    public boolean checkEscape(){
        if(isCaged && GameProcessor.date.getUnit() - cagedTime.getUnit() >= ELIMINATION_TIME)
            return true;
        return false;
    }

    public boolean isCageCommandValid(){ //Cage commands should be in different time units
        int size = cageCommands.size();
        if(size == 0)
            return true;

    return GameProcessor.date.getUnit() - cageCommands.get(size - 1).getUnit() != 0;
    }

    public void addCageCommand(){
        cageCommands.add(new TimeUnit(GameProcessor.date.getUnit()));
    }

    public int calculateRemainingCageCommands(){
        int len = cageCommands.size();
        if(len == 0)
            return minimumCageCommands;

        int n = cageCommands.size();

        for(int i = 0; i < len - 1; i++){
            int delay = cageCommands.get(i + 1).getUnit() - cageCommands.get(i).getUnit() - 1;
            if(delay > 0)
            {
                n -= delay;
            }
        }

        if(n <= 0)
            return minimumCageCommands - 1;

        return minimumCageCommands - n;
    }

    public boolean isCagingComplete(){
        int rem = calculateRemainingCageCommands();
        if(rem == 0)
            return true;

        return false;
    }

    public void cage(){
        isCaged = true;
        cagedTime.setUnit(GameProcessor.date.getUnit());
    }

    public boolean pickUp(){
        if(!GameProcessor.storage.checkAvailableSpace())
            return false;

        isInStorage = true;
        GameProcessor.storage.addToStorage(this);
        return true;
    }

    public void getAttackedByDog(){
        alive = false;
    }
}
