package com.ali.universaldatetools;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.uneversaldatetools.date.Calendar;
import com.ali.uneversaldatetools.datePicker.UDatePicker;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UDatePicker a = findViewById(R.id.date_picker);
        Button lang = findViewById(R.id.btn_lang);
        Button getTime = findViewById(R.id.btn_get_time);
        TextView timeView = findViewById(R.id.text_time_show);

        a.ShowDatePicker(Calendar.Jalali);
        a.setOnDateSelected((dateSystem, unixTime) -> {
            timeView.setText("unix time is: " + unixTime);
            timeView.append("\ndate is: " + dateSystem);
        });

        getTime.setOnClickListener(v -> {
            if (a.getSelectedUnixTime() == null) {
                Toast.makeText(this, "plz select day", Toast.LENGTH_SHORT).show();
            } else {
                timeView.setText("unix time is: " + a.getSelectedUnixTime());
                timeView.append("\ndate is: " + a.getSelectedDate());
            }
        });


        lang.setOnClickListener(v -> {
            if (getString(R.string.LangId).equals("en")) {
                setLocale("fa");
            } else {
                setLocale("en");
            }
        });
    }

    private void setLocale(String lng) {
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lng.toLowerCase())); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
        finishAndRemoveTask();
        startActivity(new Intent(this, MainActivity.class));
    }
}
