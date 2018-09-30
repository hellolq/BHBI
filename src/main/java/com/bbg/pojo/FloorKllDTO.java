package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/9/29.
 */
public class FloorKllDTO {

    private String shopId;
    private String shopName;
    private String floorName;
    private String doorId;
    private String doorName;
    private double    incount;
    private double    incountCom;
    private String  growth;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public double getIncount() {
        return incount;
    }

    public void setIncount(double incount) {
        this.incount = incount;
    }

    public double getIncountCom() {
        return incountCom;
    }

    public void setIncountCom(double incountCom) {
        this.incountCom = incountCom;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }
}
