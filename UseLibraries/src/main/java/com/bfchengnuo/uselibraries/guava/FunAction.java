package com.bfchengnuo.uselibraries.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * Google 通用库 Guava 的基本使用
 *
 * @author Created by 冰封承諾Andy on 2019/6/9.
 * <p>
 * see： https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/%E7%B1%BB%E5%BA%93%E7%9B%B8%E5%85%B3/Guava%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.md
 */
public class FunAction {
    public static void main(String[] args) {
        // CollectionsTest.immutableCollectionTest();
        // CollectionsTest.multisetTest();
        // CollectionsTest.biMapTest();


        StringRelated.charMatcherTest("12kjkj3kj212jk3kj2j3kj");
    }


    public static void oauth() {
        String input = "hello, world";

        // 计算 MD5
        // Google 建议使用 Hashing.goodFastHash() 而不再用 MD5，称既不快也不安全
        String md = Hashing.md5().newHasher().putString(input, Charsets.UTF_8).hash().toString();
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        // 计算sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        // 计算sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        // 计算crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());

        System.out.println(Hashing.md5().hashUnencodedChars(input).toString());
    }
}
