package com.bfchengnuo.basic;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/19
 */
public class Exam {
    public static void main(String[] args) {
        // out: 1
        System.out.println(retTest());
    }

    /**
     * 具体的解释对应字节码，参考《深入理解 JVM》
     * @return
     */
    public static int retTest() {
        int x;
        try {
            x = 1;
            // 将 x 拷贝一份，当作返回值记录
            return x;
        } catch (Exception e) {
            // 同上
            x = 2;
            return x;
        } finally {
            // 修改的原始值，执行后再将之前保存的返回值拿到栈顶，然后 return
            x = 3;
        }
    }
}
