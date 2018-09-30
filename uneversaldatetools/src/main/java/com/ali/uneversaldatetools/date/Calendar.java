package com.ali.uneversaldatetools.date;

/**
 * Created by ali on 9/5/18.
 */

public enum Calendar {
    None(0),
    Gregorian(1),
    Jalali(2),
    Hijri(3);

    private final int value;

    Calendar(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Calendar ToCalender(int num) {
        switch (num) {
            case 0:
                return None;
            case 1:
                return Gregorian;
            case 2:
                return Jalali;
            case 3:
                return Hijri;
        }
        return null;
    }
}
