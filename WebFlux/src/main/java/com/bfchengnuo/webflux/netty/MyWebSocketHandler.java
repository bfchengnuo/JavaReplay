package com.bfchengnuo.webflux.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * 接收、处理、响应客户端 websocket 请求的核心业务处理类
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;
    public static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";


    /**
     * Web 端处理 websocket 请求的核心方法
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        // 处理客户端发起的 http 握手请求业务
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(channelHandlerContext, (FullHttpRequest) msg);
        }
        // 处理 websocket 连接业务
        if (msg instanceof WebSocketFrame) {
            handWebSocketFrame(channelHandlerContext, (WebSocketFrame) msg);
        }
    }

    /**
     * 处理 http 握手请求的业务
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // req.getDecoderResult() 已过时
        if (!req.decoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            // sendUnsupportedWebSocketVersionResponse 已经过时
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 服务端向客户端响应消息
     * @param ctx
     * @param req
     * @param res
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 如果请求失败
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 服务端向客户端发送数据
        ChannelFuture f = ctx.channel().writeAndFlush(res);

        if (res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 处理客户端与服务端之间的 websocket 业务
     * @param ctx
     * @param frame
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 如果是关闭指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
        }
        // 判断是否是 ping 消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 判断是否是二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("暂时不支持二进制消息");
            throw new RuntimeException(this.getClass().getName() + "|| 不支持消息");
        }

        // 返回应答消息
        // 获取客户端向服务器发送的消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到客户端的消息=========>" + request);

        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "====>" + request);
        // 群发，服务端向每个连接上的客户端群发消息
        NettyConfig.group.writeAndFlush(tws);
    }

    /**
     * 客户端与服务器建立连接的时候调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端连接开启...");
    }

    /**
     * 客户端与服务端断开连接时调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端连接关闭...");
    }

    /**
     * 服务端接收客户端发送过来的数据结束后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 工程出现异常时调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 关闭连接
        ctx.close();
    }

}
