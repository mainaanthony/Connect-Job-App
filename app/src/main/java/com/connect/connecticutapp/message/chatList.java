package com.connect.connecticutapp.message;

public class chatList {

    private String mobile , name , message , date, time;


    public chatList(String mobile, String name, String message, String date, String time) {
        this.mobile = mobile;
        this.name = name;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
