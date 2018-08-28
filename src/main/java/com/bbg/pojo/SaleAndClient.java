package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/8/24.
 */
public class SaleAndClient {

    private String shopId;
    private String shopName;
    private String yt;
    private String sq;
    private double xsje;
    private double hyxs;
    private int kll;
    private int xsbs;
    private String zfrq;
    private String xsjekbRate;//可比销售增长
    private String dcRate;//达成
    private String mll;//毛利率
    private int gmv;

    public String getXsjekbRate() {
        return xsjekbRate;
    }

    public void setXsjekbRate(String xsjekbRate) {
        this.xsjekbRate = xsjekbRate;
    }

    public String getDcRate() {
        return dcRate;
    }

    public void setDcRate(String dcRate) {
        this.dcRate = dcRate;
    }

    public String getMll() {
        return mll;
    }

    public void setMll(String mll) {
        this.mll = mll;
    }

    public int getGmv() {
        return gmv;
    }

    public void setGmv(int gmv) {
        this.gmv = gmv;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getSq() {
        return sq;
    }

    public void setSq(String sq) {
        this.sq = sq;
    }

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

    public double getXsje() {
        return xsje;
    }

    public void setXsje(double xsje) {
        this.xsje = xsje;
    }

    public double getHyxs() {
        return hyxs;
    }

    public void setHyxs(double hyxs) {
        this.hyxs = hyxs;
    }

    public int getKll() {
        return kll;
    }

    public void setKll(int kll) {
        this.kll = kll;
    }

    public int getXsbs() {
        return xsbs;
    }

    public void setXsbs(int xsbs) {
        this.xsbs = xsbs;
    }

    public String getZfrq() {
        return zfrq;
    }

    public void setZfrq(String zfrq) {
        this.zfrq = zfrq;
    }

    @Override
    public String toString() {
        return "SaleAndClient{" +
                "shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", yt='" + yt + '\'' +
                ", sq='" + sq + '\'' +
                ", xsje=" + xsje +
                ", hyxs=" + hyxs +
                ", kll=" + kll +
                ", xsbs=" + xsbs +
                ", zfrq='" + zfrq + '\'' +
                ", xsjekbRate='" + xsjekbRate + '\'' +
                ", dcRate='" + dcRate + '\'' +
                ", mll='" + mll + '\'' +
                ", gmv=" + gmv +
                '}';
    }
}
