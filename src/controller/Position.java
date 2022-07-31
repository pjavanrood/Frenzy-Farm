package controller;

import java.util.Random;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position randomPosition(){
        Random random = new Random();
        return new Position(random.nextInt(6), random.nextInt(6));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static boolean isCoordinateValid(int x, int y){
        if(x < 0 || x > 5 || y < 0 || y > 5)
            return false;
        return true;
    }
}
