package controller.userprocessor;

import controller.GameProcessor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserFile {

    public UserFile() throws IOException {
    }

    public static void readUsersFromFile() {
        GameProcessor.users = GameProcessor.userfile.readFromFile();
    }

    public static void addUser(String username, String password) {
        GameProcessor.user = new User(username, password);
    }

    public static void addUser(User user) {
        GameProcessor.user = user;
    }

    public static void addUserToUsers(String username, String password) {
        User user = new User(username, password);
        GameProcessor.users.add(user);
    }


    public static void writeUsersInFile(ArrayList<User> users){
        clearTheFile();
        for (int i = 0; i < users.size(); i++) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileWriter("users.txt", true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.append("Username: " + users.get(i).getName() + " " + "Password: " + users.get(i).getPassword()
                    + " " + "Level: " + users.get(i).getLevelpassed() + " " + "coins: " + users.get(i).getCoins() + "\n");
            writer.close();
        }
    }

    public ArrayList<User> readFromFile(){
        ArrayList<User> users = new ArrayList<>();
        if (countLineNumberReader() != 0) {
            for (int i = 0; i < countLineNumberReader(); i++) {

                try {
                    String[] userstring = Files.readAllLines(Paths.get("users.txt")).get(i).split("\\s");
                    users.add(new User(userstring[1], userstring[3], Integer.parseInt(userstring[5]), Integer.parseInt(userstring[7])));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    public static int countLineNumberReader() {
        File file = new File("users.txt");
        int lines = 0;
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {
            while (lnr.readLine() != null) ;
            lines = lnr.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void clearTheFile() {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter("users.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
            fwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUsers() {
        for (int i = 0; i < GameProcessor.users.size(); i++) {
            if (GameProcessor.users.get(i).getName().equals(GameProcessor.user.getName())) {
                GameProcessor.users.remove(i);
                GameProcessor.users.add(GameProcessor.user);
            }
        }
    }
}
