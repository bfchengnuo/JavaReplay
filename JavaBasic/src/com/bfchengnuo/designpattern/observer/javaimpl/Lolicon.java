package com.bfchengnuo.designpattern.observer.javaimpl;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * 使用 java 自带的类实现
 * 观察者--需要实现 Observer
 * 然后在 update 方法里写更新逻辑即可
 */
public class Lolicon implements Observer {
    private String status = "我来了！！！";

    public Lolicon() {
    }

    public Lolicon(String status) {
        this.status = status;
    }

    // 会传递过来被观察者的对象实体，和数据（如果有的话）
    @Override
    public void update(Observable o, Object arg) {
        // 更新自己.....
        StandardLoli loli = (StandardLoli) o;
        System.out.println(status + loli.getName());
    }
}
