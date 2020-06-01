package com.bfchengnuo.webflux.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class NettyConfig {
    /**
     * 存储每一个客户端连接进来时的 channel 对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
