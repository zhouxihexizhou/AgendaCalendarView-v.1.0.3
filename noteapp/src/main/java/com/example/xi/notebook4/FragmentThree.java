package com.example.xi.notebook4;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xi.notebook4.Overall.Variable;

public class FragmentThree extends AppCompatActivity {

    private BeforeLogin beforeLogin;

    private FrameLayout frameLayout_one;

    private FrameLayout frameLayout_two;

    private FrameLayout frameLayout_three;

    private ImageView imageView_one;

    private ImageView imageView_two;

    private ImageView imageView_three;

    WebView mWebview;
    WebSettings mWebSettings;
    TextView beginLoading,endLoading,loading,mtitle;
    @SuppressLint("NewApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        initView();

        initData();

        clickBefore();

        mWebview = (WebView) findViewById(com.example.carson_ho.webview_demo.R.id.webView1);
        beginLoading = (TextView) findViewById(com.example.carson_ho.webview_demo.R.id.text_beginLoading);
        endLoading = (TextView) findViewById(com.example.carson_ho.webview_demo.R.id.text_endLoading);
        loading = (TextView) findViewById(com.example.carson_ho.webview_demo.R.id.text_Loading);
        mtitle = (TextView) findViewById(com.example.carson_ho.webview_demo.R.id.title);

        mWebSettings = mWebview.getSettings();
        setSettings(mWebSettings);

        mWebview.loadUrl("http://47.94.13.128:8888/accept/bitDays?username="+ Variable.getUsername0());
        mWebview.setLayerType(WebView.LAYER_TYPE_HARDWARE, null);

        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });




    }

    @SuppressLint("NewApi")
    private void setSettings(WebSettings setting) {
        setting.setJavaScriptEnabled(true);
        setting.setBuiltInZoomControls(true);
        setting.setDisplayZoomControls(false);
        setting.setSupportZoom(true);

        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        // 全屏显示
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
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
                Intent intent = new Intent(FragmentThree.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_change, R.anim.activity_change);
                FragmentThree.this.finish();
            }
        });

        imageView_one.setSelected(false);

        imageView_two.setSelected(false);

        imageView_three.setSelected(true);

        frameLayout_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentThree.this, FragmentTwo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_change, R.anim.activity_change);
                FragmentThree.this.finish();
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
        Dialog dialog = new AlertDialog.Builder(FragmentThree.this)
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
                        FragmentThree.this.finish();
                    }
                })
                .create();
        dialog.show();
    }
}
