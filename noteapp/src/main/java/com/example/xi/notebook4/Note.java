package com.example.xi.notebook4;

/**
 * Created by xi on 2017/11/22.
 */

public class Note {

    private String title;

    private String text;

    private int imageId;

    private String time;

    public Note(String title, String text, int imageId, String time){
        this.title = title;
        this.text = text;
        this.imageId = imageId;
        this.time = time;
    }

    public String getTitle(){
        return title;
    }

    public String getText(){
        return text;
    }

    public int getImageId(){
        return imageId;
    }

    public String getTime(){
        return time;
    }

}
