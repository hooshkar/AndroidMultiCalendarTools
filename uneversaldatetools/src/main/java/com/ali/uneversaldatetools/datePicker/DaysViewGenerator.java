package com.ali.uneversaldatetools.datePicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ali.uneversaldatetools.R;
import com.ali.uneversaldatetools.tools.StringGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 9/9/18.
 */

class DaysViewGenerator {

    private Context mActivity;
    private LayoutInflater mInflator;

    DaysViewGenerator(Context context) {
        mActivity = context;
        mInflator = LayoutInflater.from(context);
    }

    View getEmptyView() {
        return mInflator.inflate(R.layout.item_day_empty, null);
    }

    View getNormalView(int num) {
        ViewGroup root = (ViewGroup) mInflator.inflate(R.layout.item_day_normal, null);
        TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(0);
        day.setText(StringGenerator.NumberToString(mActivity.getResources(), num));
        root.setTag(num);
        return root;
    }

    View getSelectedView(int num) {

        ViewGroup root = (ViewGroup) mInflator.inflate(R.layout.item_day_selected, null);
        TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(1);
        day.setText(StringGenerator.NumberToString(mActivity.getResources(), num));
        root.setTag(num);
        return root;
    }

    View getTodayView(int num) {
        ViewGroup root = (ViewGroup) mInflator.inflate(R.layout.item_day_today, null);
        TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(1);
        day.setText(StringGenerator.NumberToString(mActivity.getResources(), num));
        root.setTag(num);
        return root;
    }

    List<View> getDayOfWeek() {

        List<View> views = new ArrayList<>();

        for (String i : mActivity.getResources().getStringArray(R.array.week_days_one_char)) {
            ViewGroup root = (ViewGroup) mInflator.inflate(R.layout.item_day_normal, null);
            TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(0);
            day.setTextColor(mActivity.getResources().getColor(R.color.gray));
            day.setText(i);
            views.add(root);
        }

        return views;
    }
}
