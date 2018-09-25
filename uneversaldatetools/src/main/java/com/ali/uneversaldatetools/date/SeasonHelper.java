package com.ali.uneversaldatetools.date;

/**
 * Created by ali on 9/5/18.
 */

public class SeasonHelper {

    public static String[] GetTitleList()
    {
        return new String[]
        {
            "بهار", "تابستان", "پاییز", "زمستان"
        };
    }

    public static Season GetByTitle(String title)
    {
        switch (title)
        {
            case "بهار":
            {
                return Season.Spring;
            }
            case "تابستان":
            {
                return Season.Summer;
            }
            case "پاییز":
            {
                return Season.Autumn;
            }
            case "زمستان":
            {
                return Season.Winter;
            }
            default:
            {
                return Season.Spring;
            }
        }
    }
}
