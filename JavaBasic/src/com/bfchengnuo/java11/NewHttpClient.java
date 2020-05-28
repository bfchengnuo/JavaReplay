package com.bfchengnuo.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 新的 http client，9 开始出现，11 已经在标准库 java.net 中
 * 支持 WebSocket 和 HTTP2 流以及服务器推送特性。
 *
 * @author 冰封承諾Andy
 * @date 2020/5/15
 */
public class NewHttpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        syncCall();
        asyncCall();
    }

    /**
     * 同步调用
     */
    public static void syncCall() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://baidu.com"))
                .GET()
                .build();

        var client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    /**
     * 同步调用
     */
    public static void asyncCall() {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://baidu.com"))
                .header("Content-Type", "text/plain")
                // 如果是 post 调用
                // .POST(HttpRequest.BodyPublishers.ofString("Hi there!"))
                .build();

        var client = HttpClient.newHttpClient();
        // sendAsync 不会阻塞当前线程，返回一个 Future 对象
        // 可省略最后的 get 调用，默认 get
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }
}
