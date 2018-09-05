package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/8/27.
 */
public class SaleAndClientRequireParam  implements  Cloneable{

    private String shopId;
    private String startTime;
    private String endTime;
    private String startTimedb;
    private String endTimedb;
    private String timeType;
    private int pnSize;
    private int pn;
    private String dbType;
    private String descTxt;

    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

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

    @Override
    public SaleAndClientRequireParam clone() {
        SaleAndClientRequireParam stu = null;
        try{
            stu = (SaleAndClientRequireParam)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }

    @Override
    public String toString() {
        return "SaleAndClientRequireParam{" +
                "shopId='" + shopId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startTimedb='" + startTimedb + '\'' +
                ", endTimedb='" + endTimedb + '\'' +
                ", timeType='" + timeType + '\'' +
                ", pnSize=" + pnSize +
                ", pn=" + pn +
                ", dbType='" + dbType + '\'' +
                ", descTxt='" + descTxt + '\'' +
                '}';
    }
}
