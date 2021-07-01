package com.example.kotlinmvp.event;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Create by Cabbage on 2021/7/1.
 */
public class MessageEvent implements MultiItemEntity {

    private String message;
    public MessageEvent(){
    }
    public MessageEvent(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}