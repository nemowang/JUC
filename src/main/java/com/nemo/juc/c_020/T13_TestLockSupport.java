package com.nemo.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 15:08
 * @Description
 */
public class T13_TestLockSupport {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 5) {
                    LockSupport.park();
                }
            }
        });

        t.start();

        try {
            TimeUnit.SECONDS.sleep(8);
            System.out.println("after 8 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(t);
    }
}
