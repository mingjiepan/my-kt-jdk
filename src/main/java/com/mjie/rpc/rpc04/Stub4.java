package com.mjie.rpc.rpc04;

import com.mjie.common.domain.User;
import com.mjie.common.service.UserService;

import java.io.*;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author panmingjie
 * @date 2020/11/27 09:45
 */
public class Stub4 {
    public User findUserById(Integer id) {
        UserService userService = (UserService)Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, (proxy, method, args) -> {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 8899));
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream dos = new ObjectOutputStream(outputStream);

            dos.writeUTF(UserService.class.getName());
            dos.writeUTF(method.getName());
            dos.writeObject(method.getParameterTypes());
            dos.writeInt(23);

            outputStream.flush();

            InputStream inputStream = socket.getInputStream();

            DataInputStream dis = new DataInputStream(inputStream);
            int readInt = dis.readInt();
            String readUTF = dis.readUTF();
            System.out.println("返回的用户ID=" + readInt + ", 名称=" + readUTF);
            socket.close();
            return new User(readInt, readUTF);
        });
        return userService.findUserById(id);
    }
}
