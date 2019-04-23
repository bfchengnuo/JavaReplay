package com.bfchengnuo.designpattern.observer.javaimpl;

import java.util.Observable;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * 使用 java 自带的类实现
 * 被观察者--需要继承 Observable
 */
public class StandardLoli extends Observable {
    private String name = "佳芷";

    // 此方法就当作是更新状态的方法吧
    public void speak(String name) {
        System.out.println("大哥哥" + name + "快来呀！！");

        // 标记此 Observable 对象为已改变的对象；现在 hasChanged 方法将返回 true
        super.setChanged();
        // 更新完后通知所有观察者
        // 如果 hasChanged 方法指示对象已改变，则通知其所有观察者，并调用 clearChanged 方法来指示此对象不再改变。
        // 还可以携带一个对象传递
        super.notifyObservers();
        // super.notifyObservers("data");
    }

    public String getName() {
        return name;
    }
}
