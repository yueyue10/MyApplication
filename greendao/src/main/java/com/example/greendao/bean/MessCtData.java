package com.example.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 精品推荐返回三家餐厅bean
 */
@Entity
public class MessCtData {

    @Id
    private Long id;
    private int pid;
    private int state;
    private int radius;
    private double lat;
    private double lon;
    private int scenic;
    private int suitableTime;
    private String optimumTime;

    private int sid;
    private int grade;
    private int scode;
    private int isUsed;
    private String name;
    private String code;
    private String type;//类型
    private int codeType;
    private int distance;
    private String enName;
    private String picUrl;
    private String comment;
    private int resDetail;
    private String description;
    private String redLangue;
    private String startTime;
    private String endTime;
    private String result;
    private String video;
    @Generated(hash = 1013873934)
    public MessCtData(Long id, int pid, int state, int radius, double lat,
                      double lon, int scenic, int suitableTime, String optimumTime, int sid,
                      int grade, int scode, int isUsed, String name, String code, String type,
                      int codeType, int distance, String enName, String picUrl,
                      String comment, int resDetail, String description, String redLangue,
                      String startTime, String endTime, String result, String video) {
        this.id = id;
        this.pid = pid;
        this.state = state;
        this.radius = radius;
        this.lat = lat;
        this.lon = lon;
        this.scenic = scenic;
        this.suitableTime = suitableTime;
        this.optimumTime = optimumTime;
        this.sid = sid;
        this.grade = grade;
        this.scode = scode;
        this.isUsed = isUsed;
        this.name = name;
        this.code = code;
        this.type = type;
        this.codeType = codeType;
        this.distance = distance;
        this.enName = enName;
        this.picUrl = picUrl;
        this.comment = comment;
        this.resDetail = resDetail;
        this.description = description;
        this.redLangue = redLangue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.result = result;
        this.video = video;
    }
    @Generated(hash = 224101700)
    public MessCtData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getPid() {
        return this.pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getRadius() {
        return this.radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public double getLat() {
        return this.lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return this.lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public int getScenic() {
        return this.scenic;
    }
    public void setScenic(int scenic) {
        this.scenic = scenic;
    }
    public int getSuitableTime() {
        return this.suitableTime;
    }
    public void setSuitableTime(int suitableTime) {
        this.suitableTime = suitableTime;
    }
    public String getOptimumTime() {
        return this.optimumTime;
    }
    public void setOptimumTime(String optimumTime) {
        this.optimumTime = optimumTime;
    }
    public int getSid() {
        return this.sid;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public int getGrade() {
        return this.grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getScode() {
        return this.scode;
    }
    public void setScode(int scode) {
        this.scode = scode;
    }
    public int getIsUsed() {
        return this.isUsed;
    }
    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCodeType() {
        return this.codeType;
    }
    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }
    public int getDistance() {
        return this.distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public String getEnName() {
        return this.enName;
    }
    public void setEnName(String enName) {
        this.enName = enName;
    }
    public String getPicUrl() {
        return this.picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getResDetail() {
        return this.resDetail;
    }
    public void setResDetail(int resDetail) {
        this.resDetail = resDetail;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getRedLangue() {
        return this.redLangue;
    }
    public void setRedLangue(String redLangue) {
        this.redLangue = redLangue;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getResult() {
        return this.result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getVideo() {
        return this.video;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    

}
