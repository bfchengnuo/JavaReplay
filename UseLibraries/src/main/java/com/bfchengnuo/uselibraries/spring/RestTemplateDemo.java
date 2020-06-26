package com.bfchengnuo.uselibraries.spring;

import com.bfchengnuo.uselibraries.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * RestTemplate 基础使用
 * 最常用的 getForEntity 方法的返回值是一个 ResponseEntity<T>，
 * ResponseEntity<T> 是 Spring 对 HTTP 请求响应的封装，包括了几个重要的元素，
 * 如响应码、contentType、contentLength、响应消息体等。
 * <p>
 * 而类似的 getForObject 函数实际上是对 getForEntity 函数的进一步封装，
 * 如果你只关注返回的消息体的内容，对其他信息都不关注，此时可以使用 getForObject。
 * <p>
 * 更多配置：https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Spring%E5%AE%B6%E6%97%8F/RestTemplate%E4%BD%BF%E7%94%A8%E7%AC%94%E8%AE%B0.md
 *
 * @author 冰封承諾Andy
 * @date 2020/6/21
 */
public class RestTemplateDemo {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public RestTemplateDemo(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void simple() {
        // 请求地址
        String url = "http://localhost:8080/testPost";
        // 入参 -> json
        User user = new User();
        user.setName("mps");

        Map respMap = restTemplate.postForObject(url, user, Map.class);
    }

    /**
     * 基础的 getForEntity 与 getForObject 参考 {@link #simple()}
     */
    public void get() {
        // 传递参数的两种方式
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://HELLO-SERVICE/sayhello?name={1}", String.class, "张三");

        Map<String, String> map = new HashMap<>();
        map.put("name", "李四");
        responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/sayhello?name={name}", String.class, map);
    }

    public void post() {
        String url = "http://localhost:8080/selectSmallVideo2";
        LinkedMultiValueMap<String, Integer> map = new LinkedMultiValueMap<>();
        // 注意是 add 不是 put
        map.add("sdt", 20180531);
        map.add("edt", 20180531);
        String result = restTemplate.postForObject(url, map, String.class);
        System.err.println(result);
    }

    /**
     * 使用 Exchange
     * 相当于一个公共的请求模板，使用姿势和 get/post 没有什么区别，只是可以由调用发自己来选择具体的请求方法。
     */
    public void exchange() {
        String url = "http://localhost:8080/post";
        String nick = "Mps";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("nick", nick);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println(response.getStatusCode() + " | " + response.getBody());
    }

    /**
     * 自定义 Header 的一个简单示例
     * 使用 Token 认证
     */
    public String customHeader() {
        // 请求地址
        String url = "http://127.0.0.1/oauth/token";
        // 入参
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("username", "mps");
        paramMap.add("password", "pwd");
        paramMap.add("grant_type", "password");
        paramMap.add("scope", "server");

        // 封装头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("TENANT_ID", "1");
        headers.add("Authorization", "Basic YWdya546165=");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);

        RestTemplate restTemplate = new RestTemplate();
        // 可能会抛出异常
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, httpEntity, Map.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Map entityBody = responseEntity.getBody();
            // dosomething
        }

        return "凭证验证失败！";
    }

    /**
     * url 构建工具类
     */
    public void urlParser() {
        UriComponents uriComponents =
                UriComponentsBuilder
                        .fromUriString("http://HELLO-SERVICE/sayhello?name={name}")
                        .build()
                        .expand("mps")
                        .encode();

        String fullUrl = UriComponentsBuilder
                .fromHttpUrl("http://xx.com")
                .queryParams(null)
                .build()
                .toUriString();

        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
    }
}
