package com.bfchengnuo.basic;

import java.math.BigInteger;
import java.util.*;

/**
 * 关于方法的相关基本知识点
 * 可变参数重载、泛型擦除 - 语法糖解析
 *
 * @author 冰封承諾Andy
 * @date 2020/4/22
 */
public class Methods {
    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };
        // 测试精确匹配，重载是静态匹配，与运行时实际类型无关
        for (Collection<?> c : collections) {
            System.out.println(classify(c));
        }

        // 数组其实也是继承 Object 的，所以它更具体
        confusing(null);
    }

    public static void confusing(Object o) {
        System.out.println("Object");
    }
    public static void confusing(double[] dArray) {
        System.out.println("double array");
    }

    public static String classify(Set<?> s) {
        return "Set";
    }
    public static String classify(List<?> lst) {
        return "List";
    }
    public static String classify(Collection<?> c) {
        return "Unknown Collection";
    }

    /**
     * 可变参重载与数组是一样的，编译不通过
     *
     * @param ints
     */
    public void overloading(int... ints){

    }
    // public void overloading(int[] ints){}

    /**
     * 由于泛型的擦除，所以下面的是不合法的；
     * 但是如果返回值不同，对于部分编译器却是合法的，这个在其他类型是无效的，因为返回值不属于签名（对 JVM 来说算）
     * 对于 IDEA 使用的编译器来说，不允许
     */
    public static String genericOver(List<Integer> arg){return "";}
    // public static void genericOver(List<String> arg){}
    // public static int genericOver(List<String> arg){return 0;}
}
