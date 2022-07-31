package missionprocessor;

import java.util.ArrayList;

public class Task
{
    private int coin_amount_goal;
    private ArrayList<String> productsname = new ArrayList<>();
    private ArrayList<Integer> amounts = new ArrayList<>();

    public Task(int coin_amount_goal, ArrayList<String> productsname, ArrayList<Integer> amounts) {
        this.coin_amount_goal = coin_amount_goal;
        this.productsname = productsname;
        this.amounts = amounts;
    }



    public int getCoin_amount_goal() {
        return coin_amount_goal;
    }

    public void setCoin_amount_goal(int coin_amount_goal) {
        this.coin_amount_goal = coin_amount_goal;
    }

    public ArrayList<String> getProductsname() {
        return productsname;
    }

    public void setProductsname(ArrayList<String> productsname) {
        this.productsname = productsname;
    }

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }


}
