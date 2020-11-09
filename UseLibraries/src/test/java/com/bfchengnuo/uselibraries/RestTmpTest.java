package com.bfchengnuo.uselibraries;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author 冰封承諾Andy
 * @date 2020/7/8
 */
public class RestTmpTest {
    public static void main(String[] args) {
        String url = "http://localhost:2333";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("nick", "mps");

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("head", "xxxx");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        System.out.println(response.getStatusCode() + " | " + response.getBody());
    }
}
