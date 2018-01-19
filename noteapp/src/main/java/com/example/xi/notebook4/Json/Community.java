package com.example.xi.notebook4.Json;

/**
 * 发布到社区时的jason包
 * Created by Benny on 2017/12/1.
 */

public class Community {
    private String author;//用户名
    private String title;//标题
    private String description;//描述
    private String remindtime;//提醒时间
    private String community;//发布的社区

    public Community(String author, String title, String description, String remindtime, String community)
    {
        super();
        this.author = author;
        this.title = title;
        this.description = description;
        this.remindtime = remindtime;
        this.community = community;
    }

    public Community getCommunity(){return new Community(author,title,description,remindtime,community); }

    public String toString(){
       return "Community [author="+author+", title="+title+ ", description=" +description
                + ",remindtime="+remindtime+",community="+community+"]";
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRemindtime(String remindtime) {
        this.remindtime = remindtime;
    }

    public void setcommunity(String community) {
        this.community = community;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRemindtime() {
        return remindtime;
    }

    public String getcommunity() {
        return community;
    }
}
