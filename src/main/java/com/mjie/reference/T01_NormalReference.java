package com.mjie.reference;

import java.io.IOException;

/**
 * GC 是单独的线程
 */
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();

        m = null;

        System.gc();

        System.out.println(m);

        System.in.read();
    }
}
