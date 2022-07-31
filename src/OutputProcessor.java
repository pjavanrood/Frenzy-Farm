import controller.GameProcessor;
import controller.logger.HandleLogger;


public class OutputProcessor {
    public static void animalNotFound(){
        System.err.println("Animal was not found!");
        HandleLogger.printError("Animal not found.");
    }
    public static void notEnoughCoin(){
        System.err.println("Insufficient Coins!");
        HandleLogger.printError("Not enough coin.");
    }
    public static void animalBuySuccessful(String name, int x, int y){
        System.out.println(name + " Bought Successfully!");
        System.out.printf("Initial Position: %d %d\n", y, x);
        HandleLogger.printInfo("Buy animal successful.");
    }
    public static void wellIsBusy(){
        System.err.println("Well is Currently Busy!");
        HandleLogger.printError("Well is busy.");
    }
    public static void wellNotEmpty(){
        System.err.println("Well is Not Empty!");
        HandleLogger.printError("Well is not empty.");
    }
    public static void wellCommandSuccessful(){
        System.out.println("Draining Process Started Successfully! Remaining Draining Time: 3");
        HandleLogger.printInfo("Well command successful.");
    }
    public static void noGrassAlert(){System.out.println("there is NO Grass in field!");
    HandleLogger.printAlarm("No grass in field.");
    }
    public static void wellEmpty(){System.err.println("Insufficient Water Amount!");
    HandleLogger.printError("Well is empty.");
    }
    public static void plantSuccessfully(){
        System.out.println("Plant Successful!");
        HandleLogger.printInfo("Plant successfully.");
    }
    public static void noProductFound(){System.err.println("No Product found at the given coordinates!");
    HandleLogger.printError("No product found.");
    }
    public static void notEnoughStorage(){System.err.println("Not Enough Storage!");
    HandleLogger.printError("Not enough storage.");}


    //*********************
    public static void pickUpSuccessfully(){System.out.println("Product Stored Successfully!");
    AlertBox.display("Pickup", "Pick up successfully!");
    HandleLogger.printInfo("Pick up successfully.");}




    public static void invalidCoordinates(){System.err.println("Coordinate is out of range!");
    HandleLogger.printError("Invalid coordinates.");}
    public static void productNotStored(){System.err.println("Named Product was not found in Storage!");
    HandleLogger.printError("Product not stored.");}
    public static void wildNotCaged(){System.err.println("Named Wild Animal was not Caged!");
    HandleLogger.printError("Wild animal not caged.");}
    public static void productLoadedSuccessfully(){System.out.println("Product Loaded Successfully!");
    HandleLogger.printInfo("Product loaded successfully.");}
    public static void wildLoadedSuccessfully(){System.out.println("Wild Animal Loaded Successfully!");
    HandleLogger.printInfo("Wild animal loaded successfully.");}
    public static void productNotFoundInTruck(){System.err.println("Product was not loaded!");
    HandleLogger.printError("Product not found.");}
    public static void wildNotFoundInTruck(){System.err.println("Wild Animal was not loaded!");
    HandleLogger.printError("Wild animal not found in truck");}
    public static void unloadedSuccessfully(){System.out.println("Unloaded Successfully!");
    HandleLogger.printInfo("Unloaded Successfully.");}
    public static void truckIsWorking(){System.err.println("Truck is Unavailable!");
    HandleLogger.printError("Truck is working.");}
    public static void truckGone(int eta){System.out.printf("Truck is gone now! ETA %d units!\n", eta);
    HandleLogger.printInfo("Truck is gone.");}
    public static void sellReceipt(int date, int sellMoney){
        System.out.printf("Date: %d -> Truck is Back! -> %d coins were added to your account!\n", date, sellMoney);
        HandleLogger.printInfo("Sell receipt.");
    }
    public static void noWildAnimal(){System.err.println("No Wild Animal at the given coordinate!");
    HandleLogger.printError("No wild animal.");}

    //************************
    public static void invalidCageCommand(){System.err.println("Invalid Cage Command!");
    AlertBox.display("Cage", "Invalid Cage Command!");
    HandleLogger.printError("Invalid cage command.");}

    public static void cageCommandSuccessful(int remaining){System.out.printf("Cage Command Successful! %d commands remaining!\n", remaining);
    AlertBox.display("Cage", "Cage Command Successful! " + remaining + " commands remaining!");
    HandleLogger.printInfo("Cage command successful.");}

    public static void cageCompleted(){System.out.println("Animal Caged successfully!");
    AlertBox.display("Cage", "Animal Caged successfully!");
    HandleLogger.printInfo("Cage completed.");}



    public static void factoryBuild(){System.out.println("Factory built successfully!");
    HandleLogger.printInfo("Factory built.");};
    public static void factoryNotFound(){System.err.println("Factory not Found!");
    HandleLogger.printError("Factory not found.");}
    public static void factoryExists(){System.err.println("Factory already Exists!");
    HandleLogger.printError("Factory exists.");}
    public static void factoryNotExist(){System.err.println("Factory does not exist!");
    HandleLogger.printError("Factory does`nt exist.");}
    public static void factoryStart(int time){System.out.printf("Factory started working! remaining Time: %d\n", time);
    HandleLogger.printInfo("Factory starts working.");}
    public static void factoryNotBuilt(){System.err.println("Factory was not built!");
    HandleLogger.printError("Factory was`nt built.");}
    public static void factoryIsBusy(){System.err.println("Factory is busy!");
    HandleLogger.printError("Factory is busy.");}
    public static void inputNotExist(){System.err.println("Input does not exist!");
    HandleLogger.printError("Input does`nt exist.");}
    public static void upgradeSuccessful() {
        System.out.println("Upgrade successful.");
        HandleLogger.printInfo("Upgrade successful.");
    }
    public static void invalidUpgrade(){System.err.println("Invalid upgrade!");
    HandleLogger.printError("Invalid upgrade.");}
    public static void upgradeNotPossible(){System.err.println("upgrade is impossible!");
    HandleLogger.printError("Upgrade not possible.");}


    //****************
    public static void repititiousUsername(){System.err.println("This username was added before!");
        AlertBox.display("Error", "This Username already exists!");
    HandleLogger.printError("Repititious username.");} // repititoususername

    public static void turn() {//turn button
        System.out.println("turn " + GameProcessor.date.getUnit() + "!");
        AlertBox.display("Turn", "Turn " + GameProcessor.date.getUnit());
        HandleLogger.printInfo("turn " + GameProcessor.date.getUnit() + " started.");
    }

    public static void invalidPassword(){System.err.println("Invalid password!");
    HandleLogger.printError("Invalid username.");} // invalidpassword

    public static void signupSuccessful(){System.out.println("Signup successful!");
    HandleLogger.printInfo("Sign up successful.");} // signupsuccessful


    //**************
    public static void invalidUsername(){System.err.println("Invalid username!");
        AlertBox.display("Error", "Username not found! \n   Sign up first!");
    HandleLogger.printError("Invalid username.");} // invalidusername



    public static void loginSuccessful(){System.out.println("Login successful!");
    HandleLogger.printInfo("Login successful.");} // loginsuccessful
    public static void invalidCommand(){System.err.println("Invalid command!");
    HandleLogger.printError("Invalid command.");} // invalidcommand
    public static void logoutSuccessful(){System.out.println("Logout Successful!");
    HandleLogger.printInfo("Log out successful.");} // logoutsuccessful


    //**************
    public static void levelIsLocked(){
        System.err.println("This level is locked!");
        HandleLogger.printError("Level is locked.");
        AlertBox.display("Error", "Level is Locked!");
    } // levelislocked



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













}
