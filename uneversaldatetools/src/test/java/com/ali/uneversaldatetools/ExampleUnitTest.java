package com.ali.uneversaldatetools;

import com.ali.uneversaldatetools.date.Calendar;
import com.ali.uneversaldatetools.date.DateConverter;
import com.ali.uneversaldatetools.date.DateSystem;
import com.ali.uneversaldatetools.date.HijriDateTime;
import com.ali.uneversaldatetools.date.JalaliDateTime;
import com.ali.uneversaldatetools.model.DateModel;
import com.ali.uneversaldatetools.tools.DateTools;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void CurrentTimeTest() {
        DateModel date = DateTools.getCurrentDate();
        Log(date.year);
        Log(date.month);
        Log(date.day);
    }


    @Test
    public void Test() {
        DateModel date = DateTools.getCurrentDate();
        DateSystem dateSystem = new DateSystem(date, Calendar.Jalali);
        Log(dateSystem.getYear() + "/" + dateSystem.getMonth() + "/" + dateSystem.getDate());
    }

    @Test
    public void ToStringTest(){
        JalaliDateTime dateTime = new JalaliDateTime(1377,1,27);
        Log(dateTime);
        Log(dateTime.toLongString());
    }

    @Test
    public void UnixTest() {
            int unixTime = 1538316150;
        //     int max  2147483647;
        DateModel dateModel = DateConverter.UnixToGregorian(unixTime);
        Log(dateModel.year);
        Log(dateModel.month);
        Log(dateModel.day);
        Log(dateModel.hour);
        Log(dateModel.min);
        Log(dateModel.sec);
        Log("-------");
        long days = DateConverter.GregorianToDays(1969, 12, 31);
        Log(days);
        long sec = days * 86400;
        Log(sec);
        dateModel = DateConverter.UnixToGregorian(unixTime - 2006141056);
        Log(dateModel.year);
        Log(dateModel.month);
        Log(dateModel.day);

        Log("--");
        Log(Integer.MAX_VALUE);
    }

    @Test
    public void JalaliTest() {
        HijriDateTime mDateTime = new HijriDateTime(DateTools.getCurrentDate());
    }

    @Test
    public void HijriTest() {
        JalaliDateTime mDateTime = new JalaliDateTime(DateTools.getCurrentDate());
        Log(DateConverter.IsHijriLeap(1439));
    }

    @Test
    public void SystemDateTest() {
        DateSystem mDateTime = new DateSystem(DateTools.getCurrentDate(), Calendar.Hijri);
    }

    private void Log(Object s) {
        System.out.println(s);
    }
}