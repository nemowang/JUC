package com.nemo.juc.c_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Nemo Wong
 * @Date 2021/4/21 15:19
 * @Description 公平锁
 * 按等待队列的顺序执行
 */
public class T05_ReentrantLock5 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 tl = new T05_ReentrantLock5();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
    }
}
