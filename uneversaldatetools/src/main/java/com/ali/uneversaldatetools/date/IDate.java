package com.ali.uneversaldatetools.date;

import java.util.Date;

/**
 * Created by ali on 9/5/18.
 */

public interface IDate {

    int getYear();
    int getMonth();
    int getDay();
    int getDays();
    int getDaysOfMonth();
    Date getDate();
    JalaliDateTime getHDateTime();
    GregorianDateTime getMDateTime();
    HijriDateTime getGDateTime();
    Date getFirstDayOfYear();
    Date getLastDayOfYear ();
    Date getFirstDayOfMonth ();
    Date getLastDayOfMonth ();
    Date FirstDayOfSeason(Season season);
    Date LastDayOfSeason(Season season);
    Season getSeason();
    DayOfWeek getDayOfWeek ();
    Date AddYears(int years);
    Date AddMonths(int months);
    Date AddDays(int days);
    String getLetters ();
    String getMonthLetters ();
    String getYearMonthLetters();
    String getToYearMonth();
    long getUnixTime();
}
