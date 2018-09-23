package com.ali.uneversaldatetools.tools;

import com.ali.uneversaldatetools.model.DateModel;

import java.util.Date;

/**
 * Created by ali on 9/11/18.
 */

public class DateTools {

    public static DateModel getCurrentDate() {
        Date date = new Date();
        date.setYear(date.getYear() + 1900);
        date.setMonth(date.getMonth() + 1);  // change index to 1
        return new DateModel(date.getYear(), date.getMonth(), date.getDate());
    }
}
