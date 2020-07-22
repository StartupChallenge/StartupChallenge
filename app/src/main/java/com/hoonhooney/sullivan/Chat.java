package com.hoonhooney.sullivan;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat {

    private boolean isUser;
    private String message;
    private String time;

    @SuppressLint("SimpleDateFormat")
    public Chat(boolean isUser, String message){
        this.isUser = isUser;
        this.message = message;
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    public boolean isUser() {
        return isUser;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

}
