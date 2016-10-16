package com.lgb.arc.utils;

import org.joda.time.DateTime;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static int getNowWeek() {
        Calendar cal = Calendar.getInstance();

        cal.setTime(DateTime.now().toDate());

        int week = cal.get(Calendar.DAY_OF_WEEK);

        if (week == 1) {
            week = 7;
        } else {
            week = week - 1;
        }

        return week;
    }

    public static Date dataFromStringToDate(String date) {
        String newDate = date.substring(0,4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(sdf.parse(newDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int nowYear() {
        DateTime time = new DateTime();

        return time.year().get();
    }
}
