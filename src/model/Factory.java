package model;

import controller.GameProcessor;
import model.factories.*;

import java.util.ArrayList;

public class Factory {
    public static final String[] allFactoryNames = {"milk packing factory", "mill", "textile factory", "bakery", "tailor shop", "ice cream factory"};
    private final String name;
    private ArrayList<TimeUnit> productDeliverTimes;
    private final Class input;
    private final Class output;
    final int price;
    int produceTime;
    boolean isWorking;
    boolean upgraded; //false: lvl 1 , true: lvl 2
    boolean multiTaskUpgrade;
    boolean speedUpgrade;

    public Factory(String name, Class input, Class output, int price, int produceTime) {
        productDeliverTimes = new ArrayList<>();
        this.name = name;
        this.input = input;
        this.output = output;
        this.price = price;
        this.produceTime = produceTime;
        this.isWorking = false;
        upgraded = false;
        multiTaskUpgrade = false;
        speedUpgrade = false;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public int getPrice() {
        return price;
    }

    public static boolean checkFactoryExist(String name){
        for (Factory factory : GameProcessor.factories) {
            if (factory.name.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public static int getFactoryIndex(String name){
        int factoryIndex = -1;

        for (Factory factory : GameProcessor.factories) {
            if(factory.getName().equalsIgnoreCase(name))
                factoryIndex = GameProcessor.factories.indexOf(factory);
        }

        return factoryIndex;
    }

    public String getName() {
        return name;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public int getProduceTime() {
        return produceTime;
    }

    public void checkWorking(){
        if(productDeliverTimes.size() == 0)
            isWorking = false;
        else
            isWorking = true;
    }

    public static int getFactoryType(String name){
        for(int i = 0; i < 6; i++){
            if(allFactoryNames[i].equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }

    public int checkInputExists(){
        switch (this.name) {
            case "milk packing factory" : {
                return MilkPackingFactory.checkMilkExists();
            }
            case "mill" : {
                return Mill.checkEggExists();
            }
            case "textile factory" : {
                return TextileFactory.checkFeatherExists();
            }
            case "bakery" : {
                return Bakery.checkFlourExists();
            }
            case "tailor shop" : {
                return TailorShop.checkClothExists();
            }
            case "ice cream factory" : {
                return IceCreamFactory.checkPackedMilkExists();
            }
        }
        return -1;
    }

    public void startWorking(int productIndex){
        isWorking = true;
        GameProcessor.storage.giveProductToFactory(productIndex);
        TimeUnit timeUnit = new TimeUnit();
        timeUnit.setUnit(GameProcessor.date.getUnit() + produceTime);
        productDeliverTimes.add(timeUnit);
    }

    public void updateFactory(){
        for(int i = productDeliverTimes.size() - 1; i > -1; i--)
        {
            if(GameProcessor.date.getUnit() >= productDeliverTimes.get(i).getUnit())
            {
                productDeliverTimes.remove(i);
                deliverProduct();
                checkWorking();
            }
        }

    }

    public void deliverProduct(){
        if(this instanceof MilkPackingFactory) {
            ((MilkPackingFactory) this).deliver();
            return;
        }

        if(this instanceof Mill){
            ((Mill) this).deliver();
            return;
        }

        if(this instanceof TextileFactory){
            ((TextileFactory) this).deliver();
            return;
        }

        if(this instanceof Bakery){
            ((Bakery) this).deliver();
            return;
        }

        if(this instanceof TailorShop){
            ((TailorShop) this).deliver();
            return;
        }

        if(this instanceof IceCreamFactory){
            ((IceCreamFactory) this).deliver();
        }

    }

    public static void updateAllFactories(){
        for (Factory factory : GameProcessor.factories) {
            factory.updateFactory();
        }
    }

    public boolean isWorkingPossible(){
        if(!isWorking)
            return true;

        if(multiTaskUpgrade && productDeliverTimes.size() == 1)
            return true;

        return false;
    }

    public static int getUpgradeType(String type){
        if(type.equalsIgnoreCase("speed"))
            return 1;

        if(type.equalsIgnoreCase("multitask"))
            return 2;

        return -1;
    }

    public boolean isUpgradePossible(){
        return !upgraded;
    }

    public void upgradeMultiTask(){
        upgraded = true;
        multiTaskUpgrade = true;
    }

    public void upgradeSpeed(){
        speedUpgrade = true;
        produceTime = produceTime / 2;
    }
}
