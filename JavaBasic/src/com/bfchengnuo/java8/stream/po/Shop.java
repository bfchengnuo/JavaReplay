package com.bfchengnuo.java8.stream.po;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by 冰封承諾Andy on 2019/7/4.
 */
public class Shop {
    private String name;
    private Double price;

    public Shop() {
    }

    public Shop(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public static List<Shop> getList() {
        List<Shop> list = new ArrayList<>();
        list.add(new Shop("name1", 12D));
        list.add(new Shop("name2", 13D));
        list.add(new Shop("name3", 14D));
        list.add(new Shop("name4", 15D));
        list.add(new Shop("name5", 16D));
        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 便于测试的空实现
     */
    public static Shop parse(Double price) {
        return new Shop("transform", price + 1D);
    }

    /**
     * 便于测试的随意实现
     */
    public static String applyDiscount(Shop shop) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(shop.getPrice() * 10);
    }
}
