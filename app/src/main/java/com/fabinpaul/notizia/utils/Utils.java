package com.fabinpaul.notizia.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    private Utils() {

    }

    public static String getTimeInHrSince(String time, String srcFormat) throws ParseException {
        DateFormat format = new SimpleDateFormat(srcFormat, Locale.ENGLISH);
        Date srcDate = format.parse(time);
        long timeDiffInMilli = System.currentTimeMillis() - srcDate.getTime();
        return String.valueOf((timeDiffInMilli / 3600000));
    }
}
