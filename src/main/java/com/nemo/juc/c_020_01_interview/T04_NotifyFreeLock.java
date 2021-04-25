package com.nemo.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 16:52
 * @Description 与T03_NotifyHoldingLock的区别就是 notify()后唤醒线程，让另一个线程继续执行
 */
public class T04_NotifyFreeLock {

    /*volatile*/ List<Object> list = new ArrayList<>();

    private int getSize() {
        return list.size();
    }

    private void add(Object o) {
        list.add(o);
    }

    public static void main(String[] args) {
        T04_NotifyFreeLock c = new T04_NotifyFreeLock();

        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 启动");
                try {
                    if (c.getSize() != 5) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 结束");
                // 通知t1继续执行
                lock.notify();
            }
        }, "T2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("t1 启动");
                    for (int i = 0; i < 10; i++) {
                        c.add(new Object());
                        System.out.println("t1 add " + i);

                        if (c.getSize() == 5) {
                            lock.notify();
                            // 释放锁
                            lock.wait();
                        }

                        TimeUnit.SECONDS.sleep(1);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 结束");
            }
        }, "T1").start();

    }
}
