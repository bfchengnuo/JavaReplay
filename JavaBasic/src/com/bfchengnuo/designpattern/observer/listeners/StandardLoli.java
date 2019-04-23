package com.bfchengnuo.designpattern.observer.listeners;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * 事件源，感觉像被观察者
 * 事件源上发生操作的时候，会调用事件监听器里的一个方法，并在调用这个方法的时候会传递事件对象过来，事件监听器由开发人员编写
 */
public class StandardLoli {
    private String name = "佳芷";
    private LoliListeners listeners;

    // 当事件触发的时候通知相应的监听器
    public void speak(String name) {
        System.out.println("大哥哥" + name + "快来呀！！");

        if (listeners != null) {
            Event event = new Event(this);
            listeners.speakListeners(event);
        }
    }

    public void hug() {
        System.out.println("(づ｡◕‿‿◕｡)づ");
        if (listeners != null) {
            Event event = new Event(this);
            listeners.hugListeners(event);
        }
    }

    public String getName() {
        return name;
    }

    // 提供一个注册监听器的方法，由事件源对象调用
    public void registerListeners(LoliListeners listeners) {
        this.listeners = listeners;
    }
}
