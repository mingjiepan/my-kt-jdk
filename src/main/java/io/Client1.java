package io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author panmingjie
 * @date 2020/11/16 19:05
 */
public class Client1 {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(new InetSocketAddress(8899));
        if (connect) {
            System.out.println("immediately connect");
        }
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        while (true) {
            int select = selector.select();
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    socketChannel = (SocketChannel) selectionKey.channel();
                    if (selectionKey.isConnectable()) {
                        boolean finishConnect = socketChannel.finishConnect();
                        if (finishConnect) {
                            String str = "send from client : hello server, i'm client";
                            ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            socketChannel.write(byteBuffer);
                            byteBuffer.clear();
                            byteBuffer = null;
                        }
                    } else if (selectionKey.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                        socketChannel.read(byteBuffer);
                        System.out.println("receive from server : " + new String(byteBuffer.array()));
                        byteBuffer.clear();
                        byteBuffer = null;
//                        selectionKey.cancel();
                    }
                    iterator.remove();
                }
            }
        }
    }
}
