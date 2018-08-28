package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/8/27.
 */
public class SaleAndClientRequireParam {

    private String shopId;
    private String startTime;
    private String endTime;
    private String startTimedb;
    private String endTimedb;
    private String timeType;
    private int pnSize;
    private int pn;

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getStartTimedb() {
        return startTimedb;
    }

    public void setStartTimedb(String startTimedb) {
        this.startTimedb = startTimedb;
    }

    public String getEndTimedb() {
        return endTimedb;
    }

    public void setEndTimedb(String endTimedb) {
        this.endTimedb = endTimedb;
    }

    public int getPnSize() {
        return pnSize;
    }

    public void setPnSize(int pnSize) {
        this.pnSize = pnSize;
    }

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
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
