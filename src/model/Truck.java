package model;

import controller.GameProcessor;


import java.util.ArrayList;

public class Truck {
    final int SPACE = 15;
    ArrayList<Product> storedProducts;
    ArrayList<WildAnimals> wildAnimals;
    TimeUnit departureTime;
    final int SELLING_TIME = 10;
    boolean isWorking;
    boolean isFull;
    int availableSpace;
    int sellMoney;

    public Truck() {
        storedProducts = new ArrayList<>();
        wildAnimals = new ArrayList<>();
        departureTime = new TimeUnit();
        isWorking = false;
        isFull = false;
        availableSpace = SPACE;
        sellMoney = 0;
    }

    public ArrayList<Product> getStoredProducts() {
        return storedProducts;
    }

    public ArrayList<WildAnimals> getWildAnimals() {
        return wildAnimals;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public int getSELLING_TIME() {
        return SELLING_TIME;
    }

    public boolean checkAvailableSpace(Product product){
        if(isFull)
            return false;

        if(availableSpace < product.space)
            return false;

        return true;
    }

    public boolean checkAvailableSpace(){
        if(availableSpace < WildAnimals.getSTORAGE_SPACE())
            return false;
        return true;
    }

    public void load(Product product){
        storedProducts.add(product);
        availableSpace -= product.space;
        product.setLoaded(true);
        setFull();
        GameProcessor.storage.loadToTruck(product);
    }

    public void load(WildAnimals wildAnimal){
        if(!wildAnimal.isCaged)
            return;
        wildAnimals.add(wildAnimal);
        wildAnimal.setLoaded(true);
        availableSpace -= WildAnimals.getSTORAGE_SPACE();
        setFull();
        GameProcessor.storage.loadWildToTruck(wildAnimal);
    }

    public void unLoad(Product unloadingProduct){
        unloadingProduct.setLoaded(false);
        GameProcessor.storage.addToStorage(unloadingProduct);
        storedProducts.remove(unloadingProduct);
        availableSpace += unloadingProduct.space;
        setFull();
    }

    public void unLoadAnimal(WildAnimals unLoadingAnimal){
        unLoadingAnimal.setLoaded(false);
        GameProcessor.storage.addToStorage(unLoadingAnimal);
        wildAnimals.remove(unLoadingAnimal);
        availableSpace += WildAnimals.getSTORAGE_SPACE();
        setFull();
    }

    public void setFull(){
        if(availableSpace == 0)
            isFull = true;

        if(availableSpace > 0 && isFull)
            isFull = false;
    }

    public void calculateSellMoney(){
        int sellMoney = 0;
        for (Product storedProduct : storedProducts) {
            sellMoney += storedProduct.price;
        }

        for (WildAnimals wildAnimal : wildAnimals) {
            sellMoney += wildAnimal.getPrice();
        }

        this.sellMoney = sellMoney;
    }

    public void go(){
        isWorking = true;
        departureTime.setUnit(GameProcessor.date.getUnit());
        calculateSellMoney();
    }

    public void update(){
        if(isWorking && GameProcessor.date.getUnit() - departureTime.getUnit() == SELLING_TIME){
            GameProcessor.addCoin(sellMoney);
            //OutputProcessor.sellReceipt(controller.GameProcessor.date.getUnit(), sellMoney);
            storedProducts.clear();
            wildAnimals.clear();
            departureTime = new TimeUnit();
            isWorking = false;
            isFull = false;
            availableSpace = 15;
            sellMoney = 0;
        }

    }
}
