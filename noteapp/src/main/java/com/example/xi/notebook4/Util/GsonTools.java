package com.example.xi.notebook4.Util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Benny on 2017/12/3.
 */

public class GsonTools {

    /**TODO 转换为json字符串
     * @param object  要转换成json格式的 对象
     * @return
     */
    public static String createJsonString(Object object) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        return jsonString;
    }


    /**TODO 转换为指定的 对象
     * @param jsonString
     * @param type 指定对象的类型 ，即 T.class
     */
    public static <T> T getObject(String jsonString, Class<T> type) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    /**得到 一个List<T>集合
     * @param jsonString
     * @param type  T的类型
     * @return
     */
    public static <T> List<T> getList(String jsonString, Class<T> type) {
        List<T> list = new ArrayList<T>();
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;

    }

    /**TODO 得到一个List<T> 的集合
     * @param jsonString json字符串
     * @param type  数组的类型 ，即T[].class
     * @return
     */
    public static <T> List<T> StringTolist(String jsonString, Class<T[]> type) {
        T[] list = null;
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, type);
        } catch (JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Arrays.asList(list);

    }
    /**把json字符串转换为 String 集合
     * @param jsonString
     * @return
     */
    public static List<String> getStrings(String jsonString) {
        List<String> list = new ArrayList<String>();
        Gson gson = new Gson();
        new TypeToken<List<String>>(){}.getType();
        list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
        }.getType());
        return list;

    }

    /**TODO 将json数据解析为Map<String,Object>集合
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getMaps(String jsonString) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Gson gson = new Gson();
        list = gson.fromJson(jsonString,
                new TypeToken<List<Map<String, Object>>>() {
                }.getType());

        return list;

    }


}

