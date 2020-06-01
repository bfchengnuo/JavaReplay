package com.bfchengnuo.webflux.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 使用 Netty 实现 Websocket NIO 通信的 Demo
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class Run {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new MyWebSocketChannelHandler());

            System.out.println("服务端开启，等待客户端连接....");

            Channel channel = b.bind(8888).sync().channel();
            channel.closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
