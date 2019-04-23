package com.bfchengnuo.designpattern.observer.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冰封承諾Andy on 5/18/2017.
 * 具体目标(ConcreteSubject)角色，也就是被观察者
 */
public class StandardLoliImpl implements StandardLoli {
    // 用来保存所有的已注册的观察者
    // 如果要频繁的增删那要使用 LinkedList
    // 如果要多线程同步操作，要把 list 转化为安全的 Collections.synchronizedList(list);
    private List<Lolicon> list = new ArrayList<>();

    private String name = "佳芷";

    // 此方法就当作是更新状态的方法吧
    public void speak(String name) {
        System.out.println("大哥哥" + name + "快来呀！！");
        // 更新完后通知所有观察者
        notifyObservers(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public void register(Lolicon observer) {
        list.add(observer);
    }

    @Override
    public void remove(Lolicon observer) {
        list.remove(observer);
    }


    @Override
    public void notifyObservers(StandardLoliImpl data) {
        // 通知所有的已注册的观察者更新自己
        // 如果是用的 LinkedList 或者 Map 之类的要使用迭代器进行迭代
        for (Lolicon lolicon : list) {
            lolicon.update(data);
        }
    }
}
