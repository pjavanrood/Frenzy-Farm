package view;


import controller.GameProcessor;
import controller.logger.HandleLogger;

import java.util.Scanner;

public class InputProcessor {

    boolean login;
    static boolean start;
    GameProcessor gameProcessor;
    Scanner scanner;
    HandleLogger handleLogger = new HandleLogger();

    public InputProcessor(GameProcessor gameProcessor) {
        this.gameProcessor = gameProcessor;
        this.scanner = new Scanner(System.in);
        login = false;
        start = false;
    }

    public static void setStart(boolean bool) {
        start = bool;
    }

    public void run () {

        String input;

        while (!InputCommands.EXIT.getmatcher(input = scanner.nextLine()).matches()) {
            boolean isCommandValid = false;

            //handle login
            if(!login && (InputCommands.SIGNUP.getmatcher(input).matches() || InputCommands.LOG_IN.getmatcher(input).matches())){
                login = handleLogIn(input);
                isCommandValid = true;
            }

            //handle start
            if (!start && login) {
                if (InputCommands.START.getmatcher(input).matches()) {
                    isCommandValid = true;
                    int requestLevel = Integer.parseInt(input.split("\\s")[1]);
                    if(requestLevel <= GameProcessor.user.getLevelpassed()){
                        start = true;
                        GameProcessor.setLevel(requestLevel);
                        GameProcessor.Level = requestLevel;
                        GameProcessor.startLeveli();
                        continue;
                    } else {
                        GameProcessor.levelIsLocked();
                    }
                }
            }

            //handle logout
            if (InputCommands.LOG_OUT.getmatcher(input).matches()) {
                login = false;
                start = false;
                GameProcessor.user = null;
                isCommandValid = true;
                GameProcessor.logoutSuccessful();
            }

            //handle game commands
            else if (start && login){
                if (InputCommands.BUY.getmatcher(input).matches()) {
                    GameProcessor.buyAnimal(input);
                } else if (InputCommands.PICKUP.getmatcher(input).matches()) {
                    GameProcessor.pickUp(input);
                } else if (InputCommands.WELL.getmatcher(input).matches()) {
                    GameProcessor.fillWell();
                } else if (InputCommands.PLANT.getmatcher(input).matches()) {
                    /*GameProcessor.plant();*/
                } else if (InputCommands.BUILD.getmatcher(input).matches()) {
                    GameProcessor.buildFactory(input);
                } else if (InputCommands.WORK.getmatcher(input).matches()) {
                    //GameProcessor.factoryWork(input);
                } else if (InputCommands.CAGE.getmatcher(input).matches()) {
                    //controller.GameProcessor.cage(input);
                } else if (InputCommands.TURN.getmatcher(input).matches()) {
                    GameProcessor.turnCommand();
                } else if (InputCommands.TRUCK_LOAD.getmatcher(input).matches()) {
                    //GameProcessor.loadTruck(input);
                } else if (InputCommands.TRUCK_UNLOAD.getmatcher(input).matches()) {
                    //GameProcessor.unloadTruck(input);
                } else if (InputCommands.TRUCK_GO.getmatcher(input).matches()) {
                    GameProcessor.goTruck();
                } else if (InputCommands.INQUIRY.getmatcher(input).matches()) {
                    //OutputProcessor.inquiry();
                } else if (InputCommands.UPGRADE.getmatcher(input).matches()) {
                    //GameProcessor.upgradeFactory(input);
                } else {
                    OutputProcessor.invalidCommand();
                }
                continue;
            }


            //handle invalid command
            if(!isCommandValid)
                OutputProcessor.invalidCommand();

        }
        if (GameProcessor.gameover) {
            start = false;
        }
    }

    public static boolean handleLogIn(String input){
        boolean login = false;
        GameProcessor.userfile.readUsersFromFile();
        if (InputCommands.SIGNUP.getmatcher(input).matches()) {
            String username = input.split("\\s")[2];
            String password = input.split("\\s")[4];
            if (GameProcessor.checkUsername(username)) {
                GameProcessor.repetitiousUsername();
            } else {
                GameProcessor.userfile.addUser(username, password);
                GameProcessor.userfile.addUserToUsers(username, password);
                GameProcessor.signupSuccessful();
                login = true;
                GameProcessor.userfile.writeUsersInFile(GameProcessor.users);
            }
        } else if (InputCommands.LOG_IN.getmatcher(input).matches()) {
            String username = input.split("\\s")[2];
            String password = input.split("\\s")[4];
            if (GameProcessor.checkUsername(username)) {
                if (GameProcessor.checkPassword(username, password)) {
                    GameProcessor.userfile.addUser(GameProcessor.giveUser(username, password));
                    GameProcessor.loginSuccessful();
                    login = true;
                } else {
                    GameProcessor.invalidPassword();
                }
            } else {
                GameProcessor.invalidUsername();
            }
        } else {
            GameProcessor.invalidCommand();
        }

        return login;
    }
}
