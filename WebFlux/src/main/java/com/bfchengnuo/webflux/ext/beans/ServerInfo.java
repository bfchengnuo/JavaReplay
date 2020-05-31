package com.bfchengnuo.webflux.ext.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {
    private String url;
}
