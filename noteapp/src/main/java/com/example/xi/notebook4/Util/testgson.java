package com.example.xi.notebook4.Util;//package com.example.xi.notebook4.Util;
//
//import com.example.benny.Json.Signin;
//import com.example.benny.Util.DataUtil;
//import com.example.benny.Util.GsonTools;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Benny on 2017/12/3.
// */
//
//public class testgson{
//    public static void main(String[] args) {
//        String jsonString;
//        jsonString = GsonTools.createJsonString(DataUtil.getUser());
//        System.out.println("-------------------转化为Json字符串------一个Siginin------------");
//        System.out.println(jsonString);
//        System.out.println("-------------------解析后1------------------");
//        Signin signin = GsonTools.getObject(jsonString,Signin.class);
//        System.out.println(signin.toString());
//
//        System.out.println("-------------------转化为Json字符串2-----list-Siginin-------------");
//        jsonString = GsonTools.createJsonString(DataUtil.getUsers());
//        System.out.println(jsonString);
//        System.out.println("-------------------解析后2------------------");
//        List<Signin> list = GsonTools.getList(jsonString,Signin.class);
//        System.out.println(list.toString());
//
//
//        System.out.println("-------------------转化为Json字符串3-----string-list-------------");
//        jsonString = GsonTools.createJsonString(DataUtil.getStrings());
//        System.out.println(jsonString);
//        System.out.println("-------------------解析后3------------------");
//        List<String> list2 = GsonTools.getStrings(jsonString);
//        System.out.println(list2.toString());
//
//
//        System.out.println("-------------------转化为Json字符串4------------------");
//        jsonString = GsonTools.createJsonString(DataUtil.getMaps());
//        System.out.println(jsonString);
//        System.out.println("-------------------解析后4------------------");
//        List<Map<String, Object>> list3 = GsonTools.getMaps(jsonString);
//        System.out.println(list3.toString());
//    }
//
//}
