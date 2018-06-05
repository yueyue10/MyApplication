package com.example.greendao.bean;

/**
 * Created by zhaoyuejun on 2017/11/14.
 */

public class MessageBean {
    public String title;
    public String msg_type;
    public String msg_num = "0";

    public MessageBean(String msg_type, String title) {
        this.msg_type = msg_type;
        this.title = title;
    }

    public MessageBean(String msg_type, String title, String msg_num) {
        this.msg_type = msg_type;
        this.title = title;
        this.msg_num = msg_num;
    }
}
