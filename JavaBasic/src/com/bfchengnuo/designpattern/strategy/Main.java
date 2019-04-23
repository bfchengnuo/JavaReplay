package com.bfchengnuo.designpattern.strategy;

/**
 * Created by 冰封承諾Andy on 5/10/2017.
 * 策略模式
 */
public class Main {
    public static void main(String[] args) {
        MakeFriends makeFriends;

        makeFriends = new MakeFriends(new StandardLoli());
        makeFriends.start();

        System.out.println("---------------------------------");

        makeFriends = new MakeFriends(new LegitimateLoli());
        makeFriends.start();
    }
}
