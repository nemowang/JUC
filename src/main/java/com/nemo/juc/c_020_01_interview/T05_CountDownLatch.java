package com.nemo.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 17:11
 * @Description
 * 使用两个CountDownLatch，给两个线程分别设置门闩
 */
public class T05_CountDownLatch {
    /*volatile*/ List<Object> list = new ArrayList<>();

    private int getSize() {
        return list.size();
    }

    private void add(Object o) {
        list.add(o);
    }

    public static void main(String[] args) {
        T05_CountDownLatch c = new T05_CountDownLatch();
        CountDownLatch observerLatch = new CountDownLatch(1);
        CountDownLatch producerLatch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 开始");
            if (c.getSize() != 5) {
                try {
                    observerLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
            producerLatch.countDown();
        }, "T2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 开始");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                if (i == 5) {
                    observerLatch.countDown();
                    try {
                        producerLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "T1").start();
    }
}
