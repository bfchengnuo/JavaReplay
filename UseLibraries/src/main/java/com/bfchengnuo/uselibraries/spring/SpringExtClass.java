package com.bfchengnuo.uselibraries.spring;

import com.bfchengnuo.uselibraries.guava.CollectionsTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

/**
 * Spring 中的扩展对象
 * @author 冰封承諾Andy
 * @date 2021/2/27
 */
public class SpringExtClass {
    public static void main(String[] args) {
        multiValueMapTest();
    }

    /**
     * MultiValueMap
     * 即一对多的 Map，允许一个 key 存储多个值，也就是 Map<K, List<V>>，严格来说内部是一个 LinkedList
     *
     * 在 ApacheCommon 或者 Guava 中也有类似的扩展类
     * Spring 中最典型的应用就是 RestTemplate 中参数使用
     * @see CollectionsTest#multimapTest()
     * @see RestTemplateDemo
     */
    private static void multiValueMapTest() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("key", "val1");
        multiValueMap.add("key", "val2");
        multiValueMap.add("key", "val3");

        multiValueMap.put("list", Arrays.asList("1", "2", "3"));

        System.out.println(multiValueMap);

        multiValueMap.set("key", "val4");
        multiValueMap.set("key", "val5");

        System.out.println(multiValueMap);
    }
}
