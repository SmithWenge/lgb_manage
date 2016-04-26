package com.lgb.arc.utils;

import org.joda.time.DateTime;

public class BillNumUtils {

    public static String creatBillNum() {
        String dateString = new DateTime().toString("MMddHHmmssSS");
        return dateString;
    }
}
