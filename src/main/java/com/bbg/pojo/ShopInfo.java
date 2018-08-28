package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/8/27.
 */
public class ShopInfo {

    private String shopId;//门店ID
    private String shopName;//门店名称
    private String sq;//省区
    private String bjYt;//业态

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

    public String getSq() {
        return sq;
    }

    public void setSq(String sq) {
        this.sq = sq;
    }

    public String getBjYt() {
        return bjYt;
    }

    public void setBjYt(String bjYt) {
        this.bjYt = bjYt;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", sq='" + sq + '\'' +
                ", bjYt='" + bjYt + '\'' +
                '}';
    }
}
