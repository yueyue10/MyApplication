package com.encdata.mvp.core.bean;

/**
 * Created by zhaoyuejun on 2018/10/16.
 */

public class WorkBusData {

    /**
     * bus_name : DW08(早班)草房-望京
     * ticket_status : 明日有票
     * geton_time : 08:02
     * geton_name : 常营名族家园
     * geton_type : 途径
     * ticket_price_old : 10元
     * ticket_price_now : 9元
     * getoff_time : 08:46
     * getoff_name : 爱立信大厦
     */

    private String bus_name;
    private String ticket_status;
    private String geton_time;
    private String geton_name;
    private String geton_type;
    private String ticket_price_old;
    private String ticket_price_now;
    private String getoff_time;
    private String getoff_name;

    public String getBus_name() {
        return bus_name == null ? "" : bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getTicket_status() {
        return ticket_status == null ? "" : ticket_status;
    }

    public void setTicket_status(String ticket_status) {
        this.ticket_status = ticket_status;
    }

    public String getGeton_time() {
        return geton_time == null ? "" : geton_time;
    }

    public void setGeton_time(String geton_time) {
        this.geton_time = geton_time;
    }

    public String getGeton_name() {
        return geton_name == null ? "" : geton_name;
    }

    public void setGeton_name(String geton_name) {
        this.geton_name = geton_name;
    }

    public String getGeton_type() {
        return geton_type == null ? "" : geton_type;
    }

    public void setGeton_type(String geton_type) {
        this.geton_type = geton_type;
    }

    public String getTicket_price_old() {
        return ticket_price_old == null ? "" : ticket_price_old;
    }

    public void setTicket_price_old(String ticket_price_old) {
        this.ticket_price_old = ticket_price_old;
    }

    public String getTicket_price_now() {
        return ticket_price_now == null ? "" : ticket_price_now;
    }

    public void setTicket_price_now(String ticket_price_now) {
        this.ticket_price_now = ticket_price_now;
    }

    public String getGetoff_time() {
        return getoff_time == null ? "" : getoff_time;
    }

    public void setGetoff_time(String getoff_time) {
        this.getoff_time = getoff_time;
    }

    public String getGetoff_name() {
        return getoff_name == null ? "" : getoff_name;
    }

    public void setGetoff_name(String getoff_name) {
        this.getoff_name = getoff_name;
    }


}
