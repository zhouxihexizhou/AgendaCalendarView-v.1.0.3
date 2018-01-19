package com.example.xi.notebook4.Json;

/**
 * 登录时的jason包
 * Created by Benny on 2017/12/5.
 */

public class Signup {
    private String username;//用户名
    private String password;//密码

    public Signup(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public Signup getSignup(){
        return new Signup(username,password);
    }

    public String toString(){
        return "Signup [username=" + username + ", password=" + password + "]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
