package io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio多路复用器
 * @author panmingjie
 * @date 2020/11/16 10:24
 */
public class NioEpollServer1 {
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private int port = 8899;

    private void initServer() {
        try {
            //若是底层支持epoll，那么等于系统调用epoll_create() = 1
            selector = Selector.open();
            // socket() = 2
            serverSocketChannel = ServerSocketChannel.open();
            // bind(2, 8899), listen()
            serverSocketChannel.bind(new InetSocketAddress(port));
            //设置socket2 为非阻塞，fcntl(2. non_blocking)
            serverSocketChannel.configureBlocking(false);
            //epoll_ctl(1, 2, accept)
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void start() {
        while (true) {
            try {
                //epoll_wait(1)
                int select = selector.select();
                if (select > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            handleAccept(selectionKey);
                        } else if (selectionKey.isReadable()) {
                            handleReadable(selectionKey);
                        } else if (selectionKey.isWritable()) {
                            handleWritable(selectionKey);
                        }
                        iterator.remove();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private void handleAccept(SelectionKey selectionKey) {
        try {
            serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void handleReadable(SelectionKey selectionKey) {
        try {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
            socketChannel.read(byteBuffer);
            System.out.println("receive from client : " + new String(byteBuffer.array()));
            byteBuffer.clear();
            byteBuffer = null;
            //读取数据，取消关注
//            selectionKey.cancel();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void handleWritable(SelectionKey selectionKey) {
        try {
            String content = "i'm server ,hello client";
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            System.out.println("send from server " + socketChannel.socket().getInetAddress() + ":" + socketChannel.socket().getPort() + ", 消息体 = " + content);
            ByteBuffer wrap = ByteBuffer.wrap(content.getBytes());
            socketChannel.write(wrap);
            wrap.clear();
            wrap = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        NioEpollServer1 nioEpollServer1 = new NioEpollServer1();
        nioEpollServer1.initServer();
        nioEpollServer1.start();
    }
}
