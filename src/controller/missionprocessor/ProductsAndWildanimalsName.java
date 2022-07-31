package controller.missionprocessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProductsAndWildanimalsName {

    PRODUCTS("(?i)(bread)||(cloth)||(egg)||(feather)||(flour)||(icecream)||(milk)||(pasteurizedmilk)||(shirt)"),
    WILDANIMALS("(?i)(tiger)||(lion)||(bear)");

    private final Pattern commandPattern;

    ProductsAndWildanimalsName(String s) {
        this.commandPattern = Pattern.compile(s);
    }

    public Matcher getmatcher(String s) {
        return this.commandPattern.matcher(s);
    }
}
