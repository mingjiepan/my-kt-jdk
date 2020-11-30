package com.mjie.juc.synchronize;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class MarkwordTest {
    public static void main(String[] args)throws Exception {
        //当程序启动先睡眠5秒钟后，jvm就会开启偏向锁
        TimeUnit.SECONDS.sleep(5);

        //普通对象
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        //      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        //      8     4        (object header)                           92 c3 00 f8 (10010010 11000011 00000000 11111000) (-134167662)
        //     12     4    int TT.m                                      0
        //Instance size: 16 bytes
        TT t = new TT();
        String printable = ClassLayout.parseInstance(t).toPrintable();
        System.out.println(printable);

        //自旋锁
        //com.mjie.synchronize.MarkwordTest$TT object internals:
        // OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //      0     4        (object header)                           e8 78 35 07 (11101000 01111000 00110101 00000111) (120944872)
        //      4     4        (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
        //      8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
        //     12     4    int TT.m                                      0
        //Instance size: 16 bytes
        synchronized (t) {
            printable = ClassLayout.parseInstance(t).toPrintable();
            System.out.println(printable);
        }

        //t.hashCode之后，hashCode值也会写在markWord，并且不会再改变了
        t.hashCode();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
    }
    static class TT {
        int m;
    }
}
