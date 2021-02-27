package com.mjie.rpc.rpc01;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author panmingjie
 * @date 2020/11/27 09:45
 */
public class Client1 {
    public static void main(String[] args) throws Exception {
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
    }
}
