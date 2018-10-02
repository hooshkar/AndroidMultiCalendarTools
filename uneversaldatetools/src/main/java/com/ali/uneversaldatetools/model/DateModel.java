package com.ali.uneversaldatetools.model;

/**
 * Created by ali on 9/5/18.
 */

public class DateModel  {

    public int year;
    public int month;
    public int day;
    public int hour;
    public int min;
    public int sec;

    public DateModel(){}

    public DateModel(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public DateModel(int year, int month, int day, int hour, int min, int sec) {
        this(year, month, day);
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }
}