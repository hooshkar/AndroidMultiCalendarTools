package com.ali.uneversaldatetools.tools;

import java.util.Date;

/**
 * Created by ali on 9/11/18.
 */

public class DateTools {

//    public static int getCurrentDays() {
//        Long currentMilSeconds = new Date().getTime();
//        //1 day = 86400000 ms
//        // x    =  cms
//        //so :
//        return (int) (currentMilSeconds / 86400000);
//    }

    public static Date getCurrentDate() {
        Date date = new Date();
        date.setYear(date.getYear() + 1900); //
        date.setMonth(date.getMonth() + 1);  // change index to 1
        return date;
    }
}
