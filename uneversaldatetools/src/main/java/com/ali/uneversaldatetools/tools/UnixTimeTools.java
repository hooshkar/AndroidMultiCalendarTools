package com.ali.uneversaldatetools.tools;

/**
 * Created by ali on 9/11/18.
 */

public class UnixTimeTools {

    public static int getCurrentUnixTime() {
        return (int) (System.currentTimeMillis() / 1000L); //toSec
    }
}
