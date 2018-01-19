package com.example.xi.notebook4.Json;

/**
 * Created by Benny on 2017/12/6.
 */

public class UploadHeadshot {
    private String username;
    private String picture_path;

    public  UploadHeadshot(String username,String picture_path){
        super();
        this.username = username;
        this.picture_path = picture_path;
    }
    public UploadHeadshot getUploadHeadshot(){return new UploadHeadshot(username,picture_path);};

    public String toString(){
        return "Signup [username=" + username + ", picture_path=" + picture_path + "]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }
}
