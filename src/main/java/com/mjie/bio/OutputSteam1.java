package com.mjie.bio;

import java.io.*;

/**
 * IO流分类
 * 1 根据是否直接对接源头分为：节点流、处理流
 * 2 根据每次操作的字节数分为：字节流、字符流
 * 3 根据流的输入输出方向分为：输入流、输出流
 *
 * 节点流与处理流的区别：节点流对接数据源头，如FileInputStream(文件)、ByteArrayInputSteam（内存数组）、ObjectInputSteam（java对象序列化后的，可以）
 * 处理流有：BufferedInputStream、DataInputSteam
 *
 * 字节的节点流直接操作数据源，因此没close或者flush时，写出操作都会生效
 * 而字符流是带缓冲区的，需要close或者flush，写出操作才会生效
 *
 *
 *
 * @author panmingjie
 * @date 2020/11/27 10:21
 */
public class OutputSteam1 {
    public static void main(String[] args) throws Exception {
        /*OutputStream outputStream = new FileOutputStream("F:\\tmp\\test.txt");
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        bos.write("hello world".getBytes());
        bos.flush();*/
//        outputStream.write("hello".getBytes());

        OutputStream outputStream = new FileOutputStream("F:\\tmp\\test.txt");
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
       /* BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(12);
        bos.flush();*/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream bos = new DataOutputStream(baos);
        bos.writeInt(100);
        outputStream.write(baos.toByteArray());
    }
}
