package com.bfchengnuo.designpattern.decorator;

/**
 * Created by 冰封承諾Andy on 5/4/2017.
 * 装饰模式测试类
 */
public class MainTest {
    public static void main(String[] args) {
        // 选择了标准 Loli
        Loli loli = new LegitimateLoli();

        //对标准 Loli 进行了装饰（增加功能）
        loli = new LovelyLoli(loli);

        loli.hug();
        System.out.println("----------");
        System.out.println(loli.speak("bfchengnuo"));
    }
}
