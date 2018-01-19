package com.example.xi.notebook4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    private final int DISPLAY_LENGTH = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                DisplayActivity.this.startActivity(intent);
                DisplayActivity.this.finish();
            }
        }, DISPLAY_LENGTH);
    }
}
