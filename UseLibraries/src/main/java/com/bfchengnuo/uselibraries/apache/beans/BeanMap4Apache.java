package com.bfchengnuo.uselibraries.apache.beans;

import com.bfchengnuo.uselibraries.common.User;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Apache 推出的 bean 工具类中 Bean 与 Map 的互换
 *
 * 类似的功能 Spring 中也有对应的工具类，详情见 {@link com.bfchengnuo.uselibraries.spring.BeanMapDemo}
 *
 * @author Created by 冰封承諾Andy on 2019/7/16.
 */
public class BeanMap4Apache {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User user = new User("skye", 15, "desc");
        // 只能是 Map<String, String>
        Map<String, String> map = BeanUtils.describe(user);
        User user1 = new User();
        BeanUtils.populate(user1, map);

        // 等价于
        BeanUtilsBean beanUtilsBean = BeanUtilsBean2.getInstance();
        Map<String, String> describe = beanUtilsBean.describe(user);

        System.out.println(map);
        System.out.println(describe);
        System.out.println(user1);


        beanMap();
    }

    /**
     * Apache 的 BeanMap 示例
     *
     * BeanMap 的使用与 {@link com.bfchengnuo.uselibraries.spring.BeanMapDemo} 基本一致
     */
    private static void beanMap() {
        User user = new User("skye", 15, "desc");

        new BeanMap(user).forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        User user1 = new User();
        BeanMap beanMap = new BeanMap(user1);
        beanMap.put("name", "loli");
        beanMap.put("age", 12);
        beanMap.put("desc", "dd");

        // put 之后是实时更新的
        System.out.println(user1);
        System.out.println(beanMap.getBean());
    }
}
