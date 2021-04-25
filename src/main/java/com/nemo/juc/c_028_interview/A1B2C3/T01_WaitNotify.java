package com.nemo.juc.c_028_interview.A1B2C3;

/**
 * @Author Nemo Wong
 * @Date 2021/4/25 11:43
 * @Description
 */
public class T01_WaitNotify {

    private static Thread t1 = null;
    private static Thread t2 = null;

    public static void main(String[] args) {
        Object lock = new Object();
        t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    System.out.print(i + 1);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                // 循环结束后要调用notify，防止最后一个线程wait后无法被叫醒
                lock.notify();
            }
        });

        t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) (65 + i));
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 循环结束后要调用notify，防止最后一个线程wait后无法被叫醒
                lock.notify();
            }
        });

        t1.start();
        t2.start();
    }
}
