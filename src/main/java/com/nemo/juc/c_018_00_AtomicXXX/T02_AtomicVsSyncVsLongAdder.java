package com.nemo.juc.c_018_00_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author Nemo Wong
 * @Date 2021/4/21 10:05
 * @Description
 * 递增性能比较
 * 原子类 vs Synchronized锁 vs LongAdder
 */
public class T02_AtomicVsSyncVsLongAdder {
    
    static AtomicLong atomicLong = new AtomicLong(0L);
    static long l = 0L;
    static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    atomicLong.incrementAndGet();
                }
            });
        }

        long atomicStart = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long atomicEnd = System.currentTimeMillis();
        System.out.println("Atomic:" + atomicLong + " time:" + (atomicEnd - atomicStart));
        //-----------------------------------------------------------------------------------------------------------

        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    synchronized (lock) {
                        l++;
                    }
                }
            });
        }

        long longStart = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long longEnd = System.currentTimeMillis();
        System.out.println("long:" + l + " time:" + (longEnd - longStart));
        //-----------------------------------------------------------------------------------------------------------

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    longAdder.increment();
                }
            });
        }
        long longAdderStart = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long longAdderEnd = System.currentTimeMillis();
        System.out.println("longAdder:" + longAdder + " time:" + (longAdderEnd - longAdderStart));
    }
}
