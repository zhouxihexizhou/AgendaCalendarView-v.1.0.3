package com.example.xi.notebook4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.xi.notebook4.Overall.Variable;

/**
 * Created by xi on 2017/11/19.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private FragmentOne fragmentOne;

    private BeforeLogin beforeLogin;

    private AfterLogin afterLogin;

    private FrameLayout frameLayout_one;

    private FrameLayout frameLayout_two;

    private FrameLayout frameLayout_three;

    private ImageView imageView_one;

    private ImageView imageView_two;

    private ImageView imageView_three;

    private MyDatabaseHelper dbHelper;

    private IntentFilter intentFilter;

    private LocalReceiver localReceiver;

    private LocalBroadcastManager localBroadcastManager;

    private Boolean login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        clickOne();

        login = Variable.getlogined();
        if(login){
            clickAfter();
        }else{
            clickBefore();
        }

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        dbHelper.getWritableDatabase();

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.xi.notebook4.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
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
        frameLayout_one.setOnClickListener(this);
        frameLayout_two.setOnClickListener(this);
        frameLayout_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_one:clickOne();break;
            case R.id.layout_two:clickTwo();break;
            case R.id.layout_three:clickThree();break;
        }
    }

    public void clickOne(){
        fragmentOne = new FragmentOne();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content, fragmentOne);
        fragmentTransaction.commit();

        frameLayout_one.setSelected(true);
        imageView_one.setSelected(true);

        imageView_two.setSelected(false);

        imageView_three.setSelected(false);

    }

    private void clickTwo(){
        Intent intent = new Intent(MainActivity.this, FragmentTwo.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_change, R.anim.activity_change);
        MainActivity.this.finish();
    }

    private void clickThree(){
        if(Variable.getUsername0().equals("123"))
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, FragmentThree.class);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_change, R.anim.activity_change);
            MainActivity.this.finish();
        }
    }

    private void clickBefore(){
        beforeLogin = new BeforeLogin();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content2, beforeLogin);
        fragmentTransaction.commit();
    }

    private void clickAfter(){
        afterLogin = new AfterLogin();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_content2, afterLogin);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Dialog dialog = new AlertDialog.Builder(MainActivity.this)
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
                        MainActivity.this.finish();
                    }
                })
                .create();
        dialog.show();
    }

    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int check=0;
            check = intent.getIntExtra("update", -1);
            if(check==1){
                MainActivity.this.recreate();
            }else{
                Variable.setlogined(true);
                MainActivity.this.recreate();
            }
        }
    }

}

