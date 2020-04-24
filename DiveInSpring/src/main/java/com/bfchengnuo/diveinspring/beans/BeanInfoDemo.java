package com.bfchengnuo.diveinspring.beans;

import com.bfchengnuo.diveinspring.commons.entry.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * IoC 容器基础，通过内省方式获取 bean 信息
 *
 * @author 冰封承諾Andy
 * @date 2020/3/9
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(System.out::println);
    }
}
