package controller;

import controller.logger.HandleLogger;
import controller.missionprocessor.Level;
import controller.userprocessor.User;
import controller.userprocessor.UserFile;
import model.*;
import model.domesticAnimals.Buffalo;
import model.domesticAnimals.Hen;
import model.domesticAnimals.Turkey;
import model.factories.*;
import model.products.*;
import model.specialAnimals.Cat;
import model.specialAnimals.Hound;
import model.wildAnimals.Bear;
import model.wildAnimals.Lion;
import model.wildAnimals.Tiger;
import view.InputCommands;
import view.InputProcessor;
import view.OutputProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameProcessor {
    public static int coins;
    public static ArrayList<AliveCreature> allCreatures;
    public static ArrayList<Factory> factories;
    public static Map gameMap;
    public static Well well;
    public static TimeUnit date;
    public static Storage storage;
    public static Truck truck;
    public static int wildAnimalAppearanceTime;
    public static ArrayList<User> users;
    public static User user;
    public static Level level;
    public static int Level;
    public static boolean gameover;
    public static ArrayList<Integer> taskproductsamount;
    public static UserFile userfile;

    public GameProcessor() {
        date = new TimeUnit(1);
        allCreatures = new ArrayList<>();
        factories = new ArrayList<>();
        gameMap = new Map();
        well = new Well();
        storage = new Storage();
        truck = new Truck();
        wildAnimalAppearanceTime = 2;
        users = new ArrayList<>();
        gameover = false;
        taskproductsamount = new ArrayList<>();
        try {
            userfile = new UserFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //////
        gameMap.productions[0][0].add(new Egg());
        gameMap.productions[0][0].add(new Egg());
        gameMap.productions[0][0].add(new Flour());
        gameMap.productions[0][0].add(new Flour());
        gameMap.productions[0][0].add(new IceCream());
        gameMap.productions[0][0].add(new Bread());
        gameMap.productions[0][0].add(new Cloth());
        gameMap.productions[0][0].add(new Feather());
        gameMap.productions[0][0].add(new Milk());
        gameMap.productions[0][0].add(new PasteurizedMilk());
        gameMap.productions[0][0].add(new Shirt());


        gameMap.creature[0][0].add(new Bear());
        gameMap.creature[0][0].add(new Buffalo());
        gameMap.creature[0][0].add(new Cat());
        gameMap.creature[0][0].add(new Hen());
        gameMap.creature[0][0].add(new Hound());
        gameMap.creature[0][0].add(new Lion());
        gameMap.creature[0][0].add(new Turkey());
        gameMap.creature[0][0].add(new Tiger());
        WildAnimals wildAnimals = new Tiger();
        wildAnimals.isCaged = true;
        gameMap.creature[0][0].add(wildAnimals);
        factories.add(new Mill());
        factories.add(new Bakery());
        factories.add(new IceCreamFactory());
        factories.add(new MilkPackingFactory());
        factories.add(new TailorShop());
        //factories.add(new TextileFactory());
    }

    public static void addCoin(int n){coins += n;};

    public static void reduceCoin(int n){coins -= n;};

    public static boolean checkEnoughCoin(int n){
        return n <= GameProcessor.coins;
    }

    public static void buyAnimal(String animalName){

        //Buy Hound
        if(animalName.equalsIgnoreCase("hound")){
            AliveCreature hound = new Hound();
            if(!checkEnoughCoin(hound.getPrice())){
                OutputProcessor.notEnoughCoin();
                return;
            }

            reduceCoin(hound.getPrice());
            allCreatures.add(hound);
            gameMap.creature[hound.getPosition().getY()][hound.getPosition().getX()].add(hound);
            OutputProcessor.animalBuySuccessful(animalName, hound.getPosition().getX(), hound.getPosition().getY());
            return;
        }


        //Buy Cat
        if(animalName.equalsIgnoreCase("cat")){
            AliveCreature cat = new Cat();
            if(!checkEnoughCoin(cat.getPrice())){
                OutputProcessor.notEnoughCoin();
                return;
            }

            reduceCoin(cat.getPrice());
            allCreatures.add(cat);
            gameMap.creature[cat.getPosition().getY()][cat.getPosition().getX()].add(cat);
            OutputProcessor.animalBuySuccessful(animalName, cat.getPosition().getX(), cat.getPosition().getY());
            return;
        }


        //Buy Domestic Animal
        int domesticType = DomesticAnimals.isDomesticAnimal(animalName);

        if(domesticType == -1){
            OutputProcessor.animalNotFound();
            return;
        }

        DomesticAnimals domesticAnimals = null;

        switch (domesticType) {
            case 0 : {
                domesticAnimals = new Hen();
                break;
            }
            case 1 : {
                domesticAnimals = new Turkey();
                break;
            }
            case 2 : {
                domesticAnimals = new Buffalo();
                break;
            }
        }

        if(!checkEnoughCoin(domesticAnimals.getPrice())){
            OutputProcessor.notEnoughCoin();
            return;
        }

        reduceCoin(domesticAnimals.getPrice());
        allCreatures.add(domesticAnimals);
        gameMap.creature[domesticAnimals.getPosition().getY()][domesticAnimals.getPosition().getX()].add(domesticAnimals);
        OutputProcessor.animalBuySuccessful(animalName, domesticAnimals.getPosition().getX(), domesticAnimals.getPosition().getY());
    }

     public static void fillWell(){
        switch (well.checkWellRequest()){
            case 1:
                OutputProcessor.wellIsBusy();
                return;
            case 2:
                OutputProcessor.wellNotEmpty();
                return;
        }
        well.drainWater();
        OutputProcessor.wellCommandSuccessful();
    }

    public static void plant(int row, int col){

        if(!Position.isCoordinateValid(row, col)){
            OutputProcessor.invalidCoordinates();
            return;
        }

        if(!well.getWater()){
            OutputProcessor.wellEmpty();
            return;
        }
        gameMap.grass[col][row]++;
        gameMap.setGrasses();
        OutputProcessor.plantSuccessfully();
    }

    public static void update(){
        well.updateWell();
        gameMap.updateMap();
        storage.checkWildAnimalEscape();
        truck.update();
        AliveCreature.updateAliveCreatures();
        WildAnimals.generateWildAnimal();
        Factory.updateAllFactories();
        if (date.getUnit() >= level.getMaxtime()) {
            gameover = true;
        }
        handleLevel();
        //TODO
        //Everything needs to be updated
    }

    public static void turnCommand(){ // just one turn
        int n = 1;

        for(int i = 0; i < n; i++)
        {
           if(!gameover) {
                date.turn();
                update();
            }

           else{
               OutputProcessor.gameOver();
               InputProcessor.setStart(false);
               return;
           }
        }
        OutputProcessor.inquiry();
        HandleLogger.printInfo("Turn to Date " + date.getUnit() + ".");
    }



    //***************************************//TODO
    public static void pickupAnimal(String name, int r, int c){
        ArrayList<AliveCreature> creatures = gameMap.creature[r][c];
        String animalName = name.substring(0, name.length() - 1);

        int cagedAnimalIndex = -1;
        for (AliveCreature creature : creatures) {
            if(creature instanceof WildAnimals && ((WildAnimals) creature).isCaged() && creature.getName().equalsIgnoreCase(animalName)){
                cagedAnimalIndex = creatures.indexOf(creature);
                break;
            }
        }

        if(cagedAnimalIndex != -1){
            if(((WildAnimals) creatures.get(cagedAnimalIndex)).pickUp()){
                allCreatures.remove(creatures.get(cagedAnimalIndex));
                creatures.remove(cagedAnimalIndex);
                handleLevel();
                OutputProcessor.pickUpSuccessfully();
                return;
            }
        }
        OutputProcessor.notEnoughStorage();
    }

    public static void pickupProduct(String name, int r, int c){
        ArrayList<Product> products = gameMap.productions[r][c];
        for (Product product : products) {
            if(product.getName().equalsIgnoreCase(name) && product.pickUp()){
                products.remove(products.indexOf(product));
                handleLevel();
                OutputProcessor.pickUpSuccessfully();
                return;
            }

        }
        OutputProcessor.notEnoughStorage();
    }

    public static void pickUp(String command){
        Matcher matcher = InputCommands.PICKUP.getmatcher(command);
        matcher.matches();

        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));

        if(!Position.isCoordinateValid(x, y)){
            OutputProcessor.invalidCoordinates();
            return;
        }

        ArrayList<Product> products = gameMap.productions[y][x];
        ArrayList<AliveCreature> creatures = gameMap.creature[y][x];

        //Pickup Caged Animals
        int cagedAnimalIndex = -1;
        for (AliveCreature creature : creatures) {
            if(creature instanceof WildAnimals && ((WildAnimals) creature).isCaged()){
                cagedAnimalIndex = creatures.indexOf(creature);
                break;
            }
        }

        if(cagedAnimalIndex != -1){
            if(((WildAnimals) creatures.get(cagedAnimalIndex)).pickUp()){
                allCreatures.remove(creatures.get(cagedAnimalIndex));
                creatures.remove(cagedAnimalIndex);
                handleLevel();
                OutputProcessor.pickUpSuccessfully();
                return;
            }
        }

        //Pickup Product
        if(products.isEmpty()){
            OutputProcessor.noProductFound();
            return;
        }

        if(!products.get(0).pickUp()){
            OutputProcessor.notEnoughStorage();
            return;
        }
        products.remove(0);
        handleLevel();
        OutputProcessor.pickUpSuccessfully();
    }

    public static void cage(String name, int r, int c){
        ArrayList<AliveCreature> positionCreatures = gameMap.creature[r][c];
        String animalName = name;
        if(name.charAt(name.length() - 1) == '*')
            animalName = name.substring(0, name.length() - 1);


        WildAnimals wildAnimal = null;
        for (AliveCreature positionCreature : positionCreatures) {
            if(positionCreature.getName().equals(animalName)) {
                wildAnimal = (WildAnimals) positionCreature;
                break;
            }
        }


        assert wildAnimal != null;
        if(wildAnimal.isCageCommandValid()){

            wildAnimal.addCageCommand();

            if(!wildAnimal.isCagingComplete()){
                OutputProcessor.cageCommandSuccessful(wildAnimal.calculateRemainingCageCommands());
                return;
            }

            wildAnimal.cage();
            OutputProcessor.cageCompleted();
            return;
        }
        OutputProcessor.invalidCageCommand();
    }

    public static void loadTruckWildAnimal(String name){
        String animalName = name.substring(0, name.length() - 1);

        //for wild animals
        if(WildAnimals.isWildAnimal(animalName) != -1){
            WildAnimals wildAnimal = storage.loadWildToTruck(animalName);

            if(!truck.checkAvailableSpace()){
                OutputProcessor.notEnoughStorage();
                return;
            }

            if(truck.isWorking()){
                OutputProcessor.truckIsWorking();
                return;
            }

            truck.load(wildAnimal);
            OutputProcessor.wildLoadedSuccessfully();
        }
    }

    public static void loadTruckProduct(String name){
        //for products
        Product product = storage.loadToTruck(name);

        if(product == null){
            OutputProcessor.productNotStored();
            return;
        }

        if(!truck.checkAvailableSpace(product)){
            OutputProcessor.notEnoughStorage();
            return;
        }

        if(truck.isWorking()){
            OutputProcessor.truckIsWorking();
            return;
        }

        truck.load(product);
        handleLevel();
        OutputProcessor.productLoadedSuccessfully();
    }

    public static void unloadTruckWildAnimal(String editedName){
        String name = editedName.substring(0, editedName.length() - 1);
        int index = -1;

        //for wild animals

        for (WildAnimals wildAnimal : truck.getWildAnimals()) {
            if(wildAnimal.getName().equalsIgnoreCase(name)){
                index = truck.getWildAnimals().indexOf(wildAnimal);
                break;
            }
        }
        WildAnimals unloadingAnimal = truck.getWildAnimals().get(index);

        if(!storage.checkAvailableSpace()){
            OutputProcessor.notEnoughStorage();
            return;
        }

        if(truck.isWorking()){
            OutputProcessor.truckIsWorking();
            return;
        }

        truck.unLoadAnimal(unloadingAnimal);
        OutputProcessor.unloadedSuccessfully();
    }


    public static void unloadTruckProduct(String name){
        int index = -1;
        //for products
        for (Product storedProduct : truck.getStoredProducts()) {
            if(storedProduct.getName().equalsIgnoreCase(name)){
                index = truck.getStoredProducts().indexOf(storedProduct);
                break;
            }
        }

        Product unloadingProduct = truck.getStoredProducts().get(index);

        if(!storage.checkAvailableSpace(unloadingProduct)){
            OutputProcessor.notEnoughStorage();
            return;
        }

        if(truck.isWorking()){
            OutputProcessor.truckIsWorking();
            return;
        }

        truck.unLoad(unloadingProduct);
        handleLevel();
        OutputProcessor.unloadedSuccessfully();
    }

    public static void goTruck(){
        if(truck.getWildAnimals().isEmpty() && truck.getStoredProducts().isEmpty()){
            OutputProcessor.truckEmpty();
            return;
        }
        if(truck.isWorking()){
            OutputProcessor.truckIsWorking();
            return;
        }

        truck.go();
        OutputProcessor.truckGone(truck.getSELLING_TIME());
    }



    public static void buildFactory(String name){

        int factoryType = Factory.getFactoryType(name);

        if(factoryType == -1){
            OutputProcessor.factoryNotFound();
            return;
        }

        if(Factory.checkFactoryExist(name)){
            OutputProcessor.factoryExists();
            return;
        }

        Factory factory = null;

        switch (factoryType) {
            case 0 :
                factory = new MilkPackingFactory();
                break;
            case 1 :
                factory = new Mill();
                break;
            case 2 :
                factory = new TextileFactory();
                break;
            case 3 :
                factory = new Bakery();
                break;
            case 4 :
                factory = new TailorShop();
                break;
            case 5 :
                factory = new IceCreamFactory();
                break;
        }

        if(!checkEnoughCoin(factory.getPrice())){
            OutputProcessor.notEnoughCoin();
            return;
        }

        factories.add(factory);
        reduceCoin(factory.getPrice());
        OutputProcessor.factoryBuild();
    }

    public static void factoryWork(Factory factory){
        int productIndex = factory.checkInputExists();

        if(!factory.isWorkingPossible()){
            OutputProcessor.factoryIsBusy();
            return;
        }


        if(productIndex == -1){
            OutputProcessor.inputNotExist();
            return;
        }

        factory.startWorking(productIndex);
        handleLevel();
        OutputProcessor.factoryStart(factory.getProduceTime());
    }

    public static void upgradeFactory(Factory factory){
        if(!factory.isUpgradePossible()){
            OutputProcessor.upgradeNotPossible();
            return;
        }

        factory.upgradeSpeed();

//        else if(upgradeType == 2)
//            factory.upgradeMultiTask();

        OutputProcessor.upgradeSuccessful();
    }

    public static void invalidCommand() {
        OutputProcessor.invalidCommand();
    }

    public static void invalidUsername() {
        OutputProcessor.invalidUsername();
    }

    public static void repetitiousUsername() {
        OutputProcessor.repititiousUsername();
    }

    public static void invalidPassword() {
        OutputProcessor.invalidPassword();
    }

    public static boolean checkUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPassword(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(username) && users.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static User giveUser(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(username) && users.get(i).getPassword().equals(password)) {
                return users.get(i);
            }
        }
        return null;
    }

    public static void signupSuccessful() {
        OutputProcessor.signupSuccessful();
    }

    public static void loginSuccessful() {
        OutputProcessor.loginSuccessful();
    }

    public static void logoutSuccessful() {
        OutputProcessor.logoutSuccessful();
    }

    public static void setLevel(int levelnum) {
        level = new Level(levelnum);
    }

    public static void levelIsLocked() {
        OutputProcessor.levelIsLocked();
    }

    public static void startLevel() {
        coins = level.getInitial_coin() + user.getCoins();
        storage = new Storage();
        allCreatures = new ArrayList<>();
        factories = new ArrayList<>();
        gameMap = new Map();
        well = new Well();
        date = new TimeUnit();
        truck = new Truck();
        taskproductsamount = new ArrayList<>();
        wildAnimalAppearanceTime = level.getWildanimalsappearanceperiod();
        OutputProcessor.startLevel(Level);
    }

    public static void startLeveli() {
        coins = level.getInitial_coin() + user.getCoins();
        storage = new Storage();
        allCreatures = new ArrayList<>();
        factories = new ArrayList<>();
        gameMap = new Map();
        well = new Well();
        date = new TimeUnit();
        truck = new Truck();
        taskproductsamount = new ArrayList<>();
        wildAnimalAppearanceTime = level.getWildanimalsappearanceperiod();
        gameover = false;
        OutputProcessor.startLevel(Level);
    }

    public static void handleLevel() {
        taskproductsamount = new ArrayList<>();
        boolean levelpassed = true;
        if (coins < level.getTask().getCoin_amount_goal()){
            levelpassed = false;
        }
        for (int i = 0; i < level.getTask().getProductsname().size(); i++)
            for (int j = 0; j < storage.getProductsname().size(); j++) {
                if (level.getTask().getProductsname().get(i).equals(storage.getProductsname().get(j))) {
                    taskproductsamount.add(countProducts(storage.getProductsname().get(j)));
                    if (countProducts(storage.getProductsname().get(j)) < level.getTask().getAmounts().get(i)) {
                        levelpassed = false;
                    }
                }
            }

        if(!levelpassed)
            return;

        if (date.getUnit() <= level.getRewardtime()) {
            user.setCoins(level.getReward_coin());
        }
        goNextLevel();
        userfile.updateUsers();
        userfile.writeUsersInFile(users);
        startLevel();

    }

    public static int countProducts(String productname){
        int amount = 0;
        for (int i = 0; i < storage.getProducts().size(); i++) {
            if (storage.getProducts().get(i).getName().equalsIgnoreCase(productname)) {
                amount ++;
            }
        }
        return amount;
    }

    public static void goNextLevel() {
        if (Level == user.getLevelpassed()) {
            user.goNextLevel();
        }
        Level ++;
        setLevel(Level);
    }

    public static void gameOver() {

    }

    public static void printGameOver() {
        OutputProcessor.gameOver();
    }

}
