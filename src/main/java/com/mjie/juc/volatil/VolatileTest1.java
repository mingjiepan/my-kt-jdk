package com.mjie.juc.volatil;

public class VolatileTest1 {
//    volatile  int i;
    static volatile boolean isRunning = true;

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread( () -> {
            while (isRunning) {
                //
            }
        }, "myThread");
        thread.setDaemon(false);
        thread.start();

        Thread.sleep(1000);
        //若isRunning没加volatile，那么main线程对isRunning的修改对myThread是不可见的，因此myThread也就不会退出死循环了
        isRunning = false;
    }

}
