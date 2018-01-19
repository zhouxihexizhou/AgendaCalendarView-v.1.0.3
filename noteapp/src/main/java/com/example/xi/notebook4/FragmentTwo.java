package com.example.xi.notebook4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentTwo extends AppCompatActivity  implements CalendarPickerController {

    private MyDatabaseHelper dbHelper;

    private BeforeLogin beforeLogin;

    private FrameLayout frameLayout_one;

    private FrameLayout frameLayout_two;

    private FrameLayout frameLayout_three;

    private ImageView imageView_one;

    private ImageView imageView_two;

    private ImageView imageView_three;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.activity_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.agenda_calendar_view)
    AgendaCalendarView mAgendaCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initView();

        initData();

        clickBefore();

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        // minimum and maximum date of our calendar
        // 2 month behind, one year ahead, example: March 2015 <-> May 2015 <-> May 2016
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.YEAR, -3);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 8);

        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);

        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());

    }

    private void initView(){
        frameLayout_one = (FrameLayout)findViewById(R.id.layout_one);
        frameLayout_two = (FrameLayout)findViewById(R.id.layout_two);
        frameLayout_three = (FrameLayout)findViewById(R.id.layout_three);

        imageView_one = (ImageView)findViewById(R.id.image_one);
        imageView_two = (ImageView)findViewById(R.id.image_two);
        imageView_three = (ImageView)findViewById(R.id.image_three);
    }

    private void initData(){
        frameLayout_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentTwo.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_change, R.anim.activity_change);
                FragmentTwo.this.finish();
            }
        });

        imageView_one.setSelected(false);

        imageView_two.setSelected(true);

        imageView_three.setSelected(false);

        frameLayout_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentTwo.this, FragmentThree.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_change, R.anim.activity_change);
                FragmentTwo.this.finish();
            }
        });
    }

    private void clickBefore(){
        beforeLogin = new BeforeLogin();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content2, beforeLogin);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Dialog dialog = new AlertDialog.Builder(FragmentTwo.this)
                .setTitle("退出提示")
                .setMessage("您确定要退出应用吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentTwo.this.finish();
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    public void onDaySelected(DayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    // endregion

    // region Private Methods

    private void mockList(List<CalendarEvent> eventList) {
        dbHelper = new MyDatabaseHelper(FragmentTwo.this, "BookStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if(cursor.moveToNext()){
            do{
                Calendar startTime = Calendar.getInstance();
                Calendar endTime = Calendar.getInstance();
//                startTime.set(cursor.getInt(cursor.getColumnIndex("year")),cursor.getInt(cursor.getColumnIndex("month")),cursor.getInt(cursor.getColumnIndex("day")));
//                endTime.set(cursor.getInt(cursor.getColumnIndex("year")),cursor.getInt(cursor.getColumnIndex("month")),cursor.getInt(cursor.getColumnIndex("day"))+1);
//                startTime.set(2017,12,12);
//                endTime.set(2017,12,13);
                startTime.set(Calendar.YEAR, cursor.getInt(cursor.getColumnIndex("year")));
                startTime.set(Calendar.MONTH, cursor.getInt(cursor.getColumnIndex("month"))-1);
                startTime.set(Calendar.DAY_OF_MONTH, cursor.getInt(cursor.getColumnIndex("day")));
                endTime.set(Calendar.YEAR, cursor.getInt(cursor.getColumnIndex("year")));
                endTime.set(Calendar.MONTH, cursor.getInt(cursor.getColumnIndex("month"))-1);
                endTime.set(Calendar.DAY_OF_MONTH, cursor.getInt(cursor.getColumnIndex("day")));

//                startTime.add(Calendar.DAY_OF_YEAR, cursor.getInt(cursor.getColumnIndex("day")));
//                endTime.add(Calendar.DAY_OF_YEAR,cursor.getInt(cursor.getColumnIndex("day"))+1);
                System.out.println("asdasdasdasdasd第"+cursor.getString(cursor.getColumnIndex("week"))+"周");
                BaseCalendarEvent event1 = new BaseCalendarEvent("第"+cursor.getString(cursor.getColumnIndex("week"))+"周","星期"+cursor.getString(cursor.getColumnIndex("weekday")),cursor.getString(cursor.getColumnIndex("title")), cursor.getString(cursor.getColumnIndex("weather")), cursor.getString(cursor.getColumnIndex("area")),
                        ContextCompat.getColor(this, R.color.orange_dark), startTime, endTime, true);
                eventList.add(event1);
            }while(cursor.moveToNext());
        }
        cursor.close();

//        Calendar startTime1 = Calendar.getInstance();
//        Calendar endTime1 = Calendar.getInstance();
//        endTime1.add(Calendar.MONTH,1);
//        BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland","Thibault travels in Iceland","Thibault travels in Iceland", "A wonderful journey!", "Iceland",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime1, endTime1, true);
//        eventList.add(event1);
//
//        Calendar startTime2 = Calendar.getInstance();
//        startTime2.add(Calendar.DAY_OF_YEAR, 1);
//        Calendar endTime2 = Calendar.getInstance();
//        endTime2.add(Calendar.DAY_OF_YEAR, 3);
//        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
//                ContextCompat.getColor(this, R.color.yellow), startTime2, endTime2, true);
//        eventList.add(event2);
//
//        Calendar startTime3 = Calendar.getInstance();
//        Calendar endTime3 = Calendar.getInstance();
//        startTime3.set(Calendar.HOUR_OF_DAY, 14);
//        startTime3.set(Calendar.MINUTE, 0);
//        endTime3.set(Calendar.HOUR_OF_DAY, 15);
//        endTime3.set(Calendar.MINUTE, 0);
//        DrawableCalendarEvent event3 = new DrawableCalendarEvent("Visit of Harpa", "", "Dalvík",
//                ContextCompat.getColor(this, R.color.blue_dark), startTime3, endTime3, false, R.drawable.common_ic_googleplayservices);
//        eventList.add(event3);
    }
}
