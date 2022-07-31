package controller.logger;

import controller.GameProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HandleLogger {

    public static boolean printheader = false;
    public static boolean clearlog = false;

    public static void clearTheFile() {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter("log.txt", false);
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

    public static void printError(String input) {
        if (!clearlog) {
            clearTheFile();
            clearlog = true;
        }
        if (!printheader) {
            Header.printHeader();
            printheader = true;
        }
        LocalDateTime dateandtime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss");
        String formattedDate = dateandtime.format(myFormatObj);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String error = "[Error],";
        if (GameProcessor.user == null) {
            error += "Free User";
        } else {
            error += GameProcessor.user.getName();
        }
        error += ",";
        error += formattedDate;
        error += ",";
        error += input;
        error += "\n";
        writer.append(error);
        writer.close();
    }

    public static void printInfo(String input) {
        if (!clearlog) {
            clearTheFile();
            clearlog = true;
        }
        if (!printheader) {
            Header.printHeader();
            printheader = true;
        }
        LocalDateTime dateandtime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss");
        String formattedDate = dateandtime.format(myFormatObj);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String info = "[Info],";
        if (GameProcessor.user == null) {
            info += "Free User";
        } else {
            info += GameProcessor.user.getName();
        }
        info += ",";
        info += formattedDate;
        info += ",";
        info += input;
        info += "\n";
        writer.append(info);
        writer.close();
    }

    public static void printAlarm(String input) {
        if (!clearlog) {
            clearTheFile();
            clearlog = true;
        }
        if (!printheader) {
            Header.printHeader();
            printheader = true;
        }
        LocalDateTime dateandtime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss");
        String formattedDate = dateandtime.format(myFormatObj);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String alarm = "[Alarm],";
        if (GameProcessor.user == null) {
            alarm += "Free User";
        } else {
            alarm += GameProcessor.user.getName();
        }
        alarm += ",";
        alarm += formattedDate;
        alarm += ",";
        alarm += input;
        alarm += "\n";
        writer.append(alarm);
        writer.close();
    }

}
