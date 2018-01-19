package com.example.xi.notebook4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Card extends AppCompatActivity {

    private TextView title_text;

    private TextView text_text;

    private TextView time_text;

    private String title;

    private String text;

    private String time;

    private int number;

    private MyDatabaseHelper dbHelper;

    private Button arrow;

    private Button back;

    private Button update;

    private Button delete;

    private String year;

    private String month;

    private String day;

    private String week;

    private String weekday;

    private int position = -1;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        dbHelper = new MyDatabaseHelper(Card.this, "BookStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if(cursor.moveToNext()){
            do{
                number = cursor.getInt(cursor.getColumnIndex("id"));
                if(number==position){
                    title = cursor.getString(cursor.getColumnIndex("title"));
                    text = cursor.getString(cursor.getColumnIndex("text"));
                    year = String.valueOf(cursor.getInt(cursor.getColumnIndex("year")));
                    month = String.valueOf(cursor.getInt(cursor.getColumnIndex("month")));
                    day = String.valueOf(cursor.getInt(cursor.getColumnIndex("day")));
                    weekday = String.valueOf(cursor.getInt(cursor.getColumnIndex("weekday")));
                    time = year + "年" + month + "月" + day + "日" + "星期"+ weekday;
                    break;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        title_text = (TextView)findViewById(R.id.title_text);
        text_text = (TextView)findViewById(R.id.text_text);
        time_text = (TextView)findViewById(R.id.time_text);

        title_text.setText(title);
        text_text.setText(text);
        time_text.setText(time);

        arrow = (Button)findViewById(R.id.arrow_back);
        back = (Button)findViewById(R.id.button_back);
        update = (Button)findViewById(R.id.button_update);
        delete = (Button)findViewById(R.id.button_delete);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card.this.finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card.this.finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Card.this, AddNote.class);
                intent.putExtra("posit", position);
                startActivity(intent);
                Card.this.finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new AlertDialog.Builder(Card.this)
                        .setTitle("删除提示")
                        .setMessage("您确认要删除这条记录吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.delete("Book","id=?", new String[] {String.valueOf(position)});
                                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                                if(cursor.moveToNext()){
                                    do{
                                        number = cursor.getInt(cursor.getColumnIndex("id"));
                                        if(number>position){
                                            ContentValues values = new ContentValues();
                                            values.put("id", number-1);
                                            db.update("Book", values, "id=?", new String[] {String.valueOf(number)});
                                        }
                                    }while(cursor.moveToNext());
                                }
                                Intent intent1 = new Intent("com.example.xi.notebook4.LOCAL_BROADCAST");
                                localBroadcastManager.sendBroadcast(intent1);
                                Card.this.finish();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }
}
