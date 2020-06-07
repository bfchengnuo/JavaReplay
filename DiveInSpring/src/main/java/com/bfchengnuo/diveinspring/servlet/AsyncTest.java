package com.bfchengnuo.diveinspring.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 基于 servlet3.0 的异步示例
 * 前台看到的结果都是阻塞一定时间（等待异步 complete 后返回），后台不会阻塞(容器的 servlet 线程)
 *
 * @author 冰封承諾Andy on 2019/2/17
 */
@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        AsyncContext asyncContext = req.startAsync();

        // 此方法不会阻塞
        asyncContext.start(() -> {
            try {
                resp.getWriter().println("Hello World!");
                // 手动触发完成
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 另一种写法
        AsyncContext asyncContext = req.startAsync();

        // 1.8 新增，对 Future 的增强
        // 此方法不会阻塞
        CompletableFuture.runAsync(() -> doSomeThing(asyncContext));
    }

    private void doSomeThing(AsyncContext asyncContext) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            asyncContext.getResponse().getWriter().append("done.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 通知上下文处理完毕
        asyncContext.complete();
    }
}
