package com.ali.uneversaldatetools.date;

import android.support.annotation.NonNull;

import com.ali.uneversaldatetools.model.DateModel;
import com.ali.uneversaldatetools.tools.DateTools;

/**
 * Created by ali on 9/5/18.
 */

public class JalaliDateTime implements IDate, Comparable<JalaliDateTime> {

    private int Year;
    private int Month;
    private int Day;

    public static final int[] DaysInMonth = {
            0,
            31,
            31,
            31,
            31,
            31,
            31,
            30,
            30,
            30,
            30,
            30,
            30
    };

    public JalaliDateTime(DateModel dateTime) {
        DateModel sd = DateConverter.GregorianToJalali(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());

        Year = sd.getYear();
        Month = sd.getMonth();
        Day = sd.getDay();
    }

    public JalaliDateTime(int year, int month, int day) {
        Year = year;
        Month = month;
        Day = day;
    }

    public JalaliDateTime(int days) {
        DateModel sd = DateConverter.DaysToJalali(days);

        Year = sd.getYear();
        Month = sd.getMonth();
        Day = sd.getDay();
    }

    public static JalaliDateTime Parse(String s) {
        int y = Integer.valueOf(s.substring(0, 4));
        int m = Integer.valueOf(s.substring(5, 7));
        int d = Integer.valueOf(s.substring(8, 10));
        return new JalaliDateTime(y, m, d);
    }

    public static JalaliDateTime Parse(String yearMonth, int day) {
        int y = Integer.valueOf(yearMonth.substring(0, 4));
        int m = Integer.valueOf(yearMonth.substring(5, 7));
        return new JalaliDateTime(y, m, day);
    }

    public static JalaliDateTime ParseYearMonth(String s) {
        int y = Integer.valueOf(s.substring(0, 4));
        int m = Integer.valueOf(s.substring(5, 7));
        return new JalaliDateTime(y, m, 1);
    }

    public static JalaliDateTime Now() {
        return new JalaliDateTime(DateTools.getCurrentDate());
    }

    public DateModel getDate() {
        DateModel md = DateConverter.JalaliToGregorian(Year, Month, Day);
        return new DateModel(md.getYear(), md.getMonth(), md.getDay());
    }

    public DateModel getFirstDayOfYear() {
        DateModel md = DateConverter.JalaliToGregorian(Year, 1, 1);
        return new DateModel(md.getYear(), md.getMonth(), md.getDay());
    }

    public DateModel getLastDayOfYear() {
        DateModel md = DateConverter.JalaliToGregorian(Year, 12,
                DaysInMonth[12] - (!DateConverter.IsJalaliLeap(Year) ? 1 : 0));
        return new DateModel(md.getYear(), md.getMonth(), md.getDay(), 23, 59, 59);
    }

    public DateModel getFirstDayOfMonth() {
        DateModel md = DateConverter.JalaliToGregorian(Year, Month, 1);
        return new DateModel(md.getYear(), md.getMonth(), md.getDay());
    }

    public DateModel getLastDayOfMonth() {
        DateModel md = DateConverter.JalaliToGregorian(Year, Month, DaysInMonth[Month] - (Month == 12 && !DateConverter.IsJalaliLeap(Year) ? 1 : 0));
        return new DateModel(md.getYear(), md.getMonth(), md.getDay(), 23, 59, 59);
    }

    public DateModel FirstDayOfSeason(Season season) {
        int month = 1 + (season.getValue() - 1) * 3;
        return new JalaliDateTime(Year, month, 1).getDate();
    }

    public DateModel LastDayOfSeason(Season season) {
        int month = 3 + (season.getValue() - 1) * 3;
        int day = DaysInMonth[month] - (month == 12 && !DateConverter.IsJalaliLeap(Year) ? 1 : 0);
        DateModel md = DateConverter.JalaliToGregorian(Year, month, day);
        return new DateModel(md.getYear(), md.getMonth(), md.getDay(), 23, 59, 59);
    }

    public Season getSeason() {
        switch (Month) {
            case 1:
            case 2:
            case 3:
                return Season.Spring;
            case 4:
            case 5:
            case 6:
                return Season.Summer;
            case 7:
            case 8:
            case 9:
                return Season.Autumn;
            case 10:
            case 11:
            case 12:
                return Season.Winter;
        }
        throw new IndexOutOfBoundsException("season not valid");
    }

    public DateModel AddYears(int years) {
        int y = Year + years;
        int m = Month;
        int maxd = DaysInMonth[m];
        maxd -= (m == 12 && !DateConverter.IsJalaliLeap(y) ? 1 : 0);
        int d = Day > maxd ? maxd : Day;
        return new JalaliDateTime(y, m, d).getDate();
    }

    public DateModel AddMonths(int months) {
        int m = Month - 1 + months;
        int y = Year + (m / 12);
        m = (m % 12) + 1;
        int maxd = DaysInMonth[m];
        maxd -= (m == 12 && !DateConverter.IsJalaliLeap(y) ? 1 : 0);
        int d = Day > maxd ? maxd : Day;
        return new JalaliDateTime(y, m, d).getDate();
    }

    public DateModel AddDays(int days) {
        return new JalaliDateTime(getDays() + days).getDate();
    }

    public JalaliDateTime getJalaliDateTime() {
        return this;
    }

    public GregorianDateTime getGregorianDateTime() {
        return new GregorianDateTime(getDate());
    }

    public HijriDateTime getHijriDateTime() {
        return new HijriDateTime(getDate());
    }

    public String getLetters() {
        return "";
    }

    public String getMonthLetters() {
        return "";
    }

    public String getYearMonthLetters() {
        return "";
    }

    public int getYear() {
        return Year;
    }

    @Override
    public int getMonth() {
        return Month;
    }

    @Override
    public int getDay() {
        return Day;
    }

    public int getDays() {
        return DateConverter.JalaliToDays(Year, Month, Day);
    }

    public DayOfWeek getDayOfWeek() {
        return DayOfWeek.ToDayOfWeek((getDays() + 5) % 7);
    }

    public int getDaysOfMonth() {
        return Month < 7 ? 31 : (Month == 12 & !DateConverter.IsJalaliLeap(Year) ? 29 : 30);
    }

    public String getToYearMonth() {
        return String.format("%04d/%02d", Year, Month); //tested
    }

    public String toString() {
        return String.format("%04d/%02d/%02d", Year, Month, Day); //tested
    }

    @Override
    public int compareTo(@NonNull JalaliDateTime o) {
        return CompareTo(o);
    }

    public int CompareTo(IDate t2) {
        if (t2.getYear() > Year) return -1;
        if (t2.getYear() < Year) return 1;
        if (t2.getMonth() > Month) return -1;
        if (t2.getMonth() < Month) return 1;
        if (t2.getDay() > Day) return -1;
        return t2.getDay() == Day ? 0 : 1;
    }

    public boolean equals(JalaliDateTime other) {
        return Year == other.Year && Month == other.Month && Day == other.Day;
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        return obj instanceof JalaliDateTime && equals((JalaliDateTime) obj);
    }

    public int hashCode() {
        int hashCode = Year;
        hashCode = (hashCode * 397) ^ Month;
        hashCode = (hashCode * 397) ^ Day;
        return hashCode;
    }

}
