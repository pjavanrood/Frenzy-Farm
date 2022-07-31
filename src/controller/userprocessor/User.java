package controller.userprocessor;

import controller.GameProcessor;

public class User {
    private String name;
    private String password;
    private int levelpassed;
    private int coins;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.levelpassed = 1;
        this.coins = 0;
    }

    public User(String name, String password, int levelpassed, int coins) {
        this.name = name;
        this.password = password;
        this.levelpassed = levelpassed;
        this.coins = coins;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevelpassed() {
        return levelpassed;
    }

    public void setLevelpassed(int levelpassed) {
        this.levelpassed = levelpassed;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void goNextLevel(){
        levelpassed ++;
    }

    public int findUser(User user) {
        if (GameProcessor.users.size() == 0) {
            return 0;
        }
        for (int i = 0; i < GameProcessor.users.size(); i++) {
            if (GameProcessor.users.get(i).getName().equals(user.getName())) {
                if (GameProcessor.users.get(i).getPassword().equals(user.getPassword())) {
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }

}
