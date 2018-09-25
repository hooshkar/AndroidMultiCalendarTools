package com.ali.uneversaldatetools;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ali.uneversaldatetools.date.Calendar;
import com.ali.uneversaldatetools.date.DateSystem;
import com.ali.uneversaldatetools.datePicker.UDatePicker;

/**
 * Created by ali on 9/25/18.
 */

@SuppressLint("ValidFragment")
public class UDatePickerDialog extends DialogFragment {


    private UDatePickerDialogListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_u_date_picker, container);

        UDatePicker datePicker = view.findViewById(R.id.date_picker_dialog_date_picker);
        datePicker.ShowDatePicker(getChildFragmentManager(), Calendar.Jalali);

        Button select = view.findViewById(R.id.btn_dialog_select);
        Button cancel = view.findViewById(R.id.btn_dialog_cancel);

        select.setOnClickListener(v -> {
            if (mListener != null)
                mListener.onSelect(datePicker.getSelectedUnixTime(), datePicker.getSelectedDate());
            dismiss();
        });

        cancel.setOnClickListener(v -> {
            if (mListener != null)
                mListener.onCancel();
            dismiss();
        });
        return view;
    }

    public void setListener(UDatePickerDialogListener listener) {
        this.mListener = listener;
    }

    public interface UDatePickerDialogListener {
        void onCancel();

        void onSelect(long unixTime, DateSystem dateSystem);
    }
}
