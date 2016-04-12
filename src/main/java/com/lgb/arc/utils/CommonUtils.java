package com.lgb.arc.utils;

public class CommonUtils {
    public static int convertTimeSpecific(String specific) {
        if (specific.equals("a")) {
            return 1;
        } else if (specific.equals("b")) {
            return 2;
        } else if (specific.equals("c")) {
            return 3;
        } else if (specific.equals("d")) {
            return 4;
        } else if (specific.equals("e")) {
            return 5;
        }

        return 0;
    }
}
