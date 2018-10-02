package com.ali.uneversaldatetools.date;

import android.support.annotation.NonNull;

import com.ali.uneversaldatetools.model.DateModel;
import com.ali.uneversaldatetools.tools.DateTools;

import java.util.TimeZone;

/**
 * Created by ali on 9/5/18.
 */

public class GregorianDateTime implements IDate, Comparable<GregorianDateTime> {

    private int Year;
    private int Month;
    private int Day;
    private int Hour;
    private int Min;
    private int Sec;
    private TimeZone TimeZone;

    public static final int[] DaysInMonth = {
            0,
            31,
            29,
            31,
            30,
            31,
            30,
            31,
            31,
            30,
            31,
            30,
            31
    };

    public GregorianDateTime(int year, int month, int day) {
        DateValidation.GregorianValidate(year, month, day, 0, 0, 0);
        Year = year;
        Month = month;
        Day = day;
        TimeZone = TimeZoneHelper.getSystemTimeZone();
    }

    public GregorianDateTime(int year, int month, int day, int hour, int min, int sec, TimeZone timeZone) {
        this(year, month, day);
        DateValidation.GregorianValidate(year, month, day, hour, min, sec);
        Hour = hour;
        Min = min;
        Sec = sec;
        TimeZone = timeZone;
    }

    public GregorianDateTime(int days) {
        DateModel sd = DateConverter.DaysToGregorian(days);
        Year = sd.year;
        Month = sd.month;
        Day = sd.day;
        TimeZone = TimeZoneHelper.getSystemTimeZone();
    }

    public GregorianDateTime(int days, int hour, int min, int sec, TimeZone timeZone) {
        this(days);
        Hour = hour;
        Min = min;
        Sec = sec;
        TimeZone = timeZone;
    }

    public GregorianDateTime(int unixTimeSeconds, TimeZone timeZone) {

        int offset = TimeZoneHelper.ToSeconds(timeZone);

        DateModel dateModel = DateConverter.UnixToGregorian(unixTimeSeconds + offset);
        Year = dateModel.year;
        Month = dateModel.month;
        Day = dateModel.day;
        Hour = dateModel.hour;
        Min = dateModel.min;
        Sec = dateModel.sec;
        TimeZone = timeZone;
    }


    public static GregorianDateTime Parse(String s) {
        int y = Integer.valueOf(s.substring(0, 4));
        int m = Integer.valueOf(s.substring(5, 7));
        int d = Integer.valueOf(s.substring(8, 10));
        return new GregorianDateTime(y, m, d);
    }

    public static GregorianDateTime Parse(String yearMonth, int day) {
        int y = Integer.valueOf(yearMonth.substring(0, 4));
        int m = Integer.valueOf(yearMonth.substring(5, 7));
        return new GregorianDateTime(y, m, day);
    }

    public static GregorianDateTime ParseYearMonth(String s) {
        int y = Integer.valueOf(s.substring(0, 4));
        int m = Integer.valueOf(s.substring(5, 7));
        return new GregorianDateTime(y, m, 1);
    }

    public static GregorianDateTime Now() {
        DateModel crnt = DateTools.getCurrentDate();
        return new GregorianDateTime(crnt.year, crnt.month, crnt.day, crnt.hour, crnt.min, crnt.sec, TimeZoneHelper.getSystemTimeZone());
    }


    public DateModel getDate() {
        return new DateModel(Year, Month, Day);
    }

    public DateModel getFirstDayOfYear() {
        return new DateModel(Year, 1, 1);
    }

    public DateModel getLastDayOfYear() {
        return new DateModel(Year, 12, DaysInMonth[12], 23, 59, 59);
    }

    public DateModel getFirstDayOfMonth() {
        return new DateModel(Year, Month, 1);
    }

    public DateModel getLastDayOfMonth() {
        return new DateModel(Year, Month, DaysInMonth[Month] - (Month == 2 && !DateConverter.IsGregorianLeap(Year) ? 1 : 0), 23, 59, 59);
    }

