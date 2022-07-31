package controller.missionprocessor;

import model.Product;
import model.WildAnimals;
import model.products.*;
import model.wildAnimals.Bear;
import model.wildAnimals.Lion;
import model.wildAnimals.Tiger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private Task task;
    private String line;
    private int initial_coin;
    private int reward_coin;
    private boolean passed;
    private int wildanimalsappearanceperiod;
    private int rewardtime;
    private int maxtime;

    public Level(int levelnum) {
        try {
            this.line = Files.readAllLines(Paths.get("levels.txt")).get(levelnum - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.passed = false;
        this.initial_coin = Integer.parseInt(line.split("\\s")[1]);
        this.reward_coin = Integer.parseInt(line.split("\\s")[2]);
        this.wildanimalsappearanceperiod = Integer.parseInt(line.split("\\s")[4]);
        this.task = new Task(Integer.parseInt(line.split("\\s")[3]), productsTask(ProductsAndAmounts(line.split("\\s"))), productsAmountTask(ProductsAndAmounts(line.split("\\s"))));
        this.rewardtime = Integer.parseInt(line.split("\\s")[line.split("\\s").length - 2]);
        this.maxtime = Integer.parseInt(line.split("\\s")[line.split("\\s").length - 1]);
    }

    public int getRewardtime() {
        return rewardtime;
    }

    public int getMaxtime() {
        return maxtime;
    }

    public static ArrayList<String> productsTask(String products) {
        ArrayList<String> output = new ArrayList<>();
        String [] productsandamount = products.split("\\s");
        for (int i = 0; i < productsandamount.length - 1; i++) {
            if(ProductsAndWildanimalsName.PRODUCTS.getmatcher(productsandamount[i]).matches()) {
                output.add(productsandamount[i]);
            }
        }
        return output;
    }

    public static ArrayList<Integer> productsAmountTask(String products) {
        ArrayList<Integer> output = new ArrayList<>();
        String [] productsandamount = products.split("\\s");
        for (int i = 0; i < productsandamount.length - 1; i++) {
            if(ProductsAndWildanimalsName.PRODUCTS.getmatcher(productsandamount[i]).matches()) {
                output.add(Integer.parseInt(productsandamount[i + 1]));
            }
        }
        return output;
    }

    /*public static Product productMaker(String productname) {
        if (productname.equalsIgnoreCase("egg")) {
            return new Egg();
        } else if (productname.equalsIgnoreCase("flour")) {
            return new Flour();
        } else if (productname.equalsIgnoreCase("bread")) {
            return new Bread();
        } else if (productname.equalsIgnoreCase("feather")) {
            return new Feather();
        } else if (productname.equalsIgnoreCase("milk")) {
            return new Milk();
        } else if (productname.equalsIgnoreCase("pasteurizedmilk")) {
            return new PasteurizedMilk();
        } else if (productname.equalsIgnoreCase("shirt")) {
            return new Shirt();
        } else if (productname.equalsIgnoreCase("cloth")) {
            return new Cloth();
        } else if (productname.equalsIgnoreCase("icecream")) {
            return new IceCream();
        }
        return null;
    }*/

    public static String ProductsAndAmounts(String[] line){
        int a = 0;
        String output = "";
        for (int i = 0; i < line.length; i++) {
            if (line[i].equalsIgnoreCase("turn")) {
                a = i;
            }
        }
        for (int i = 5; i < a; i++) {
            output += line[i];
            output += " ";
        }
        return output;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getInitial_coin() {
        return initial_coin;
    }

    public void setInitial_coin(int initial_coin) {
        this.initial_coin = initial_coin;
    }

    public int getReward_coin() {
        return reward_coin;
    }

    public void setReward_coin(int reward_coin) {
        this.reward_coin = reward_coin;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getWildanimalsappearanceperiod() {
        return wildanimalsappearanceperiod;
    }

    public void setWildanimalsappearanceperiod(int wildanimalsappearanceperiod) {
        this.wildanimalsappearanceperiod = wildanimalsappearanceperiod;
    }

}

