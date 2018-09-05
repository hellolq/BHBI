package com.bbg.pojo;

/**
 * Created by H1N1 on 2018/9/4.
 */
public class TransTime {

    /***
     * 进行 周 月 季 年查询的辅助工具类
     * */
    private String title;
    private String startTime;
    private String endTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    public TransTime() {

    }
    public TransTime(String title, String startTime, String endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
