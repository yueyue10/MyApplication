package com.example.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zhaoyuejun on 2018/6/1.
 */
@Entity
public class JsonStrData {
    @Id(autoincrement = true)
    private Long id;
    private String socket_msg;
    @Generated(hash = 1850829454)
    public JsonStrData(Long id, String socket_msg) {
        this.id = id;
        this.socket_msg = socket_msg;
    }
    @Generated(hash = 1904313559)
    public JsonStrData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSocket_msg() {
        return this.socket_msg;
    }
    public void setSocket_msg(String socket_msg) {
        this.socket_msg = socket_msg;
    }

    @Override
    public String toString() {
        return "JsonStrData{" +
                "id=" + id +
                ", socket_msg='" + socket_msg + '\'' +
                '}';
    }
}
