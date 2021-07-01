package com.example.kotlinmvp.event;

/**
 * Create by Cabbage on 2021/7/1.
 */
public class MessageEvent {

    private String message;

    public MessageEvent(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}