//package com.example.xi.notebook4.Util;
//
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//
///**
// * Created by Benny on 2017/12/3.
// */
//
//public class  HttpUtil {
//    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){//get请求
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(address)
//                .build();
//        client.newCall(request).enqueue(callback);//发送请求，并获取服务器返回的数据
//    }
//
//    public static void sendOKHttp(String address, Object RequestJsonbean,okhttp3.Callback callback){
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        OkHttpClient client =new OkHttpClient();
//        String json=GsonTools.createJsonString(RequestJsonbean);
//        RequestBody requestBody = RequestBody.create(JSON,json);
//        Request request = new Request.Builder()
//                .url(address)
//                .post(requestBody)
//                .build();
//        client.newCall(request).enqueue(callback);//发送请求，并获取服务器返回的数据
//    }
//}
