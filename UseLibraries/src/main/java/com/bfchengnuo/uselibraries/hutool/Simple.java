package com.bfchengnuo.uselibraries.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.CharsetUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * hutool 工具箱基础使用
 * 对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，可独立引用模块
 * 文档：https://www.hutool.cn/docs/#/
 *
 * 忽略的工具：克隆相关（ObjectUtil）
 * 简单工具：{@link cn.hutool.core.date.DateUtil}、{@link cn.hutool.core.date.DateTime}、
 *      {@link cn.hutool.core.io.FileUtil}、{@link cn.hutool.core.io.FileTypeUtil}、
 *      {@link cn.hutool.core.io.resource.ResourceUtil}、{@link cn.hutool.core.io.IoUtil}、
 *      {@link cn.hutool.core.util.StrUtil}、{@link cn.hutool.core.util.RandomUtil}、{@link cn.hutool.core.lang.Assert}、
 *      {@link cn.hutool.core.collection.CollUtil}、{@link cn.hutool.core.collection.ListUtil}、
 *      {@link cn.hutool.json.JSONUtil}、{@link cn.hutool.crypto.SecureUtil}、{@link cn.hutool.extra.mail.MailUtil}
 *
 * @author 冰封承諾Andy
 * @date 2020/6/29
 */
public class Simple {
    public static void main(String[] args) {
        // 类型转换
        int x = 123;
        String str = Convert.toStr(x);
        str = "1,2,3,4";
        String result1 = StrFormatter.format("this is {} for {}", "a", "b");
        // toList
        System.out.println(Arrays.toString(Convert.toIntArray(str.split(","))));
        // 通用实现
        Object[] a = { "a", "你", "好", "", 1 };
        List<String> list = Convert.convert(new TypeReference<List<String>>() {}, a);

        // 编码转换
        String result = Convert.convertCharset("你好", CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        // 时间相关
        System.out.println(Convert.toDate("2020-06-29"));
        long minutes = Convert.convertTime(2_000, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        // 金额大小写
        System.out.println(Convert.digitToChinese(123.23));
        // 自定义转换参考：ConverterRegistry
    }


}
