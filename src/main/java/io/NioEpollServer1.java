package io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * nio多路复用器
 * @author panmingjie
 * @date 2020/11/16 10:24
 */
public class NioEpollServer1 {
    public static void main(String[] args) throws Exception {
        //epoll_create
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8899));
        //epoll_ctl
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            //epoll_wait
            selector.select();
            //key可以看做是文件描述符
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeySet) {
                SelectableChannel selectableChannel = selectionKey.channel();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectableChannel;
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    ByteBuffer readBuffer = ByteBuffer.allocate(100);
                    SocketChannel socketChannel = (SocketChannel) selectableChannel;
                    socketChannel.read(readBuffer);
                    System.out.println("read message : " + new String(readBuffer.array()));
                } else if (selectionKey.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) selectableChannel;
                    socketChannel.write(ByteBuffer.wrap("hello".getBytes()));
                }
            }
            selectionKeySet.clear();
        }
    }
}
