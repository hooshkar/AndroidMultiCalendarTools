package com.ali.uneversaldatetools.date;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ali on 9/30/18.
 */

public class TimeZoneHelper {

    public static TimeZone getCurrentTimeZone() {
        return TimeZone.getDefault();
    }

    /**
     * @return in minute
     */
    public static int getCurrentTimeZoneOffset() {
        return getCurrentTimeZone().getOffset(new Date().getTime()) / 1000 / 60;   //210 minute for iran
    }

    /**
     * @return in second
     */
    public static int getCurrentTimeZoneOffsetSec() {
        return (getCurrentTimeZoneOffset()) * 60;   //to second
    }
}
