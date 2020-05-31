package com.bfchengnuo.webflux.ext.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {
    private String url;
    private HttpMethod method;
    private Map<String, Object> params;
    private Mono<?> body;
    private Class<?> bodyType;

    private boolean returnMono;
    private Class<?> returnType;
}
