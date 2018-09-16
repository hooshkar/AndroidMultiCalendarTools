package com.ali.uneversaldatetools.datePicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.ali.uneversaldatetools.R;
import com.ali.uneversaldatetools.model.Month;

/**
 * Created by ali on 9/12/18.
 */


public class CalenderViewFragment extends Fragment {

    private Month mMonth;
    private View cView;
    private CalenderFragmentInterface mInterface;
    private int mIndex;

    /**
     * @apiNote Do not use it
     */
    public CalenderViewFragment() {
        throw new RuntimeException("do not use this constructor");
    }

    @SuppressLint("ValidFragment")
    public CalenderViewFragment(Month month, int index, CalenderFragmentInterface mInterface) {
        this.mMonth = month;
        this.mInterface = mInterface;
        mIndex = index;
        Log.d("frag", "const(" + String.valueOf(index) + ")");
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        cView = inflater.inflate(R.layout.item_month_view, null);
        Log.d("frag", "createView(" + String.valueOf(mIndex) + ")");
        Render();
        return cView;
    }

    public void Render() {
        if (cView == null)
            throw new RuntimeException("Render called before onCreateView in index: " + mIndex);
        Log.d("frag", "render(" + String.valueOf(mIndex) + ")");
        GridLayout gridLayout = cView.findViewById(R.id.grid_layout_days);
        gridLayout.removeAllViews();
        DaysViewGenerator daysViewGenerator = new DaysViewGenerator(getActivity());

        //add empties
        for (int i = 0; mMonth.getStartAt().getValue() > i; i++)
            gridLayout.addView(daysViewGenerator.getEmptyView());

        //add days
        for (int i = 1; mMonth.getDaysCount() >= i; i++) {
            if (i == mInterface.selectedDayProvider())
                AddView(gridLayout, daysViewGenerator.getSelectedView(i));
            else if (i == mInterface.currentDayProvider() & mInterface.currentMonthProvider() == mIndex + 1) {
                AddView(gridLayout, daysViewGenerator.getTodayView(i));
            } else {
                AddView(gridLayout, daysViewGenerator.getNormalView(i));
            }
        }
    }

    public void AddView(GridLayout gridLayout, View view) {
        gridLayout.addView(view);

        view.setOnClickListener(v -> {
            mInterface.onDaySelectListener((int) v.getTag());
            Render();
        });
    }

    public interface CalenderFragmentInterface {

        void onDaySelectListener(int day);

        int selectedDayProvider();

        int currentMonthProvider();

        int currentDayProvider();
    }

    public void setMonth(Month month) {
        mMonth = month;
        try {
            Render();
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Render called before onCreateView in index: "))
                throw e;
        }
    }
}
