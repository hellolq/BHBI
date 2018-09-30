package com.bbg.pojo;

import java.io.Serializable;

/**
 * Created by H1N1 on 2018/9/29.
 */
public class FloorClientParam implements Serializable{

    private String shopId;
    private String startTime;
    private String endTime;
    private String startTimeCom;
    private String endTimeCom;

    public String getStartTimeCom() {
        return startTimeCom;
    }

    public void setStartTimeCom(String startTimeCom) {
        this.startTimeCom = startTimeCom;
    }

    public String getEndTimeCom() {
        return endTimeCom;
    }

    public void setEndTimeCom(String endTimeCom) {
        this.endTimeCom = endTimeCom;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
