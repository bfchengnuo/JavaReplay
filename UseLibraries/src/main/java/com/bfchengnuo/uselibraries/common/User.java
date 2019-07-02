package com.bfchengnuo.uselibraries.common;

import lombok.*;

/**
 * 供测试的一个简单的实体对象
 *
 * @author Created by 冰封承諾Andy on 2019/6/30.
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class User {
    private String name;
    private Integer age;
    private String desc;
}
