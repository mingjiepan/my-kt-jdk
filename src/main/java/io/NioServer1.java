package io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * 优点：单线程，避免线程切换
 * 缺点：每次轮询都需要调N个（文件描述符的个数）系统调用read方法。即使只有其中一个有数据
 * @author panmingjie
 * @date 2020/11/19 09:31
 */
public class NioServer1 {
    public static void main(String[] args) throws Exception {
        List<SocketChannel> channelList = new LinkedList<>();
        //int fd = socket(NON_BLOCKING)
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //bind() ----> listen()
        serverSocketChannel.bind(new InetSocketAddress(8899));
        while (true) {
            //accept()
            SocketChannel accept = serverSocketChannel.accept();
            if (accept == null) {
                System.out.println("accept is return null...............");
            } else {
                accept.configureBlocking(false);
                channelList.add(accept);
            }
            System.out.println("循环读取每个channel，进行red操作");
            for (SocketChannel socketChannel : channelList) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                //read
                int read = socketChannel.read(byteBuffer);
                if (read > 0) {
                    System.out.println("read new data: " + new String(byteBuffer.array()));
                }
                byteBuffer.clear();
            }
        }
    }
}
