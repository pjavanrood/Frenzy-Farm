package model;

public class TimeUnit {
    private int unit;

    public TimeUnit(){
        this.unit = 0;
    }

    public TimeUnit(int unit) {
        this.unit = unit;
    }

    public void turn(){
        this.unit++;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
