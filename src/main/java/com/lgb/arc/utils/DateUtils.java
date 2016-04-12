package com.lgb.arc.utils;

import org.joda.time.DateTime;

import java.util.Calendar;

public class DateUtils {
    public static int getNowWeek() {
        Calendar cal = Calendar.getInstance();

        cal.setTime(DateTime.now().toDate());

        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

        return week;
    }
}
