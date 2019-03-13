package com.ali.uneversaldatetools.model;

import com.ali.uneversaldatetools.date.DayOfWeek;

/**
 * Created by ali on 9/9/18.
 */

public class Month {

    private int pos; // 1 -> 12
    private String name;
    private int daysCount; //28 , 29 , 30 , 31
    private DayOfWeek startAt;

    public Month(int pos, String name, int daysCount, DayOfWeek startAt) {
        this.pos = pos;
        this.name = name;
        this.daysCount = daysCount;
        this.startAt = startAt;
    }

    public int getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public DayOfWeek getStartAt() {
        return startAt;
    }

}
