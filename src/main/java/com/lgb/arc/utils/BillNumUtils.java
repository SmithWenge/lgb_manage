package com.lgb.arc.utils;

import org.joda.time.DateTime;

public class BillNumUtils {

    public static String createBillNum() {
        String dateString = new DateTime().toString("YYYYMMddHHmmssSS");
        return dateString;
    }
}
