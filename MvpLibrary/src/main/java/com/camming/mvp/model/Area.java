package com.camming.mvp.model;

/**
 * Create by Cabbage on 2021/7/1.
 */
import org.litepal.crud.LitePalSupport;
//区/县
public class Area extends LitePalSupport {
    private int id;//这个id是可以不用的，因为会默认创建一个自增的id
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

