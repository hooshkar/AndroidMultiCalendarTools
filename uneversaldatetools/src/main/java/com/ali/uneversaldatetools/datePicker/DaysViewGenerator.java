package com.ali.uneversaldatetools.datePicker;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ali.uneversaldatetools.date.Calendar;
import com.ali.uneversaldatetools.R;
import com.ali.uneversaldatetools.tools.StringGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 9/9/18.
 */

public class DaysViewGenerator {

    private Activity mActivity;

    public DaysViewGenerator(Activity activity) {
        mActivity = activity;
    }

    public View getEmptyView() {
        return mActivity.getLayoutInflater().inflate(R.layout.item_day_empty, null);
    }

    public View getNormalView(int num) {
        ViewGroup root = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.item_day_normal, null);
        TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(0);
        day.setText(StringGenerator.NumberToString(mActivity.getResources(), num));
        root.setTag(num);
        return root;
    }

    public View getSelectedView(int num) {

        ViewGroup root = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.item_day_selected, null);
        TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(1);
        day.setText(StringGenerator.NumberToString(mActivity.getResources(), num));
        root.setTag(num);
        return root;
    }

    public View getTodayView(int num) {
        ViewGroup root = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.item_day_today, null);
        TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(1);
        day.setText(StringGenerator.NumberToString(mActivity.getResources(), num));
        root.setTag(num);
        return root;
    }

    public List<View> getDayOfWeek() {

        List<View> views = new ArrayList<>();

        for (String i : mActivity.getResources().getStringArray(R.array.week_days_one_char)) {
            ViewGroup root = (ViewGroup) mActivity.getLayoutInflater().inflate(R.layout.item_day_normal, null);
            TextView day = (TextView) ((ViewGroup) root.getChildAt(0)).getChildAt(0);
            day.setTextColor(mActivity.getResources().getColor(R.color.gray));
            day.setText(i);
            views.add(root);
        }

        return views;
    }
}
