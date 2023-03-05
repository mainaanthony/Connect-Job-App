package com.connect.connecticutapp.chat;

public class messageList {

     private  String name, mobile , lastmessage, chatKey;


     private int unseenMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public int getUnseenMessage() {
        return unseenMessage;
    }

    public void setUnseenMessage(int unseenMessage) {
        this.unseenMessage = unseenMessage;
    }


    public String getChatKey() {
        return chatKey;
    }

    public messageList(String name, String mobile, String lastmessage, int unseenMessage, String chatKey) {
        this.name = name;
        this.mobile = mobile;
        this.lastmessage = lastmessage;
        this.unseenMessage = unseenMessage;
        this.chatKey = chatKey;
    }
}
