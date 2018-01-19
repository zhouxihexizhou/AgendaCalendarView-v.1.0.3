package com.example.xi.notebook4.Overall;

/**
 * Created by xi on 2017/12/11.
 */

public class Variable {

    private static String username0 = "123";

    private static String school0 = null;

    private static String interest0 = null;

    private static Boolean logined = false;

    public Variable()
    {
        username0 = null;

        school0 = null;

        interest0 = null;

        logined = false;
    }

    public static String getInterest0() {
        return interest0;
    }

    public static void setInterest0(String interest0) {
        Variable.interest0 = interest0;
    }

    public static String getSchool0() {
        return school0;
    }

    public static void setSchool0(String school0) {
        Variable.school0 = school0;
    }

    public static String getUsername0() {
        return username0;
    }

    public static void setUsername0(String username0) {
        Variable.username0 = username0;
    }

    public static boolean getlogined() {
        return logined;
    }

    public static void setlogined(Boolean logined) {
        Variable.logined = logined;
    }
}
