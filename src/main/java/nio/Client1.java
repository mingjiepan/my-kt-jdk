package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
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
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for(SelectionKey selectionKey : selectionKeys) {
                SelectableChannel selectableChannel = selectionKey.channel();
                if (selectionKey.isConnectable()) {
                    SocketChannel channel = (SocketChannel) selectableChannel;
                    if (channel.finishConnect()) {
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_WRITE);
                    }
                }
                else if (selectionKey.isReadable()) {
                    ByteBuffer readBuffer = ByteBuffer.allocate(100);
                    SocketChannel sc = (SocketChannel) selectableChannel;
                    sc.read(readBuffer);
                    System.out.println("read from server message : " + new String(readBuffer.array()));
                } else if (selectionKey.isWritable()) {
                    /*SocketChannel sc = (SocketChannel) selectableChannel;
                    sc.write(ByteBuffer.wrap("你好，服务端".getBytes()));*/
                }
            }
        }
    }
}
