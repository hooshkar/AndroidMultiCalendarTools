package com.ali.uneversaldatetools.date;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.ali.uneversaldatetools.model.DateModel;
import com.ali.uneversaldatetools.tools.DateTools;

import java.util.Date;
import java.util.Objects;

/**
 * Created by ali on 9/5/18.
 */

//this is struct
public class DateSystem implements IDate, Comparable<IDate> {

    private Calendar Calendar;
    private DateModel Date;
    private IDate Date_SD;

    public DateSystem(DateModel date, Calendar calendar) {
        Calendar = calendar;

        if (date.getMonth() == 0) throw new IllegalArgumentException("month cant be 0");
        switch (Calendar) {
            case Jalali: {
                Date_SD = new JalaliDateTime(date);
                break;
            }
            case Gregorian: {
                Date_SD = new GregorianDateTime(date);
                break;
            }
            case Hijri: {
                Date_SD = new HijriDateTime(date);
                break;
            }
            default: {
                throw new RuntimeException("Invalid Calendar Type!");
            }
        }

        Date = date;
    }

    public DateSystem(int year, int month, int day, Calendar calendar) {
        Calendar = calendar;

        if (month == 0) throw new IllegalArgumentException("month cant be 0");
        switch (Calendar) {
            case Jalali: {
                Date_SD = new JalaliDateTime(year, month, day);
                break;
            }
            case Gregorian: {
                Date_SD = new GregorianDateTime(year, month, day);
                break;
            }
            case Hijri: {
                Date_SD = new HijriDateTime(year, month, day);
                break;
            }
            default: {
                throw new RuntimeException("Invalid Calendar Type!");
            }
        }

        Date = Date_SD.getDate();
    }

    public Calendar getCalendar() {
        return Calendar;
    }

    public DateModel getDate() {
        return Date;
    }

    public JalaliDateTime getJalaliDateTime() {
        return Date_SD.getJalaliDateTime();
    }

    public GregorianDateTime getGregorianDateTime() {
        return Date_SD.getGregorianDateTime();
    }

    public HijriDateTime getHijriDateTime() {
        return Date_SD.getHijriDateTime();
    }

    public IDate getDate_SD() {
        return Date_SD;
    }

    public int getYear() {
        return Date_SD.getYear();
    }

    public int getMonth() {
        return Date_SD.getMonth();
    }

    public int getDay() {
        return Date_SD.getDay();
    }

    public int getDays() {
        return Date_SD.getDays();
    }

    public DateModel getFirstDayOfYear() {
        return Date_SD.getFirstDayOfYear();
    }

    public DateModel getLastDayOfYear() {
        return Date_SD.getLastDayOfYear();
    }

    public DateModel getFirstDayOfMonth() {
        return Date_SD.getFirstDayOfMonth();
    }

    public DateModel getLastDayOfMonth() {
        return Date_SD.getLastDayOfMonth();
    }

    public DateModel FirstDayOfSeason(Season season) {
        return Date_SD.FirstDayOfSeason(season);
    }

    public DateModel LastDayOfSeason(Season season) {
        return Date_SD.LastDayOfSeason(season);
    }

    public Season getSeason() {
        return Date_SD.getSeason();
    }

    public DayOfWeek getDayOfWeek() {
        return Date_SD.getDayOfWeek();
    }

    public int getDaysOfMonth() {
        return Date_SD.getDaysOfMonth();
    }

    public String getLetters() {
        return Date_SD.getLetters();
    }

    public String getMonthLetters() {
        return Date_SD.getMonthLetters();
    }

    public String getYearMonthLetters() {
        return Date_SD.getYearMonthLetters();
    }

    public DateModel AddYears(int years) {
        return Date_SD.AddYears(years);
    }

    public DateModel AddMonths(int months) {
        return Date_SD.AddMonths(months);
    }

    public DateModel AddDays(int days) {
        return Date_SD.AddDays(days);
    }

    public static DateSystem Now(Calendar calendar) {
        return new DateSystem(DateTools.getCurrentDate(), calendar);
    }

    public static DateSystem Parse(String date, Calendar calendar) {
        switch (calendar) {
            case Jalali: {
                return new DateSystem(JalaliDateTime.Parse(date).getDate(), calendar);
            }
            case Gregorian: {
                return new DateSystem(GregorianDateTime.Parse(date).getDate(), calendar);
            }
            case Hijri: {
                return new DateSystem(HijriDateTime.Parse(date).getDate(), calendar);
            }
            default: {
                throw new RuntimeException("Invalid Calendar Type!");
            }
        }
    }

    public static DateSystem Parse(String yearMonth, int day, Calendar calendar) {
        switch (calendar) {
            case Jalali: {
                return new DateSystem(JalaliDateTime.Parse(yearMonth, day).getDate(), calendar);
            }
            case Gregorian: {
                return new DateSystem(GregorianDateTime.Parse(yearMonth, day).getDate(), calendar);
            }
            case Hijri: {
                return new DateSystem(HijriDateTime.Parse(yearMonth, day).getDate(), calendar);
            }
            default: {
                throw new RuntimeException("Invalid Calendar Type!");
            }
        }
    }

    @Nullable
    public static Date ParseOrNull(String date) {
        try {
            long binary = 0;
            date = date.replace(" ", "");//Trim()
            try {
                Long.parseLong(date, (int) binary);
                return null;                                         //todo last was return java.util.Date. (binary);
            } catch (NumberFormatException e) {
                return new Date(java.util.Date.parse(date));
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String getToYearMonth() {
        return Date_SD.getToYearMonth();
    }

    public String toString() {
        return Date_SD.toString();
    }

    @Override
    public int compareTo(@NonNull IDate o) {
        return CompareTo(o);
    }

    public int CompareTo(IDate t2) {
        return Date.compareTo(t2);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean equals(DateSystem other) {
        return Calendar == other.Calendar && Objects.equals(Date_SD, other.Date_SD);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean equals(Object obj) {
        return null != obj && obj instanceof DateSystem && equals((DateSystem) obj);
    }

    public int hashCode() {
        return (Calendar.getValue() * 397) ^ (Date_SD.hashCode());
    }

    public long getUnixTime() {
        long from = DateConverter.GregorianToDays(1970, 1, 1);
        return (getGregorianDateTime().getDays() - from) * 86400;
    }
}
