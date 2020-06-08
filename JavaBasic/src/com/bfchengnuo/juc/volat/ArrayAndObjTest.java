package com.bfchengnuo.juc.volat;

import com.bfchengnuo.pojo.User;
import com.bfchengnuo.pojo.UserPublic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Atomic 的数组引用、对象引用测试
 * 并不会修改之前的数组
 *
 * @author 冰封承諾Andy
 * @date 2020/6/7
 */
public class ArrayAndObjTest {
    public static void main(String[] args) {
        fieldTest();
        // refTest();
        // arrayTest();
    }

    private static void arrayTest() {
        int[] vals = new int[]{1, 2};
        AtomicIntegerArray ai = new AtomicIntegerArray(vals);

        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(vals[0]);
    }

    /**
     * 引用的方式修改对象
     */
    private static void refTest() {
        User user = new User("mps");
        User newUser = new User("skye");

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user);
        atomicReference.compareAndSet(user, newUser);
        System.out.println(atomicReference.get());
    }

    /**
     * 原子更新对象的某个字段(必须 public volatile，int 原生类型)
     * @see java.util.concurrent.atomic.AtomicReferenceFieldUpdater
     */
    private static void fieldTest() {
        AtomicIntegerFieldUpdater<UserPublic> atomicIntegerFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(UserPublic.class, "age");
        UserPublic user = new UserPublic("mps", 12);
        System.out.println(atomicIntegerFieldUpdater.getAndIncrement(user));
        System.out.println(atomicIntegerFieldUpdater.get(user));
        System.out.println(user);
    }
}
