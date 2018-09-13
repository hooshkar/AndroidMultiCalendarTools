package com.ali.uneversaldatetools.datePicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.ali.uneversaldatetools.R;
import com.ali.uneversaldatetools.model.Month;

/**
 * Created by ali on 9/12/18.
 */

@SuppressLint("ValidFragment")
public class CalenderViewFragment extends Fragment {

    private Month month;
    private View cView;
    private CalenderFragmentInterface mInterface;
    private int mIndex;

    @SuppressLint("ValidFragment")
    public CalenderViewFragment(Month month, int index, CalenderFragmentInterface mInterface) {
        this.month = month;
        this.mInterface = mInterface;
        mIndex = index;
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        cView = inflater.inflate(R.layout.item_month_view, null);
        Render();
        return cView;
    }


    public void Render() {
        GridLayout gridLayout = cView.findViewById(R.id.grid_layout_days);
        gridLayout.removeAllViews();
        DaysViewGenerator daysViewGenerator = new DaysViewGenerator(getActivity());

        //add empties
        for (int i = 0; month.getStartAt().getValue() > i; i++)
            gridLayout.addView(daysViewGenerator.getEmptyView());

        //add days
        for (int i = 1; month.getDaysCount() >= i; i++) {
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
}
