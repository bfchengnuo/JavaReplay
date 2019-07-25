package com.bfchengnuo.diveinspring.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于 servlet3.0 的异步示例
 *
 * @author 冰封承諾Andy on 2019/2/17
 */
@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        AsyncContext asyncContext = req.startAsync();

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
}
