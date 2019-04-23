package com.bfchengnuo.designpattern.observer.listeners;

/**
 * Created by 冰封承諾Andy on 5/19/2017.
 * 事件对象
 * 事件对象中一般封装了事件源等数据
 */
public class Event {
    private StandardLoli mStandardLoli;

    public Event() {
    }

    public Event(StandardLoli standardLoli) {
        mStandardLoli = standardLoli;
    }

    public StandardLoli getStandardLoli() {
        return mStandardLoli;
    }

    public void setStandardLoli(StandardLoli standardLoli) {
        mStandardLoli = standardLoli;
    }
}
