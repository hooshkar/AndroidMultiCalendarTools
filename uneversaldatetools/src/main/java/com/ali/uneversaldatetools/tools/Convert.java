package com.ali.uneversaldatetools.tools;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by ali on 8/11/18.
 */

public class Convert {

    public static int PixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static int DpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
