package com.ali.uneversaldatetools.date;

import com.ali.uneversaldatetools.model.DateModel;

/**
 * Created by ali on 9/5/18.
 */

public interface IDate {

    int getYear();
    int getMonth();
    int getDay();
    int getDays();
    int getDaysOfMonth();
    DateModel getDate();
    JalaliDateTime getJalaliDateTime();
    GregorianDateTime getGregorianDateTime();
    HijriDateTime getHijriDateTime();
    DateModel getFirstDayOfYear();
    DateModel getLastDayOfYear ();
    DateModel getFirstDayOfMonth ();
    DateModel getLastDayOfMonth ();
    DateModel FirstDayOfSeason(Season season);
    DateModel LastDayOfSeason(Season season);
    Season getSeason();
    DayOfWeek getDayOfWeek ();
    DateModel AddYears(int years);
    DateModel AddMonths(int months);
    DateModel AddDays(int days);
    String getLetters ();
    String getMonthLetters ();
    String getYearMonthLetters();
    String getToYearMonth();
}
