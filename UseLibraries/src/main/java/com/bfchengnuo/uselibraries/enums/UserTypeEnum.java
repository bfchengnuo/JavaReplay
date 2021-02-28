package com.bfchengnuo.uselibraries.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 冰封承諾Andy
 * @date 2021/2/28
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
@AllArgsConstructor
@Getter
public enum UserTypeEnum {
    WS_GENERAL_USER("1", "普通用户"),
    WS_OTHER_USER("2", "其他用户");

    private final String code;
    private final String value;
}
