package com.example.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 推荐路线bean
 * Created by zhaoyuejun on 2018/3/20.
 */
@Entity
public class RecomdRouteData {

    @Id(autoincrement = true)
    private Long id;
    private int state;
    private double lat;
    private double lon;
    private int viewId;
    private String name;
    private int startId;
    private int touristId;
    private String lineId;
    private String comment;
    private int strengthId;
    private int tourTypeId;
    private String coverPic;
    private String startTime;
    private String startName;
    private String updateTime;
    private String peopleCount;
    private String startTimeStr;
    @Generated(hash = 1433349018)
    public RecomdRouteData(Long id, int state, double lat, double lon, int viewId,
                           String name, int startId, int touristId, String lineId, String comment,
                           int strengthId, int tourTypeId, String coverPic, String startTime,
                           String startName, String updateTime, String peopleCount,
                           String startTimeStr) {
        this.id = id;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
        this.viewId = viewId;
        this.name = name;
        this.startId = startId;
        this.touristId = touristId;
        this.lineId = lineId;
        this.comment = comment;
        this.strengthId = strengthId;
        this.tourTypeId = tourTypeId;
        this.coverPic = coverPic;
        this.startTime = startTime;
        this.startName = startName;
        this.updateTime = updateTime;
        this.peopleCount = peopleCount;
        this.startTimeStr = startTimeStr;
    }
    @Generated(hash = 2017732161)
    public RecomdRouteData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getState() {
        return this.state;
    }
    public void setState(int state) {
        this.state = state;
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
    public int getViewId() {
        return this.viewId;
    }
    public void setViewId(int viewId) {
        this.viewId = viewId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStartId() {
        return this.startId;
    }
    public void setStartId(int startId) {
        this.startId = startId;
    }
    public int getTouristId() {
        return this.touristId;
    }
    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }
    public String getLineId() {
        return this.lineId;
    }
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getStrengthId() {
        return this.strengthId;
    }
    public void setStrengthId(int strengthId) {
        this.strengthId = strengthId;
    }
    public int getTourTypeId() {
        return this.tourTypeId;
    }
    public void setTourTypeId(int tourTypeId) {
        this.tourTypeId = tourTypeId;
    }
    public String getCoverPic() {
        return this.coverPic;
    }
    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getStartName() {
        return this.startName;
    }
    public void setStartName(String startName) {
        this.startName = startName;
    }
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getPeopleCount() {
        return this.peopleCount;
    }
    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }
    public String getStartTimeStr() {
        return this.startTimeStr;
    }
    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

}
