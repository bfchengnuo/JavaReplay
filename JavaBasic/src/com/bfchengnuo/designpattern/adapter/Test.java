package com.bfchengnuo.designpattern.adapter;

/**
 * Created by 冰封承諾Andy on 2017/9/15.
 * 适配器模式
 */
public class Test {
    public static void main(String[] args) {
        Boy boy = new ReliableBoy();
        Girl girl = new LovelyGirl();
        // 使他看起来像个女孩...
        BoyAdapter boyAdapter = new BoyAdapter(boy);

        testGirl(girl);
        System.out.println("------------------");
        testGirl(boyAdapter);
    }

    /**
     * 这个方法需要一个 Girl
     * @param girl
     */
    public static void testGirl(Girl girl) {
        girl.eat(); // 先吃饭
        girl.wearDress(); // 穿衣服
        girl.prayer();  // 做祷告
    }
}
