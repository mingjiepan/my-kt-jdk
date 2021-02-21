package com.mjie.reference;

import java.lang.ref.SoftReference;

/**
 * 堆大小，最大20M
 * -Xmx20M
 *
 *
 *
 * //软引用非常适用于缓存
 */
public class T02_SoftReference {
    public static void main(String[] args) throws Exception {
        //m 是一个强引用
        SoftReference<byte[]> m = new SoftReference(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());
        System.gc();

        Thread.sleep(1000);

        System.out.println(m.get());

        //再分配一个数组，heap装不下，这时候系统会垃圾回收，先回收一次，如果不行，会将软引用干掉
        byte[] b = new byte[1024 * 1024 * 12];
        System.out.println(m.get());
    }
}
