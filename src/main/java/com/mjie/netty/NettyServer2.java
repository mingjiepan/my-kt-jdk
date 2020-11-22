package com.mjie.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer2 {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture future = serverBootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(null).bind(8899);
        future.channel().closeFuture().sync();
    }
}
