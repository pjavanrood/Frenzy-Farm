package model;

import controller.GameProcessor;

public class Well {
    int remainingWater;
    boolean isEmpty;
    boolean isBusy;
    TimeUnit drainStartTime;

    public Well() {
        this.remainingWater = 0;
        this.isEmpty = true;
        this.isBusy = false;
        (this.drainStartTime = new TimeUnit()).setUnit(-1); //if well is not draining this should be -1.
    }

    public int getRemainingWater() {
        return remainingWater;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void drainWater () {
        isBusy = true;
        drainStartTime.setUnit(GameProcessor.date.getUnit());
    }

    public void checkDrainingComplete(){
        if(drainStartTime.getUnit() == -1 || GameProcessor.date.getUnit() - drainStartTime.getUnit() < 3){
            return;
        }

        isEmpty = false;
        isBusy = false;
        drainStartTime.setUnit(-1);
        remainingWater = 5; //Each Draining Process drains enough water for 5 Plants.
    }

    public void updateWell(){
        checkDrainingComplete();
        if(remainingWater == 0){
            isEmpty = true;
        }
    }

    public boolean getWater(){
        if(isEmpty)
            return false;
        remainingWater--;
        updateWell();
        return true;
    }

    public int checkWellRequest(){
        if(isBusy) // Well is busy draining water.
            return 1;

        if(!isEmpty) //Well should be empty to drain water
            return 2;

        return 0;
    }

}
