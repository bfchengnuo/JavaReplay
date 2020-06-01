package com.bfchengnuo.webflux.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class MyWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
    /**
     * 初始化连接时的各个组件
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
        socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        socketChannel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        socketChannel.pipeline().addLast("handler", new MyWebSocketHandler());
    }
}
