package com.example.xi.notebook4.Util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载图片辅助类
 * Created by Benny on 2017/12/3.
 */

/*
*  TODO:   从JSP端获得图片    在activity中写
    Handler handler2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x999){
                //get byte form ReadFromJSP from Jsp Servlet
                byte[] b=new ReadFromJSP("http://192.168.1.115:8080/HttpURLPro/Pic/Sky.png").PicFromJSP();
                Bitmap bitmap=BitmapFactory.decodeByteArray(b,0,b.length);
                getForm.setImageBitmap(bitmap);
//                intoThisPhone2(bitmap);
                Log.e("629------","出现629行");
            }
        }
    };
* */

public class ReadFromJSP {
    String url;
    public ReadFromJSP(String url){
        this.url=url;
    }

    public byte[] PicFromJSP(){
        try {
            URL urll =  new URL(url);
            try {
                HttpURLConnection urlConnection=(HttpURLConnection) urll.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setUseCaches(false);
                InputStream inputStream=urlConnection.getInputStream();
                Log.e("31---------","31hangchuxian");
                return getPic(inputStream);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getPic(InputStream in){
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        int len=-1;
        byte[] b=new byte[1024*4];
        try {
            while ((len = in.read(b)) !=-1){
                out.write(b,0,len);
            }
            return b;
        }catch (Exception e){
           Log.v("----------","error");
        }
        return null;
    }
}
