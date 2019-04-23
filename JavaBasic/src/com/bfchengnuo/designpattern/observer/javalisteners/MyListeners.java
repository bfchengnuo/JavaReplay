package com.bfchengnuo.designpattern.observer.javalisteners;


/**
 * Created by 冰封承諾Andy on 5/19/2017.
 * 事件监听器的具体实现类
 */
public class MyListeners implements LoliListeners {

    @Override
    public void doSpeak(Event event) {
        // 拿到事件源对象
        StandardLoli loli = (StandardLoli) event.getSource();
        System.out.println(loli.getName() + "我来了！！");
    }

    @Override
    public void doHug(Event event) {
        String status = event.getStatus();
        System.out.println("抱抱，状态" + status);
    }
}
