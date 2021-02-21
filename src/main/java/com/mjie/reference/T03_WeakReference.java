package com.mjie.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用，遇到GC就会被回收
 */
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());
    }
}
