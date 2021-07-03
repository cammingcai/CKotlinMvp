package com.example.kotlinmvp.model.news;

import java.util.List;

/**
 * Create by Cabbage on 2021/7/3.
 */
public class NewsBean {

    private String stat;
    private List<NewsData> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<NewsData> getData() {
        return data;
    }

    public void setData(List<NewsData> data) {
        this.data = data;
    }
}