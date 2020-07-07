package com.bfchengnuo.uselibraries.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.util.Date;

/**
 * fastJson 简单使用
 * 常见注解：
 * - @JSONField （ordinal 属性可以指定顺序，format 可以指定 date 的序列化/反序列化格式）
 *      serialize 是否参与序列化
 *      derialize 是否参与反序列化
 *      serialzeFeatures 序列化规则
 *      parseFeatures 反序列化规则
 *
 * PS：支持 JSONPath
 *
 * @author 冰封承諾Andy
 * @date 2020/7/7
 */
public class FastJsonDemo {
    private static final String JSON_OBJ_STR = "{\"name\":\"lily\",\"age\":12}";

    public static void main(String[] args) {
        // 数组类型 parseArray，也适用于 List
        // 到对象，可使用 TypeReference 的重载，或者直接传递 class 对象的重载
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        // 可使用 getJSONObject 继续获得复杂类型
        System.out.println(jsonObject.getString("name"));

        System.out.println(JSONObject.toJSONString(jsonObject));

        Sg sg = Sg.builder()
                .name("mps")
                .age(12)
                .date(new Date())
                .build();
        String jsonString = JSONObject.toJSONString(sg);
        System.out.println(jsonString);

        Sg sg1 = JSONObject.parseObject(jsonString, Sg.class);
        System.out.println(sg1);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class Sg{
        private String name;
        private Integer age;
        @JSONField(name = "time", format = "yyyy-MM-dd HH:mm:ss")
        private Date date;
    }
}
