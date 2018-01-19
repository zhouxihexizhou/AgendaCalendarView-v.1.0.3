package com.example.xi.notebook4.Json;

/**
 * 注册时用户的个人信息
 * Created by Benny on 2017/12/1.
 */
public class Signin {
    private String username;//用户名，6~10数字或字母
    private String password;//密码，6~10数字或字母
    private String school;//学校
    private String interset;//兴趣
    public Signin(String username, String password, String school, String interset){
        super();
        this.username = username;
        this.password = password;
        this.school = school;
        this.interset = interset;
    }

    public Signin getSigin(){return new Signin(username,password,school,interset);}

    public String toString(){
        return "Signin [username="+username+", password="+password+ ", school=" +school
                + ",interset="+interset+"]";
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getInterset() {
        return interset;
    }

    public void setInterset(String interset) {
        this.interset = interset;
    }
}
