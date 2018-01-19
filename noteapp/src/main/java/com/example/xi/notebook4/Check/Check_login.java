package com.example.xi.notebook4.Check;

/**
 * Created by Benny on 2017/12/10.
 */

public class Check_login {
    //调用formCheck,true为格式正确，false为格式错误
    public static boolean formCheck(String str){
        if(str.length()<=10&&str.length()>=6){
            for(int i=0;i<str.length();i++)
            {
                if(!(str.charAt(i)>='0'&&str.charAt(i)<='9'||str.charAt(i)<='Z'&&str.charAt(i)>='A'
                        ||str.charAt(i)<='z'&&str.charAt(i)>='a'||str.charAt(i)=='_'))
                    return false;
            }
            return true;
        }
        else
            return false;
    }
}
