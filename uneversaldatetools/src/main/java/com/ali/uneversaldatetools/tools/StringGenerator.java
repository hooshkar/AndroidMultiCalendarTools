package com.ali.uneversaldatetools.tools;

import android.content.res.Resources;

import com.ali.uneversaldatetools.R;
import com.ali.uneversaldatetools.date.Calendar;

/**
 * Created by ali on 9/8/18.
 */

public class StringGenerator {

    public static String NumberToString(Resources resources, int number) {
        String numberStr = String.valueOf(number);
        String[] nums = resources.getStringArray(R.array.nums);
        numberStr = numberStr.replace("0", nums[0]);
        numberStr = numberStr.replace("1", nums[1]);
        numberStr = numberStr.replace("2", nums[2]);
        numberStr = numberStr.replace("3", nums[3]);
        numberStr = numberStr.replace("4", nums[4]);
        numberStr = numberStr.replace("5", nums[5]);
        numberStr = numberStr.replace("6", nums[6]);
        numberStr = numberStr.replace("7", nums[7]);
        numberStr = numberStr.replace("8", nums[8]);
        numberStr = numberStr.replace("9", nums[9]);
        return numberStr;
    }

    public static String Month(Resources resources, int index, Calendar calendar) {

        switch (calendar) {
            case Gregorian:
                return resources.getStringArray(R.array.gregorian_months)[index-1];
            case Jalali:
                return resources.getStringArray(R.array.jalali_months)[index-1];
            case Hijri:
                return resources.getStringArray(R.array.hijri_month)[index-1];
            default:
                throw new IllegalArgumentException("calender not valid");
        }
    }
}
