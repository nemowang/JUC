package com.nemo.juc.c_020;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 10:24
 * @Description Semaphore 信号灯
 * 限流
 * 限制最多有多少个线程同时运行
 */
public class T11_TestSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new Thread(() -> {
            try {
                semaphore.acquire();

                System.out.println("T1 running...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();

                System.out.println("T2 running...");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("T2 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
