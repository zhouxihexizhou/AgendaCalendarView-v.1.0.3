package com.example.xi.notebook4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class ChangeSchoolCalendar extends FragmentActivity {

    private Button button_change_calendar_back;

    private Button button_change_calendar_cancel;

    private Button button_change_calendar_certain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_school_calendar);

        button_change_calendar_back = (Button)findViewById(R.id.change_calendar_back);
        button_change_calendar_cancel = (Button)findViewById(R.id.change_calendar_cancel);
        button_change_calendar_certain = (Button)findViewById(R.id.change_calendar_certain);

        button_change_calendar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeSchoolCalendar.this.finish();
            }
        });

        button_change_calendar_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeSchoolCalendar.this.finish();
            }
        });

        button_change_calendar_certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
