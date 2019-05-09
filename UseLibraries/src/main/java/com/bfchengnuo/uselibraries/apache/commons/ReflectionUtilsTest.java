package com.bfchengnuo.uselibraries.apache.commons;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Created by 冰封承諾Andy on 2019/5/8.
 *
 * 反射相关的工具类，例如 ClassUtilsTest、FieldUtils、MethodUtils、ConstructorUtils
 */
public class ReflectionUtilsTest implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReflectionUtilsTest(String name) {
        this.name = name;
    }

    public ReflectionUtilsTest() {
    }

    public static void main(String[] args) throws Exception {

        // null 可以强转为任意对象
        System.out.println("(object)null is " + (Object)null);

    }

    private static void fieldUtilsTest() throws IllegalAccessException {
        ReflectionUtilsTest test = new ReflectionUtilsTest("Ray");

        // 以下两个方法完全一样,都能获取共有或私有变量,因为第三个参数都设置了不检查
        FieldUtils.getDeclaredField(ReflectionUtilsTest.class, "name", true);
        FieldUtils.getField(ReflectionUtilsTest.class, "name", true);

        // 读取私有或公共变量的值
        FieldUtils.readField(test, "name", true);

        // 读取静态变量
        FieldUtils.readStaticField(ReflectionUtilsTest.class, "STATIC_FIELD");

        // 写入私有或共有变量
        FieldUtils.writeField(test, "name", "RayRay", true);

        // 写入静态变量
        FieldUtils.writeStaticField(ReflectionUtilsTest.class, "STATIC_FIELD", "static_value");
    }

    private static void methodUtilsTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 调用无参方法
        ReflectionUtilsTest test = new ReflectionUtilsTest();
        MethodUtils.invokeMethod(test, "getName", null);

        // 调用一参方法
        MethodUtils.invokeMethod(test, "publicHello", "Hello");

        // 调用多参方法
        MethodUtils.invokeMethod(test, "publicHello", "100", 10);

        // 调用静态方法
        MethodUtils.invokeStaticMethod(ReflectionUtilsTest.class, "staticHello", null);
    }

    private static void constructorUtilsTest() throws NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException, InstantiationException {
        // 获取参数为String的构造函数
        ConstructorUtils.getAccessibleConstructor(ReflectionUtilsTest.class, String.class);

        // 执行参数为String的构造函数
        ReflectionUtilsTest test = ConstructorUtils.invokeConstructor(ReflectionUtilsTest.class,
                "Hello");
    }

    private static void classUtilsTest() {
        // 获取 ClassUtilsTest 类所有实现的接口
        ClassUtils.getAllInterfaces(ReflectionUtilsTest.class).forEach(System.out::println);

        // 获取 ClassUtilsTest 类所有父类
        ClassUtils.getAllSuperclasses(ReflectionUtilsTest.class).forEach(System.out::println);

        // 获取 ClassUtilsTest 类所在的包名
        System.out.println("getPackageName = " + ClassUtils.getPackageName(ReflectionUtilsTest.class));

        // 获取 ClassUtilsTest 类简单类名
        System.out.println("getShortClassName = " + ClassUtils.getShortClassName(ReflectionUtilsTest.class));

        // 判断是否可以转型
        System.out.println("是否可以转型：" + ClassUtils.isAssignable(ReflectionUtilsTest.class, Object.class));

        // 判断是否有内部类
        ClassUtils.isInnerClass(ReflectionUtilsTest.class);
    }
}
