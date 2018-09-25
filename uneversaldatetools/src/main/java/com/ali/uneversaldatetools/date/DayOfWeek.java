package com.ali.uneversaldatetools.date;

/**
 * Created by ali on 9/5/18.
 */

public enum DayOfWeek {

    Saturday(0),
    SunDay(1),
    Monday(2),
    Tuesday(3),
    Wednesday(4),
    Thursday(5),
    Friday(6);

    private final int value;
    DayOfWeek(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DayOfWeek ToDayOfWeek(int num){
        switch (num){
            case 0: return Saturday;
            case 1: return SunDay;
            case 2: return Monday;
            case 3: return Tuesday;
            case 4: return Wednesday;
            case 5: return Thursday;
            case 6: return Friday;
        }
        return null;
    }
}
