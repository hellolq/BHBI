package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/9/3.
 */
public class IndexTable {

    private String shopId;//门店ID
    private String shopName;//门店名称
    private String shopYt;//业态
    private String kbType;//是否为可比门店

    //销售
    private double xsje;//本期销售金额
    private double xsjeTq;//同期销售金额
    private String  xszz;//销售增长

    //交易笔数
    private double jybs;//本期交易笔数
    private double jybsTq;//交易笔数同期
    private String jybsZz;//交易笔数增长

    //客流
    private double kll;//本期客流
    private double kllTq;//同期客流
    private String  kllZz;//客流量增长

    //客单
    private double kdj;//客单价
    private double kdjTq;//客单价同期
    private String kdjZz;//客单价增长

    //描述
    private String descTxt;//描述

    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
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

    public String getShopYt() {
        return shopYt;
    }

    public void setShopYt(String shopYt) {
        this.shopYt = shopYt;
    }

    public String getKbType() {
        return kbType;
    }

    public void setKbType(String kbType) {
        this.kbType = kbType;
    }

    public double getXsje() {
        return xsje;
    }

    public void setXsje(double xsje) {
        this.xsje = xsje;
    }

    public double getXsjeTq() {
        return xsjeTq;
    }

    public void setXsjeTq(double xsjeTq) {
        this.xsjeTq = xsjeTq;
    }

    public String getXszz() {
        return xszz;
    }

    public void setXszz(String xszz) {
        this.xszz = xszz;
    }

    public double getJybs() {
        return jybs;
    }

    public void setJybs(double jybs) {
        this.jybs = jybs;
    }

    public double getJybsTq() {
        return jybsTq;
    }

    public void setJybsTq(double jybsTq) {
        this.jybsTq = jybsTq;
    }

    public String getJybsZz() {
        return jybsZz;
    }

    public void setJybsZz(String jybsZz) {
        this.jybsZz = jybsZz;
    }

    public double getKll() {
        return kll;
    }

    public void setKll(double kll) {
        this.kll = kll;
    }

    public double getKllTq() {
        return kllTq;
    }

    public void setKllTq(double kllTq) {
        this.kllTq = kllTq;
    }

    public String getKllZz() {
        return kllZz;
    }

    public void setKllZz(String kllZz) {
        this.kllZz = kllZz;
    }

    public double getKdj() {
        return kdj;
    }

    public void setKdj(double kdj) {
        this.kdj = kdj;
    }

    public double getKdjTq() {
        return kdjTq;
    }

    public void setKdjTq(double kdjTq) {
        this.kdjTq = kdjTq;
    }

    public String getKdjZz() {
        return kdjZz;
    }

    public void setKdjZz(String kdjZz) {
        this.kdjZz = kdjZz;
    }
}
