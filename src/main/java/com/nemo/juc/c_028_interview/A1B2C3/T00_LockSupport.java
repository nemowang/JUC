package com.nemo.juc.c_028_interview.A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author Nemo Wong
 * @Date 2021/4/25 11:21
 * @Description
 */
public class T00_LockSupport {
    private static Thread t1 = null;
    private static Thread t2 = null;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(i + 1);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "numberThread");

        t2 = new Thread(() -> {
            LockSupport.park();
            for (int i = 0; i < 26; i++) {
                System.out.print((char) (65 + i));
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        }, "letterThread");

        t1.start();
        t2.start();
    }
}
