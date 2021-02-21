package com.mjie.synchronize;

import org.openjdk.jol.info.ClassLayout;

public class MarkwordTest2 {


    static class T {
        int m;
        String s;
    }
    public static void main(String[] args) {

        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());

        t.hashCode();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
    }
}
