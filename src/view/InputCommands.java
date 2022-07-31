package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputCommands {

    SIGNUP("(?i)\\s*signup\\s+username\\s+(\\w+)\\s+password\\s+(\\d{8})\\s*"),
    LOG_IN("(?i)\\s*login\\s+username\\s+(\\w+)\\s+password\\s+(\\d{8})\\s*"),
    START("(?i)\\s*start\\s+[1-9]\\d*\\s*"),
    LOG_OUT("(?i)\\s*log\\s*out\\s*"),
    EXIT("(?i)\\s*exit\\s*"),
    BUY("(?i)\\s*buy\\s*(\\w+)\\s*"),
    PICKUP("(?i)\\s*pickup\\s+(\\d+)\\s+(\\d+)\\s*"),
    WELL("(?i)\\s*well\\s*"),
    PLANT("(?i)\\s*plant\\s+(\\d+)\\s+(\\d+)\\s*"),
    BUILD("(?i)\\s*build\\s+((\\w+\\s*)*)\\s*"),
    WORK("(?i)\\s*work\\s+((\\w+\\s*)*)\\s*"),
    CAGE("(?i)\\s*cage\\s+(\\d+)\\s+(\\d+)\\s*"),
    TURN("(?i)\\s*turn\\s+(\\d+)\\s*"),
    TRUCK_LOAD("(?i)\\s*truck\\s+load\\s+((\\w+\\s*)*)\\s*"),
    TRUCK_UNLOAD("(?i)\\s*truck\\s+unload\\s+((\\w+\\s*)*)\\s*"),
    TRUCK_GO("(?i)\\s*truck\\s+go\\s*"),
    INQUIRY("(?i)\\s*inquiry\\s*"),
    UPGRADE("(?i)\\s*upgrade\\s+(.+)\\s+-\\s+(\\w+)\\s*"),
    ANYTHING("(?i)\\.+");

    private final Pattern commandPattern;

    InputCommands(String s) {
        this.commandPattern = Pattern.compile(s);
    }

    public Matcher getmatcher(String s){
        return this.commandPattern.matcher(s);
    }
}
