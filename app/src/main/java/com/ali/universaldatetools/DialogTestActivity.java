package com.ali.universaldatetools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.ali.uneversaldatetools.UDatePickerDialog;
import com.ali.uneversaldatetools.date.DateSystem;

public class DialogTestActivity extends AppCompatActivity implements UDatePickerDialog.UDatePickerDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        Button showDialog = findViewById(R.id.btn_show_dialog);

        showDialog.setOnClickListener(v -> {
            UDatePickerDialog uDatePickerDialog = new UDatePickerDialog();

            uDatePickerDialog.setListener(this);
            uDatePickerDialog.show(getSupportFragmentManager(), "");
        });
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSelect(long unixTime, DateSystem dateSystem) {
        Toast.makeText(this, dateSystem.toString(), Toast.LENGTH_SHORT).show();
    }
}
