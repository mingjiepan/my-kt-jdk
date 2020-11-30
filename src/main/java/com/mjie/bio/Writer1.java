package com.mjie.bio;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author panmingjie
 * @date 2020/11/27 10:48
 */
public class Writer1 {

    public static void main(String[] args) throws Exception{
        /*Writer writer = new FileWriter("F:\\tmp\\test.txt");
        writer.write("你好么");
        writer.flush();*/

        Writer writer = new OutputStreamWriter(new FileOutputStream("F:\\tmp\\test.txt"));
        writer.write("真的啊");
    }
}
