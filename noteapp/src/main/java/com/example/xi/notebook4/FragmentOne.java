package com.example.xi.notebook4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xi on 2017/11/19.
 */

public class FragmentOne extends android.support.v4.app.Fragment {

    private View view;

    private String title;

    private String text;

    private String time;

    private MyDatabaseHelper dbHelper;

    private Note note;

    private List<Note> noteList = new ArrayList<>();

    private NoteAdapter adapter;

    private String year;

    private String month;

    private String day;

    private String week;

    private String weekday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_one, container, false);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddNote.class);
                startActivity(intent);
            }
        });

        dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if(cursor.moveToNext()){
            do{
                title = cursor.getString(cursor.getColumnIndex("title"));
                if(title.length()>8){
                    title = title.substring(0, 8);
                }
                text = cursor.getString(cursor.getColumnIndex("text"));
                if(text.length()>11){
                    text = text.substring(0, 11);
                }
                year = String.valueOf(cursor.getInt(cursor.getColumnIndex("year")));
                month = String.valueOf(cursor.getInt(cursor.getColumnIndex("month")));
                day = String.valueOf(cursor.getInt(cursor.getColumnIndex("day")));
                weekday = String.valueOf(cursor.getInt(cursor.getColumnIndex("weekday")));
                switch(weekday){
                    case "1":weekday="一";break;
                    case "2":weekday="二";break;
                    case "3":weekday="三";break;
                    case "4":weekday="四";break;
                    case "5":weekday="五";break;
                    case "6":weekday="六";break;
                    case "7":weekday="日";break;
                }
                time = year + "年" + month + "月" + day + "日" + "周"+ weekday;
                note = new Note(title, text, R.drawable.clock, time);
                noteList.add(note);
            }while(cursor.moveToNext());
        }
        cursor.close();

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener(){
            @Override
            public void OnClick(int position) {
                Intent intent = new Intent(getContext(), Card.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        return view;
    }

}