    public DateModel FirstDayOfSeason(Season season) {
        int mSeason = season == Season.Winter ? 1 : season.getValue() + 1;
        int month = 1 + (mSeason - 1) * 3;
        return new GregorianDateTime(Year, month, 1).getDate();
    }

    public DateModel LastDayOfSeason(Season season) {
        int mSeason = season == Season.Winter ? 1 : season.getValue() + 1;
        int month = 3 + (mSeason - 1) * 3;
        int day = DaysInMonth[month] - (month == 2 && !DateConverter.IsGregorianLeap(Year) ? 1 : 0);
        return new DateModel(Year, month, day, 23, 59, 59);
    }

    public Season getSeason() {

        switch (Month) {
            case 1:
            case 2:
            case 3:
                return Season.Winter;
            case 4:
            case 5:
            case 6:
                return Season.Spring;
            case 7:
            case 8:
            case 9:
                return Season.Summer;
            case 10:
            case 11:
            case 12:
                return Season.Autumn;
        }
        throw new IndexOutOfBoundsException("month is not valid");

    }

    public DateModel AddYears(int years) {
        int y = Year + years;
        int m = Month;
        int maxd = DaysInMonth[m];
        maxd -= (m == 2 && !DateConverter.IsGregorianLeap(y) ? 1 : 0);
        int d = Day > maxd ? maxd : Day;
        return new GregorianDateTime(y, m, d).getDate();
    }

    public DateModel AddMonths(int months) {
        int m = Month - 1 + months;
        int y = Year + (m / 12);
        m = (m % 12) + 1;
        int maxd = DaysInMonth[m];
        maxd -= (m == 2 && !DateConverter.IsGregorianLeap(y) ? 1 : 0);
        int d = Day > maxd ? maxd : Day;
        return new GregorianDateTime(y, m, d).getDate();
    }

    public DateModel AddDays(int days) {
        return new GregorianDateTime(getDays() + days).getDate();
    }

    public JalaliDateTime getJalaliDateTime() {
        DateModel d = getDate();
        return new JalaliDateTime(d.year, d.month, d.day, d.hour, d.min, d.sec, TimeZone);
    }

    public GregorianDateTime getGregorianDateTime() {
        return this;
    }

    public HijriDateTime getHijriDateTime() {
        DateModel d = getDate();
        return new HijriDateTime(d.year, d.month, d.day, d.hour, d.min, d.sec, TimeZone);
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

    public int getMonth() {
        return Month;
    }

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
        return DateConverter.GregorianToDays(Year, Month, Day);
    }

    public DayOfWeek getDayOfWeek() {
        return DayOfWeek.ToDayOfWeek((getDays() + 1) % 7);
    }

    public int getDaysOfMonth() {
        return DaysInMonth[Month] - (Month == 2 & !DateConverter.IsGregorianLeap(Year) ? 1 : 0);
    }

    public String getToYearMonth() {
        return String.format("%04d/%02d " + TimeZone.getDisplayName(), Year, Month);
    }

    public String toString() {
        return String.format("%04d/%02d/%02d " + TimeZone.getDisplayName(), Year, Month, Day);
    }

    public String toLongString() {
        return String.format("%04d/%02d/%02d %02d:%02d:%02d " + TimeZone.getDisplayName(), Year, Month, Day, Hour, Min, Sec);
    }

    @Override
    public int compareTo(@NonNull GregorianDateTime o) {
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

    public boolean equals(GregorianDateTime other) {
        return Year == other.Year && Month == other.Month && Day == other.Day;
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        return obj instanceof GregorianDateTime && equals((GregorianDateTime) obj);
    }

    public int hashCode() {
        int hashCode = Year;
        hashCode = (hashCode * 397) ^ Month;
        hashCode = (hashCode * 397) ^ Day;
        return hashCode;
    }

    @Override
    public int toUnixTime() {
        return DateConverter.GregorianToUnix(Year, Month, Day, Hour, Min, Sec) - TimeZoneHelper.ToSeconds(TimeZone);
    }
}
