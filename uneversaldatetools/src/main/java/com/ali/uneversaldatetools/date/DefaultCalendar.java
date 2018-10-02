package com.ali.uneversaldatetools.date;

/**
 * Created by ali on 9/30/18.
 */

public class DefaultCalendar {

    private static Calendar mDefaultCalendar;

    public static Calendar getDefaultCalendar() {
        return mDefaultCalendar;
    }

    public static void setDefaultCalendar(Calendar DefaultCalendar) {
        mDefaultCalendar = DefaultCalendar;
    }
}
