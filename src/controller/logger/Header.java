package controller.logger;

import controller.GameProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Header {

    public static void printHeader() {
        LocalDateTime dateandtime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss");
        String formattedDate = dateandtime.format(myFormatObj);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String header = "";
        header += "Generating date & time: ";
        header += formattedDate;
        header += "\n";
        header += "User: ";
        if (GameProcessor.user == null) {
            header += "Free User";
        } else {
            header += GameProcessor.user.getName();
        }
        header += "\n";
        writer.append(header);
        writer.close();
    }

}
