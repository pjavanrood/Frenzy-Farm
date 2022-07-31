package model;

import controller.GameProcessor;

public abstract class Product {
    String name;
    int price;
    int space;
    TimeUnit producedDate; //at which date egg was produced
    boolean isInStorage;
    boolean isLoaded;
    boolean expired;
    int eliminationTime;

    public Product(String name, int price, int space, int eliminationTime) {
        this.name = name;
        this.price = price;
        this.space = space;
        (this.producedDate = new TimeUnit()).setUnit(GameProcessor.date.getUnit());
        this.isInStorage = false;
        this.isLoaded = false;
        this.expired = false;
        this.eliminationTime = eliminationTime;
    }

    public boolean isInStorage() {
        return isInStorage;
    }

    public boolean isExpired() {
        return expired;
    }

    public TimeUnit getProducedDate() {
        return producedDate;
    }

    public String getName() {
        return name;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public void updateProduct(){
        if(GameProcessor.date.getUnit() - producedDate.getUnit() > eliminationTime){
            expired = true;
        }
    }

    public boolean pickUp(){
        if(!GameProcessor.storage.checkAvailableSpace(this))
            return false;

        isInStorage = true;
        GameProcessor.storage.addToStorage(this);
        return true;
    }
}
