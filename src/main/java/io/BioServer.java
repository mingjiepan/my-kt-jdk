package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * int fd = socket()
 * bind(fd, 8899)
 * listen(fd)
 *
 * while(true) {
 *     int fd2 = accept(fd)
 *     process = clone()
 *     process {
 *         read(fd2)
 *     }
 * }
 * 优点：可以处理很多线程，模式简单
 * 缺点：过多的线程上下文切换，带来较大的系统开销
 * @author panmingjie
 * @date 2020/11/19 10:33
 */
public class BioServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        int read = bufferedReader.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
