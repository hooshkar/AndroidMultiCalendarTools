package com.ali.uneversaldatetools.date;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ali on 9/30/18.
 */

public class TimeZoneHelper {

    public static TimeZone getSystemTimeZone() {
        return TimeZone.getDefault();
    }

    public static int getSystemOffsetSeconds() {
        return (getSystemTimeZone().getOffset(new Date().getTime()) / 1000 / 60) * 60;
    }

    public static int ToSeconds(TimeZone timeZone){
        return (timeZone.getOffset(new Date().getTime()) / 1000 / 60) * 60;
    }
}
