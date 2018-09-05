package com.bbg.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H1N1 on 2018/9/4.
 */
public class ClientTableDTO {

    private String title;
    private List<IndexTable> res = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IndexTable> getRes() {
        return res;
    }

    public void setRes(List<IndexTable> res) {
        this.res = res;
    }
}
