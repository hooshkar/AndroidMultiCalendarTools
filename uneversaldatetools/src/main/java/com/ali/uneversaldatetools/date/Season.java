package com.ali.uneversaldatetools.date;

/**
 * Created by ali on 9/5/18.
 */

public enum Season {
    Spring(1),
    Summer(2),
    Autumn(3),
    Winter(4);

    private final int value;
    Season(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
