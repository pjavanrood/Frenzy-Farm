package view;

import controller.GameProcessor;
import controller.logger.HandleLogger;
import model.*;
import model.specialAnimals.Cat;
import model.specialAnimals.Hound;

import java.io.IOException;


public class OutputProcessor {
    public static AlertBox alertBox = new AlertBox();

    public OutputProcessor() {
        this.alertBox = new AlertBox();
    }

    public static void animalNotFound(){
        System.err.println("Animal was not found!");
        HandleLogger.printError("Animal not found.");
    }


    public static void wellIsBusy(){
        System.err.println("Well is Currently Busy!");
        try {
            alertBox.display("Well", "Well is busy!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Well is busy.");
    }
    public static void wellNotEmpty(){
        System.err.println("Well is Not Empty!");
        try {
            alertBox.display("Well", "Well is not empty!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Well is not empty.");
    }
    public static void wellCommandSuccessful(){
        System.out.println("Draining Process Started Successfully! Remaining Draining Time: 3");
        try {
            alertBox.display("Well", "Draining process started!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Well command successful.");
    }
    public static void noGrassAlert(){System.out.println("there is NO Grass in field!");
    HandleLogger.printAlarm("No grass in field.");
    }


    public static void noProductFound(){System.err.println("No Product found at the given coordinates!");
    HandleLogger.printError("No product found.");
    }

    public static void turn() {//turn button
        System.out.println("turn " + GameProcessor.date.getUnit() + "!");
        try {
            alertBox.display("Turn", "Turn " + GameProcessor.date.getUnit());
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("turn " + GameProcessor.date.getUnit() + " started.");
    }

    public static void invalidCoordinates(){System.err.println("Coordinate is out of range!");
    HandleLogger.printError("Invalid coordinates.");}
    public static void productNotStored(){System.err.println("Named Product was not found in Storage!");
    HandleLogger.printError("Product not stored.");}
    public static void wildNotCaged(){System.err.println("Named Wild Animal was not Caged!");
    HandleLogger.printError("Wild animal not caged.");}

    public static void productNotFoundInTruck(){System.err.println("Product was not loaded!");
    HandleLogger.printError("Product not found.");}
    public static void wildNotFoundInTruck(){System.err.println("Wild Animal was not loaded!");
    HandleLogger.printError("Wild animal not found in truck");}
    public static void unloadedSuccessfully(){System.out.println("Unloaded Successfully!");
    HandleLogger.printInfo("Unloaded Successfully.");}


    public static void sellReceipt(int date, int sellMoney){
        System.out.printf("Date: %d -> Truck is Back! -> %d coins were added to your account!\n", date, sellMoney);
        HandleLogger.printInfo("Sell receipt.");
    }
    public static void noWildAnimal(){System.err.println("No Wild Animal at the given coordinate!");
    HandleLogger.printError("No wild animal.");}





    public static void factoryBuild(){System.out.println("Factory built successfully!");
    HandleLogger.printInfo("Factory built.");};
    public static void factoryNotFound(){System.err.println("Factory not Found!");
    HandleLogger.printError("Factory not found.");}
    public static void factoryExists(){System.err.println("Factory already Exists!");
    HandleLogger.printError("Factory exists.");}
    public static void factoryNotExist(){System.err.println("Factory does not exist!");
    HandleLogger.printError("Factory does`nt exist.");}

    public static void factoryNotBuilt(){System.err.println("Factory was not built!");
    HandleLogger.printError("Factory was`nt built.");}



    public static void invalidUpgrade(){System.err.println("Invalid upgrade!");
    HandleLogger.printError("Invalid upgrade.");}




    public static void invalidPassword(){System.err.println("Invalid password!");
    HandleLogger.printError("Invalid username.");} // invalidpassword

    public static void signupSuccessful(){System.out.println("Signup successful!");
    HandleLogger.printInfo("Sign up successful.");} // signupsuccessful




    public static void loginSuccessful(){System.out.println("Login successful!");
    HandleLogger.printInfo("Login successful.");} // loginsuccessful
    public static void invalidCommand(){System.err.println("Invalid command!");
    HandleLogger.printError("Invalid command.");} // invalidcommand
    public static void logoutSuccessful(){System.out.println("Logout Successful!");
    HandleLogger.printInfo("Log out successful.");} // logoutsuccessful




    public static void levelPassed(int level){System.out.println("Congratulation! you`ve passed level " + level + "!");System.out.println();
    HandleLogger.printInfo("Level passed.");}
    public static void gameOver(){System.err.println("GAME OVER :(");
    HandleLogger.printError("Game over.");}
    public static void startLevel(int level){
        System.out.println("Level " + level + " starts!" );
        System.out.println("Complete tasks in " + GameProcessor.level.getRewardtime() + " turns to earn " + GameProcessor.level.getReward_coin() + " coins!");
        System.out.println("Complete tasks before turn " + GameProcessor.level.getMaxtime() + "!");
        System.out.println("GOOD LUCK :)");
        HandleLogger.printInfo("Level starts.");
    }



    public static void cageCommandSuccessful(int remaining){System.out.printf("Cage Command Successful! %d commands remaining!\n", remaining);
        try {
            alertBox.display("Cage", "Cage Command Successful!\n" + remaining + " commands remaining!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Cage command successful.");}

    public static void cageCompleted(){System.out.println("Animal Caged successfully!");
        try {
            alertBox.display("Cage", "Animal Caged successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Cage completed.");}



    //**********************
    public static void notEnoughStorage(){System.err.println("Not Enough Storage!");
        try {
            alertBox.display("Truck", "Not Enough Storage!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Not enough storage.");}


    //*********************
    public static void pickUpSuccessfully() {System.out.println("Product Stored Successfully!");
        try {
            alertBox.display("Pickup", "Pick up successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Pick up successfully.");}




    //************************
    public static void productLoadedSuccessfully(){System.out.println("Product Loaded Successfully!");
        try {
            alertBox.display("Truck", "Product Loaded Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Product loaded successfully.");}

    //*************************
    public static void wildLoadedSuccessfully(){System.out.println("Wild Animal Loaded Successfully!");
        try {
            alertBox.display("Truck", "Wild Animal Loaded Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Wild animal loaded successfully.");}


    //****************
    public static void repititiousUsername(){System.err.println("This username was added before!");
        try {
            alertBox.display("Error", "This Username already exists!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Repititious username.");} // repititoususername

    //**************
    public static void invalidUsername(){System.err.println("Invalid username!");
        try {
            alertBox.display("Error", "Username not found! \n   Sign up first!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Invalid username.");} // invalidusername

    //**************
    public static void levelIsLocked(){
        System.err.println("This level is locked!");
        HandleLogger.printError("Level is locked.");
        try {
            alertBox.display("Error", "Level is Locked!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // levelislocked


    //************************
    public static void invalidCageCommand(){System.err.println("Invalid Cage Command!");
        try {
            alertBox.display("Cage", "Invalid Cage Command!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Invalid cage command.");}

    public static void inputNotExist(){System.err.println("Input does not exist!");
        try {
            alertBox.display("Factory", "Input does not exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Input doesn't exist.");}


    public static void factoryIsBusy(){System.err.println("Factory is busy!");
        try {
            alertBox.display("Factory", "Factory is busy!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Factory is busy.");}

    public static void factoryStart(int time){System.out.printf("Factory started working! remaining Time: %d\n", time);
        try {
            alertBox.display("Factory", "Factory started working!\nremaining Time: " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Factory starts working.");}

    public static void truckIsWorking(){System.err.println("Truck is Unavailable!");
        try {
            alertBox.display("Truck", "Truck is Unavailable!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Truck is working.");}

    public static void truckGone(int eta){System.out.printf("Truck is gone now!\nETA %d units!\n", eta);
        try {
            alertBox.display("Truck", "Truck is gone now!\nETA " + eta + " units!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Truck is gone.");}

    public static void truckEmpty(){System.out.printf("Truck is Empty!");
        try {
            alertBox.display("Truck", "Truck is Empty!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Truck is Empty!");}


    public static void notEnoughCoin(){
        System.err.println("Insufficient Coins!");
        try {
            alertBox.display("Shop", "Not enough coin.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Not enough coin.");
    }

    public static void animalBuySuccessful(String name, int x, int y){
        System.out.println(name + " Bought Successfully!");
        System.out.printf("Initial Position: %d %d\n", y, x);
        try {
            alertBox.display("Shop", name + " Bought Successfully!\nInitial Position: " + (y + 1) + ":" + (x + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Buy animal successful.");
    }

    public static void wellEmpty(){System.err.println("Insufficient Water Amount!");
        try {
            alertBox.display("Well", "Insufficient Water Amount!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Well is empty.");
    }

    public static void plantSuccessfully(){
        System.out.println("Plant Successful!");
        try {
            alertBox.display("Well", "Plant Successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Plant successfully.");
    }

    public static void upgradeNotPossible(){System.err.println("upgrade is impossible!");
        try {
            alertBox.display("Factory", "upgrade is impossible!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printError("Upgrade not possible.");}


    public static void upgradeSuccessful() {
        System.out.println("Upgrade successful.");
        try {
            alertBox.display("Factory", "Upgrade successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HandleLogger.printInfo("Upgrade successful.");
    }

    public static void inquiry(){
        //Date
        System.out.println("Date: " + GameProcessor.date.getUnit());

        //Coins
        System.out.println("coins: " + GameProcessor.coins + "\n");

        //Grass
        GameProcessor.gameMap.emptyGrassAlert();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(GameProcessor.gameMap.grass[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //Well
        System.out.printf("remaining water: %d - is well working : %b\n\n", GameProcessor.well.getRemainingWater(), GameProcessor.well.isBusy());


        //Creatures on map
        System.out.println("Alive Creatures");
        for (AliveCreature allCreature : GameProcessor.allCreatures) {
            if(allCreature instanceof DomesticAnimals)
                System.out.println(allCreature.getName() + " - " + ((DomesticAnimals) allCreature).getLife() + " - [" + allCreature.getPosition().getX() + " , " + allCreature.getPosition().getY() + "]");

            else if(allCreature instanceof Cat || allCreature instanceof Hound)
                System.out.println(allCreature.getName() + " - [" + allCreature.getPosition().getX() + " , " + allCreature.getPosition().getY() + "]");

            else{
                System.out.println(allCreature.getName() + " - [" + allCreature.getPosition().getX() + " , " + allCreature.getPosition().getY() + "]" + " - is Caged: " +((WildAnimals) allCreature).isCaged());
            }
        }
        System.out.println();

        //products on map
        System.out.println("On map Products");
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                for (Product product : GameProcessor.gameMap.productions[i][j]) {
                    System.out.printf("%s - produced time: %d - [%d , %d]\n", product.getName(), product.getProducedDate().getUnit(), j, i);
                }
            }
        }
        System.out.println();

        //products in storage
        System.out.println("Stored Products");
        for (Product product : GameProcessor.storage.getProducts()) {
            System.out.print(product.getName() + " - ");
        }
        System.out.println();

        //caged wild animals
        System.out.println("Stored Caged Animals:");
        for (WildAnimals wildAnimal : GameProcessor.storage.getWildAnimals()) {
            System.out.println(wildAnimal.getName() + " - ");
        }
        System.out.println();

        //loaded products
        System.out.println("Loaded Products (Truck Available: " + !GameProcessor.truck.isWorking() + ")");
        for (Product storedProduct : GameProcessor.truck.getStoredProducts()) {
            System.out.print(storedProduct.getName() + " - ");
        }
        System.out.println();

        //loaded animals
        System.out.println("Loaded Animals:");
        for (WildAnimals wildAnimal : GameProcessor.truck.getWildAnimals()) {
            System.out.print(wildAnimal.getName() + " - ");
        }
        System.out.println();

        //factories
        for (Factory factory : GameProcessor.factories) {
            System.out.println(factory.getName() + " - is working: " + factory.isWorking());
        }
        System.out.println();

        //tasks
        for (int i = 0; i < GameProcessor.level.getTask().getProductsname().size(); i++) {
            System.out.print(GameProcessor.level.getTask().getProductsname().get(i));
            if (GameProcessor.taskproductsamount.size() == 0) {
                System.out.print(": 0/");
            } else if (i >= GameProcessor.taskproductsamount.size()) {
                System.out.print(": 0/");
            } else {
                System.out.print(": " + GameProcessor.taskproductsamount.get(i) + "/");
            }
            System.out.println(GameProcessor.level.getTask().getAmounts().get(i));
        }
        System.out.println("Coins: " + GameProcessor.coins + "/" + GameProcessor.level.getTask().getCoin_amount_goal());
    }

}
