package com.bfchengnuo.collection;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by 冰封承諾Andy on 2019/4/29.
 *
 * Map 中对于 Key hashCode 的计算相关, 也就是 get 方法的 key 不一定是相同类型的
 *
 * hashCode() 方法是一个较重的实现，大部分都需要迭代，尽量不要依赖，参考 Objects.hash(..)
 * hash 冲突的两个方面：
 *      1.因为 hashCode 是 int 类型，如果超出了 int 的数据范围就会很大机率有冲突
 *      2.hash 本身算法问题，有一定机率的重码率
 *
 * 因此，如果重写 hashCode 方法必须重写 equals； 如果重写 equals 并不强制覆盖 hashCode，但是建议实现
 *
 * PS： 其他还有 IdentityHashMap 等对象，IdentityHashMap 不以 hashCode 为标准
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");

        System.out.println(map.get(1));
        System.out.println(map.get(Long.valueOf(1).intValue()));
        // 不一定是 Integer，就算是个对象，只要 hashCode 和 equal 对的上就 ok
        System.out.println(map.get(1L));
        System.out.println(map.get(1.0));
        System.out.println(map.get(new Key(1)));
        System.out.println(map.containsKey(new Key(1)));

        System.out.println("==========================");
        putTest();
    }

    private static void putTest() {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> identityHashMap = new IdentityHashMap<>();

        map.put("1", 1);
        map.put(new String("1"), 1);

        identityHashMap.put("1", 1);
        identityHashMap.put(new String("1"), 1);

        // 普通 HashMap 的 key 根据的是 hashCode 和 equals 来实现的，所以 size 是 1
        System.out.println(map.size());

        System.out.println("--------------------------");

        // IdentityHashMap 不会以 hashCode 为标准
        System.out.println(identityHashMap.size());
    }

    private static class Key {

        private final int value;

        private Key(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Integer) {
                return this.value == ((Integer) o).intValue();
            }
            Key key = (Key) o;
            return value == key.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }
}
