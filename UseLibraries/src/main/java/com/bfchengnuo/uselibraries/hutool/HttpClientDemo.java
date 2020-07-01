package com.bfchengnuo.uselibraries.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于 JDK 的 {@link java.net.HttpURLConnection} 进行简单封装
 * @author 冰封承諾Andy
 * @date 2020/6/29
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        String url = "http://xxx.com";
        // 简单的 get, 可通过第二个参数 CharsetUtil 指定编码
        System.out.println(HttpUtil.get("https://baidu.com"));

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");
        // 文件上传，使用 file 为名字，file 对象为 val
        paramMap.put("file", FileUtil.file("/face.jpg"));
        // post/get 都可使用 map 参数
        String result1 = HttpUtil.post(url, paramMap);

        // 下载文件，可以使用 StreamProgress 监视进度
        long size = HttpUtil.downloadFile(url, FileUtil.file("/down/"));
    }

    public static void post(String url, Map paramMap) {
        String result1 = HttpRequest.post(url)
                // 头信息，多个头信息多次调用此方法即可
                .header(Header.USER_AGENT, "Hutool http")
                .form(paramMap)
                .timeout(20000)
                .execute().body();

        String result2 = HttpRequest.post(url)
                .body("json")
                .execute().body();
    }
}
