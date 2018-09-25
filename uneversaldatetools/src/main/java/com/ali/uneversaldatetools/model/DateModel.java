package com.ali.uneversaldatetools.model;

import android.support.annotation.NonNull;

import com.ali.uneversaldatetools.date.IDate;

/**
 * Created by ali on 9/5/18.
 */

public class DateModel implements Comparable<IDate> {

    public int year;
    public int month;
    public int day;
    private int hour;
    private int min;
    private int sec;

    public DateModel(){
        setYear(0);
        setMonth(1);
        setDay(1);
        setHour(0);
        setMin(0);
        setSec(0);
    }

    public DateModel(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(0);
        setMin(0);
        setSec(0);
    }

    public DateModel(int year, int month, int day, int hour, int min, int sec) {
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(hour);
        setMin(min);
        setSec(sec);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 1 | month > 12)
            throw new IllegalArgumentException("invalid month: " + String.valueOf(month));
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day == 0) throw new IllegalArgumentException("invalid month: " + String.valueOf(day));
        this.day = day;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (hour < 0 | hour > 24) throw new IllegalArgumentException("");
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    @Override
    public int compareTo(@NonNull IDate o) {
        throw new RuntimeException("todo write here -> DateModel.compareTo");
    }
}