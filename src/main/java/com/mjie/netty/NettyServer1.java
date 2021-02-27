package com.mjie.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer1 {
    public static void main(String[] args) throws Exception {
        /*NioEventLoopGroup thread = new NioEventLoopGroup(1);
        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();
        thread.register(serverSocketChannel);
        ChannelPipeline p = serverSocketChannel.pipeline();
        p.addLast(null);
        ChannelFuture bind = serverSocketChannel.bind(new InetSocketAddress(8899));
        bind.sync().channel().closeFuture().sync();*/
    }
}
