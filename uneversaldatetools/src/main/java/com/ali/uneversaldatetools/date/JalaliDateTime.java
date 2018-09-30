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
    private int Hour;
    private int Min;
    private int Sec;


    private static final int[] DaysInMonth = {
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

    public static GregorianDateTime FromUnixTime(int unixTime) {
        DateModel dateModel = DateConverter.UnixToJalali(unixTime);
        Validate(dateModel);
        return new GregorianDateTime(dateModel);
    }

    public JalaliDateTime(DateModel dateTime) {
        DateModel sd = DateConverter.GregorianToJalali(dateTime.year, dateTime.month, dateTime.day);

        Validate(sd);

        Year = sd.year;
        Month = sd.month;
        Day = sd.day;
        Hour = sd.hour;
        Min = sd.min;
        Sec = sd.sec;
    }

    public JalaliDateTime(int year, int month, int day) {

        Validate(new DateModel(year, month, day));

        Year = year;
        Month = month;
        Day = day;
    }

    public JalaliDateTime(int year, int month, int day, int hour, int min, int sec) {

        Validate(new DateModel(year, month, day,hour,min,sec));

        Year = year;
        Month = month;
        Day = day;
        Hour = hour;
        Min = min;
        Sec = sec;
    }

    public JalaliDateTime(int days) {
        DateModel sd = DateConverter.DaysToJalali(days);

        Year = sd.year;
        Month = sd.month;
        Day = sd.day;
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
        return new DateModel(md.year, md.month, md.day);
    }

    public DateModel getFirstDayOfYear() {
        DateModel md = DateConverter.JalaliToGregorian(Year, 1, 1);
        return new DateModel(md.year, md.month, md.day);
    }

    public DateModel getLastDayOfYear() {
        DateModel md = DateConverter.JalaliToGregorian(Year, 12,
                DaysInMonth[12] - (!DateConverter.IsJalaliLeap(Year) ? 1 : 0));
        return new DateModel(md.year, md.month, md.day, 23, 59, 59);
    }

    public DateModel getFirstDayOfMonth() {
        DateModel md = DateConverter.JalaliToGregorian(Year, Month, 1);
        return new DateModel(md.year, md.month, md.day);
    }

    public DateModel getLastDayOfMonth() {
        DateModel md = DateConverter.JalaliToGregorian(Year, Month, DaysInMonth[Month] - (Month == 12 && !DateConverter.IsJalaliLeap(Year) ? 1 : 0));
        return new DateModel(md.year, md.month, md.day, 23, 59, 59);
    }

    public DateModel FirstDayOfSeason(Season season) {
        int month = 1 + (season.getValue() - 1) * 3;
        return new JalaliDateTime(Year, month, 1).getDate();
    }

    public DateModel LastDayOfSeason(Season season) {
        int month = 3 + (season.getValue() - 1) * 3;
        int day = DaysInMonth[month] - (month == 12 && !DateConverter.IsJalaliLeap(Year) ? 1 : 0);
        DateModel md = DateConverter.JalaliToGregorian(Year, month, day);
        return new DateModel(md.year, md.month, md.day, 23, 59, 59);
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

    @Override
    public int getHour() {
        return Hour;
    }

    @Override
    public int getMin() {
        return Min;
    }

    @Override
    public int getSec() {
        return Sec;
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
        return String.format("%04d/%02d", Year, Month);
    }

    public String toString() {
        return String.format("%04d/%02d/%02d", Year, Month, Day);
    }

    public String toLongString() {
        return String.format("%04d/%02d/%02d/%02d/%02d/%02d", Year, Month, Day, Hour, Min, Sec);
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

    private static void Validate(DateModel dateModel) {
        //year
        if (dateModel.year < 0)
            throw new IllegalArgumentException("invalid date");

        //month
        if (dateModel.month < 1 | dateModel.month > 12)
            throw new IllegalArgumentException("invalid date");

        //day
        if (DateConverter.IsGregorianLeap(dateModel.year) & dateModel.month == 12) {
            if (dateModel.day < 1 | dateModel.day > DaysInMonth[dateModel.month] + 1)
                throw new IllegalArgumentException("invalid date");
        } else {
            if (dateModel.day < 1 | dateModel.day > DaysInMonth[dateModel.month])
                throw new IllegalArgumentException("invalid date");
        }

        //hour
        if (dateModel.month < 0 | dateModel.month > 23)
            throw new IllegalArgumentException("invalid date");

        //min
        if (dateModel.month < 0 | dateModel.month > 60)
            throw new IllegalArgumentException("invalid date");

        //sec
        if (dateModel.month < 0 | dateModel.month > 60)
            throw new IllegalArgumentException("invalid date");

    }

}
