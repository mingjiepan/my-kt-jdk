package com.mjie.rpc.rpc03;

import com.mjie.common.domain.User;
import com.mjie.common.service.UserService;
import com.mjie.common.service.impl.UserServiceImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author panmingjie
 * @date 2020/11/27 09:38
 */
public class Server3 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8899));
        while (!Thread.interrupted()) {
            Socket accept = serverSocket.accept();
            processSocket(accept);
            accept.close();
        }
        serverSocket.close();
    }

    private static void processSocket(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        DataInputStream dis = new DataInputStream(inputStream);
        int userId = dis.readInt();
        System.out.println("读取到客户端发来的数据=" + userId);

        UserService userService = new UserServiceImpl();
        User user = userService.findUserById(userId);

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());

        outputStream.flush();
    }
}
