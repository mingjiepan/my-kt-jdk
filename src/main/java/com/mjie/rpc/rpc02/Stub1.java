package com.mjie.rpc.rpc02;

import com.mjie.common.domain.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author panmingjie
 * @date 2020/11/27 09:45
 */
public class Stub1 {
    public User findUserById(Integer id) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost",8899));
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.writeInt(20);

            outputStream.flush();

            InputStream inputStream = socket.getInputStream();

            DataInputStream dis = new DataInputStream(inputStream);
            int readInt = dis.readInt();
            String readUTF = dis.readUTF();
            System.out.println("返回的用户ID="+ readInt + ", 名称="+ readUTF);
            socket.close();
            return new User(readInt, readUTF);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
