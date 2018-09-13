package com.ali.uneversaldatetools.model;

import com.ali.uneversaldatetools.date.DayOfWeek;

/**
 * Created by ali on 9/9/18.
 */

public class Month {

    private String name;
    private int daysCount; // 29 , 30 , 31
    private DayOfWeek startAt;

    public Month(String name, int daysCount, DayOfWeek startAt) {
        this.name = name;
        this.daysCount = daysCount;
        this.startAt = startAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        if (daysCount == 28 | daysCount == 29 | daysCount == 30 | daysCount == 31)
            this.daysCount = daysCount;
        else
            throw new IllegalArgumentException("month count is not valid");
    }

    public DayOfWeek getStartAt() {
        return startAt;
    }

    public void setStartAt(DayOfWeek startAt) {
        this.startAt = startAt;
    }
}
