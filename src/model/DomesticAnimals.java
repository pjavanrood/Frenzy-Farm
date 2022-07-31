package model;

import controller.GameProcessor;
import model.products.Egg;
import model.products.Feather;
import model.products.Milk;

public abstract class DomesticAnimals extends AliveCreature{
    final static String[] domesticAnimals = {"hen", "turkey", "buffalo"};
    private final int produceTime;
    private Class productClass;
    private int life;
    TimeUnit productionStart;

    public DomesticAnimals(String name, int produceTime, Class productClass, int velocity, int price) {
        super(name, velocity, price);
        this.produceTime = produceTime;
        this.productClass = productClass;
        (this.productionStart = new TimeUnit()).setUnit(GameProcessor.date.getUnit());
        this.life = 10;
    }

    public int getLife() {
        return life;
    }

    public void updateLife () {
        life --;
        if(life == 0){
            alive = false;
        }
    }

    public void updateProduction(){
        if(GameProcessor.date.getUnit() - productionStart.getUnit() >= produceTime){
            produce();
        }
    }

    public static int isDomesticAnimal(String name){
        for(int i = 0; i < 3; i++){
            if(domesticAnimals[i].equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }

    public void checkEating(){
        if(!alive)
            return;

        if(life > 5)
            return;

        if(checkOverlap()){
            if(!GameProcessor.gameMap.hungryAnimal(getPosition()).equals(this))
                return;
        }

        if(GameProcessor.gameMap.grass[getPosition().getY()][getPosition().getX()] <= 0){
            return;
        }

        GameProcessor.gameMap.eatGrass(getPosition());
        eat();
    }

    public void eat(){
        life = 10;
    }

    public void produce(){
        Product product = null;
        switch (name){
            case "hen" : product = new Egg();
                break;
            case "turkey" : product = new Feather();
                break;
            case "buffalo" : product = new Milk();
                break;
        }
        GameProcessor.gameMap.productions[getPosition().getY()][getPosition().getX()].add(product);
        productionStart.setUnit(GameProcessor.date.getUnit());
    }

    public void getHaunted(){
        life = 0;
        alive = false;
    }

    public void updateDomesticPosition(){
        if(GameProcessor.gameMap.grasses.size() == 0) {
            this.moveRandom();
            return;
        }
        goToPosition(findClosestGrass(getPosition()));
    }

}
