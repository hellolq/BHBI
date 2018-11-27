package com.bbg.pojo;

import java.util.List;

/**
 * Created by H1N1 on 2018/11/27.
 */
public class BfclDTO {

    private String data;
    private String member;
    private String storeCode;

    private String traceId;//请求编号
    private String dhd;//订单号
    private String goodsId;//商品ID
    private String goodName;//商品名称
    private int dhsl;//订货数量
    private double dhje;//订货金额

    private String shopId;//门店id
    private String dhrq;//订货日期
    private String status;//订单状态
    private String clbj;//处理标记
    private String clrq;//处理日期
    private String jzrq;//记账日期



    private String orderJson;//电商的订单包

    private List<BfclDTO> orders;

    public List<BfclDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<BfclDTO> orders) {
        this.orders = orders;
    }

    public String getOrderJson() {
        return orderJson;
    }

    public void setOrderJson(String orderJson) {
        this.orderJson = orderJson;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDhd() {
        return dhd;
    }

    public void setDhd(String dhd) {
        this.dhd = dhd;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getDhsl() {
        return dhsl;
    }

    public void setDhsl(int dhsl) {
        this.dhsl = dhsl;
    }

    public double getDhje() {
        return dhje;
    }

    public void setDhje(double dhje) {
        this.dhje = dhje;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getDhrq() {
        return dhrq;
    }

    public void setDhrq(String dhrq) {
        this.dhrq = dhrq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClbj() {
        return clbj;
    }

    public void setClbj(String clbj) {
        this.clbj = clbj;
    }

    public String getClrq() {
        return clrq;
    }

    public void setClrq(String clrq) {
        this.clrq = clrq;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
    @Override
    public String toString() {
        return "BfclDTO{" +
                "traceId='" + traceId + '\'' +
                ", dhd='" + dhd + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodName='" + goodName + '\'' +
                ", dhsl=" + dhsl +
                ", dhje=" + dhje +
                ", shopId='" + shopId + '\'' +
                ", dhrq='" + dhrq + '\'' +
                ", status='" + status + '\'' +
                ", clbj='" + clbj + '\'' +
                ", clrq='" + clrq + '\'' +
                ", jzrq='" + jzrq + '\'' +
                ", orderJson='" + orderJson + '\'' +
                ", orders=" + orders +
                '}';
    }
}
