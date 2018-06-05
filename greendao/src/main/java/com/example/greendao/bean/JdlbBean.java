package com.example.greendao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 作者：renjiahao
 * 类描述：景点 bean
 */

public class JdlbBean implements Serializable {

    /**
     * id : 1
     * code : 1
     * name : 象鼻山
     * enName : Xiangbishan
     * type : 核心景点
     * lat : 116.9800212
     * lon : 28.0997308
     * isUsed : 1
     * resDetail : 0
     * startTime : 10:00
     * endTime : 18:00
     * suitableTime : 120
     * picUrl : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3962166214,604423966&fm=27&gp=0.jpg
     * redLangue : 播放语音
     * description : 象鼻山形似象鼻而得名
     * scode : 5.0
     * comment : 很好
     * updateTime : 1511413216000
     * crowds : [{"id":2,"name":"幼儿","code":0}]
     */


    private int id;
    private String code;
    private String name;
    private String enName;
    private String type;
    private double lat;
    private double lon;
    private int isUsed;
    private int resDetail;
    private String startTime;
    private String endTime;
    private int suitableTime;
    private String picUrl;
    private String redLangue;
    private String description;
    private float scode;
    private String comment;
    private long updateTime;
    private List<CrowdsBean> crowds;
    private int codeType;//景点类型
    private int grade;//景点级别
    //景区推荐路线
    private int aid;
    private int rid;
    private int pid;
    private int distance;
    private int isChoosed;


    private String address;
    private String tel;

    private String video;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public JdlbBean() {

    }

    public JdlbBean(String name, double lat, double lon, String picUrl) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.picUrl = picUrl;
    }

    public JdlbBean(int rid, String name, double lat, double lon, String picUrl) {
        this.rid = rid;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.picUrl = picUrl;
    }

    public JdlbBean(String name, double lat, double lon, int grade) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName == null ? "" : enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public int getResDetail() {
        return resDetail;
    }

    public void setResDetail(int resDetail) {
        this.resDetail = resDetail;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getSuitableTime() {
        return suitableTime;
    }

    public void setSuitableTime(int suitableTime) {
        this.suitableTime = suitableTime;
    }

    public String getPicUrl() {
        return picUrl == null ? "" : picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getRedLangue() {
        return redLangue == null ? "" : redLangue;
    }

    public void setRedLangue(String redLangue) {
        this.redLangue = redLangue;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getScode() {
        return scode;
    }

    public void setScode(float scode) {
        this.scode = scode;
    }

    public String getComment() {
        return comment == null ? "" : comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public List<CrowdsBean> getCrowds() {
        if (crowds == null) {
            return new ArrayList<>();
        }
        return crowds;
    }

    public void setCrowds(List<CrowdsBean> crowds) {
        this.crowds = crowds;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getIsChoosed() {
        return isChoosed;
    }

    public void setIsChoosed(int isChoosed) {
        this.isChoosed = isChoosed;
    }

    public static class CrowdsBean implements Serializable {
        /**
         * id : 2
         * name : 幼儿
         * code : 0
         */

        private int id;
        private String name;
        private int code;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
