package com.ali.uneversaldatetools;

import com.ali.uneversaldatetools.date.DateConverter;
import com.ali.uneversaldatetools.date.GregorianDateTime;
import com.ali.uneversaldatetools.date.HijriDateTime;
import com.ali.uneversaldatetools.date.JalaliDateTime;
import com.ali.uneversaldatetools.date.TimeZoneHelper;
import com.ali.uneversaldatetools.model.DateModel;
import com.ali.uneversaldatetools.tools.UnixTimeTools;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void ToStringTest() {
        JalaliDateTime jTime = new JalaliDateTime(1377, 1, 27);
        Log(jTime.toString());
        Log(jTime.toLongString());
        Log("---");
        HijriDateTime hTime = new HijriDateTime(1440, 1, 27);
        Log(hTime.toString());
        Log(hTime.toLongString());
        Log("---");
        GregorianDateTime gTime = new GregorianDateTime(2002, 1, 27);
        Log(gTime.toString());
        Log(gTime.toLongString());
    }

    @Test
    public void UnixTest() {
        int unixTime = UnixTimeTools.getCurrentUnixTime();

        Log(unixTime);
        DateModel dateModel = DateConverter.UnixToJalali(unixTime);
        Log(dateModel.year);
        Log(dateModel.month);
        Log(dateModel.day);
        Log(dateModel.hour);
        Log(dateModel.min);
        Log(dateModel.sec);
        Log("-------");
        int unixTime2 = DateConverter.JalaliToUnix(
                dateModel.year,
                dateModel.month,
                dateModel.day,
                dateModel.hour,
                dateModel.min,
                dateModel.sec);
        Log(unixTime2);
        Assert.assertTrue(unixTime == unixTime2);
    }

    @Test
    public void JalaliTest() {
        JalaliDateTime mDateTime = new JalaliDateTime((int) new Date().getTime(), TimeZoneHelper.getSystemTimeZone());
        Log(mDateTime.toLongString());
    }

    @Test
    public void HijriTest() {
        Log(DateConverter.IsHijriLeap(1439));
    }

    private void Log(Object s) {
        System.out.println(s);
    }
}