package com.pirates.choi.hiswindtester.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {

    public static String getCurrentDate(){
        long now = System.currentTimeMillis();
        Date partsDate = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        final String currentDate = sdf.format(partsDate);
        return currentDate;
    }

}
