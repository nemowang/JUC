package com.nemo.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 16:20
 * @Description t1给list加元素，t2监控list元素个数
 * 思路：t2观察者先启动，list不等于5时等待；t1启动添加元素，list个数等于5时唤醒t2
 * 结果：不成功
 * 原因：list个数等于5时，t1调用lock.notify()唤醒t2，但没有释放锁，因此t2要等到t1运行完成后才能获得锁
 */
public class T03_NotifyHoldingLock {

    volatile List<Object> list = new ArrayList<>();

    private int getSize() {
        return list.size();
    }

    private void add(Object o) {
        list.add(o);
    }

    public static void main(String[] args) {
        T03_NotifyHoldingLock c = new T03_NotifyHoldingLock();

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
            }
        }, "T2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 启动");
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("t1 add " + i);

                    if (c.getSize() == 5) {
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1 结束");
            }
        }, "T1").start();

    }
}
