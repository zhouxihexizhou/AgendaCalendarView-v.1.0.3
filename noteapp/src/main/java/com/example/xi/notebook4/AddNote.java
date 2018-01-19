package com.example.xi.notebook4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.TargetApi;

import com.example.xi.notebook4.Overall.Variable;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;



@SuppressLint("SimpleDateFormat")
public class AddNote extends FragmentActivity {

    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy MM dd aa hh:mm");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd");


    private Button button_finish;

    private Button button_back;

    private Button arrow_back;

    private EditText editText_title;

    private EditText editText_text;

    private String title;

    private String text;

    private String remindtime;

    private String community;

    private int years;

    private int mouths;

    private int days;

    private  int weekers;
    private  int dayers;


    private MyDatabaseHelper dbHelper;

    private int number = -1;

    private int position = -1;

    private FloatingActionButton add_time_tabbar;

    private Switch switch_tabbar;

    private boolean Ispublish = false;

    private Spinner Community;

    private LocalBroadcastManager localBroadcastManager;

    private TextView textViewTime;

    private Weeker weeker;

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            textViewTime.setText(mFormatter.format(date));

            years = Integer.valueOf(yearFormat.format(date).toString());
            mouths = Integer.valueOf(monthFormat.format(date).toString());
            days = Integer.valueOf(dayFormat.format(date).toString());
            weeker = Translation.getWeeker(years,mouths,days);
            weekers = weeker.weeks;
            dayers = weeker.day;

        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {
            Toast.makeText(AddNote.this,
                    "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        button_finish = (Button)findViewById(R.id.button_finish);
        editText_title = (EditText)findViewById(R.id.edit_title);
        editText_text = (EditText)findViewById(R.id.edit_text);
        button_back = (Button)findViewById(R.id.button_back);
        arrow_back = (Button)findViewById(R.id.arrow_back);
        textViewTime = (TextView)findViewById(R.id.time_show);
        Community= (Spinner)findViewById(R.id.community);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        add_time_tabbar = (FloatingActionButton)findViewById(R.id.add_time_fab);
        add_time_tabbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        //.setMinDate(minDate)
                        //.setMaxDate(maxDate)
                        //.setIs24HourTime(true)
                        //.setTheme(SlideDateTimePicker.HOLO_DARK)
                        //.setIndicatorColor(Color.parseColor("#990000"))
                        .build()
                        .show();
            }
        });

        Intent intent = getIntent();
        position = intent.getIntExtra("posit", -1);

        dbHelper = new MyDatabaseHelper(AddNote.this, "BookStore.db", null, 1);
        SQLiteDatabase db2 = dbHelper.getWritableDatabase();
        Cursor cursor = db2.query("Book", null, null, null, null, null, null);
        if(cursor.moveToNext()){
            do{
                number = cursor.getInt(cursor.getColumnIndex("id"));
                if(number==position){
                    title = cursor.getString(cursor.getColumnIndex("title"));
                    text = cursor.getString(cursor.getColumnIndex("text"));
                    editText_title.setText(title);
                    editText_text.setText(text);
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        number++;

        //确定按钮
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = editText_title.getText().toString();
                text = editText_text.getText().toString();
                remindtime = textViewTime.getText().toString();
                community = Community.getSelectedItem().toString();

                if(position!=-1){
                    if((!title.equals(""))&&(!text.equals(""))&&(!title.equals(null))&&(!text.equals(null))){
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("id", position);
                        values.put("title", title);
                        values.put("text", text);
                        values.put("year", years);
                        values.put("month", mouths);
                        values.put("day", days);
                        values.put("week", weekers);
                        values.put("weekday", dayers);
                        values.put("area", "北理");
                        values.put("weather", "晴");
                        db.update("Book", values, "id=?", new String[] {String.valueOf(position)});
                        values.clear();
                        Toast.makeText(AddNote.this,"更新成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent("com.example.xi.notebook4.LOCAL_BROADCAST");
                        intent.putExtra("update", 1);
                        localBroadcastManager.sendBroadcast(intent);
                        AddNote.this.finish();
                    }
                    if(title.equals("")||title.equals(null)){
                        Toast.makeText(AddNote.this,"请添加事件标题", Toast.LENGTH_SHORT).show();
                    }else if(text.equals("")||text.equals(null)){
                        Toast.makeText(AddNote.this,"请添加详细描述", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if((!title.equals(""))&&(!text.equals(""))&&(!title.equals(null))&&(!text.equals(null))){
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("id", number);
                        number++;
                        values.put("title", title);
                        values.put("text", text);
                        values.put("year", years);
                        values.put("month", mouths);
                        values.put("day", days);
                        values.put("week", weekers);
                        values.put("weekday", dayers);
                        values.put("area", "北理");
                        values.put("weather", "晴");
                        db.insert("Book", null, values);
                        values.clear();
                        Toast.makeText(AddNote.this,"添加成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent("com.example.xi.notebook4.LOCAL_BROADCAST");
                        intent.putExtra("update", 1);
                        localBroadcastManager.sendBroadcast(intent);
                        AddNote.this.finish();
                    }
                    if(title.equals("")||title.equals(null)){
                        Toast.makeText(AddNote.this,"请添加事件标题", Toast.LENGTH_SHORT).show();
                    }else if(text.equals("")||text.equals(null)){
                        Toast.makeText(AddNote.this,"请添加详细描述", Toast.LENGTH_SHORT).show();
                    }
                }

                if(Variable.getUsername0().equals("123"))
                {
                    Ispublish = false;
                }
                if(Ispublish)
                {
                    final StringBuilder s = new StringBuilder("");
                    for(int i=0;i<remindtime.length();i++)
                    {
                        char c = remindtime.charAt(i);
                        if(c == ' ')
                        {
                            s.append('_');
                        } else {
                            s.append(c);
                        }
                    }
                    //发布到网上
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String url = "http://47.94.13.128:8888/accept/publish?author=" +
                                        Variable.getUsername0()+"&title="+ URLDecoder.decode(title,"UTF-8")+
                                        "&description="+URLDecoder.decode(text,"UTF-8")+
                                        "&remindtime="+URLDecoder.decode(s.toString(),"UTF-8")+
                                        "&community="+URLDecoder.decode(community,"UTF-8");
                                URL url1 =  new URL(url);
                                if(url1!=null)
                                {
                                    HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
                                    connection.setRequestMethod("GET");
                                    connection.setConnectTimeout(8000);
                                    //接受
                                    if(connection.getResponseCode()==200)
                                    {
                                        System.out.println("success");
                                        //成功发送
                                    }
                                }
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

            }
        });

        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AddNote.this.finish();
            }
        });

        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNote.this.finish();
            }
        });



        switch_tabbar = (Switch)findViewById(R.id.switch_tabbar);
        switch_tabbar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    Ispublish = true;
                }else{
                    Ispublish = false;
                }
            }
        });
    }
}
