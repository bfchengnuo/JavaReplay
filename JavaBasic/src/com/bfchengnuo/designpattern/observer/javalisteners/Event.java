package com.bfchengnuo.designpattern.observer.javalisteners;

import java.util.EventObject;

/**
 * Created by 冰封承諾Andy on 5/19/2017.
 * 事件对象，使用 Java 提供的类
 * 需要继承 EventObject
 */
public class Event extends EventObject {
    private String status;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public Event(Object source) {
        super(source);
    }

    public Event(Object source, String status) {
        super(source);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
