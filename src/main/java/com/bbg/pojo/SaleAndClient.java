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
    private double xsjeQn;
    private double xsjeTb;
    private double hyxs;
    private int kll;
    private int kllQn;
    private int kllTb;
    private int xsbs;
    private int xsbsQn;
    private int xsbsTb;
    private String zfrq;
    private String xsjekbRate;//可比销售增长
    private String dcRate;//达成
    private String mll;//毛利率
    private double gmv;

    private double hyxsQn;//会员销售-去年
    private double hyxsTb;//会员销售-同比

    public double getXsjeQn() {
        return xsjeQn;
    }

    public void setXsjeQn(double xsjeQn) {
        this.xsjeQn = xsjeQn;
    }

    public double getXsjeTb() {
        return xsjeTb;
    }

    public void setXsjeTb(double xsjeTb) {
        this.xsjeTb = xsjeTb;
    }

    public int getKllQn() {
        return kllQn;
    }

    public void setKllQn(int kllQn) {
        this.kllQn = kllQn;
    }

    public int getKllTb() {
        return kllTb;
    }

    public void setKllTb(int kllTb) {
        this.kllTb = kllTb;
    }

    public int getXsbsQn() {
        return xsbsQn;
    }

    public void setXsbsQn(int xsbsQn) {
        this.xsbsQn = xsbsQn;
    }

    public int getXsbsTb() {
        return xsbsTb;
    }

    public void setXsbsTb(int xsbsTb) {
        this.xsbsTb = xsbsTb;
    }

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

    public double getGmv() {
        return gmv;
    }

    public void setGmv(double gmv) {
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
                ", xsjeQn=" + xsjeQn +
                ", xsjeTb=" + xsjeTb +
                ", hyxs=" + hyxs +
                ", kll=" + kll +
                ", kllQn=" + kllQn +
                ", kllTb=" + kllTb +
                ", xsbs=" + xsbs +
                ", xsbsQn=" + xsbsQn +
                ", xsbsTb=" + xsbsTb +
                ", zfrq='" + zfrq + '\'' +
                ", xsjekbRate='" + xsjekbRate + '\'' +
                ", dcRate='" + dcRate + '\'' +
                ", mll='" + mll + '\'' +
                ", gmv=" + gmv +
                '}';
    }
}
