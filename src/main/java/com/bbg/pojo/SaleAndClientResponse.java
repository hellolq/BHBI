package com.bbg.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H1N1 on 2018/8/27.
 */
public class SaleAndClientResponse {

    List<String> time = new ArrayList<>();//时间轴
    List<String> timedb = new ArrayList<>();//时间轴
    List<Double> xsje = new ArrayList<>();//销售金额
    List<Double> xsjedb = new ArrayList<>();//销售金额
    List<Double> hyxs = new ArrayList<>();//会员销售
    List<Double> hyxsdb = new ArrayList<>();//会员销售
    List<Integer> xsbs = new ArrayList<>();//交易笔数
    List<Integer> xsbsdb = new ArrayList<>();//交易笔数
    List<Integer> kll = new ArrayList<>();//客流量
    List<Integer> klldb = new ArrayList<>();//客流量

    public List<String> getTimedb() {
        return timedb;
    }

    public void setTimedb(List<String> timedb) {
        this.timedb = timedb;
    }

    public List<Double> getXsjedb() {
        return xsjedb;
    }

    public void setXsjedb(List<Double> xsjedb) {
        this.xsjedb = xsjedb;
    }

    public List<Double> getHyxsdb() {
        return hyxsdb;
    }

    public void setHyxsdb(List<Double> hyxsdb) {
        this.hyxsdb = hyxsdb;
    }

    public List<Integer> getXsbsdb() {
        return xsbsdb;
    }

    public void setXsbsdb(List<Integer> xsbsdb) {
        this.xsbsdb = xsbsdb;
    }

    public List<Integer> getKlldb() {
        return klldb;
    }

    public void setKlldb(List<Integer> klldb) {
        this.klldb = klldb;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Double> getXsje() {
        return xsje;
    }

    public void setXsje(List<Double> xsje) {
        this.xsje = xsje;
    }

    public List<Double> getHyxs() {
        return hyxs;
    }

    public void setHyxs(List<Double> hyxs) {
        this.hyxs = hyxs;
    }

    public List<Integer> getXsbs() {
        return xsbs;
    }

    public void setXsbs(List<Integer> xsbs) {
        this.xsbs = xsbs;
    }

    public List<Integer> getKll() {
        return kll;
    }

    public void setKll(List<Integer> kll) {
        this.kll = kll;
    }
}
