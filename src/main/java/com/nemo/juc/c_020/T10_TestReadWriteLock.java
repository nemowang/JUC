package com.nemo.juc.c_020;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 9:39
 * @Description 读写锁
 * 读锁 共享锁 所有拥有读锁的读操作可以同时进行
 * 写锁 互斥锁 必须等上一个操作执行完才能获得锁
 */
public class T10_TestReadWriteLock {
    // 普通重入锁
    static Lock lock = new ReentrantLock();
    private static int value;

    // 读写锁s
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        // 普通锁
        /*Runnable readR = () -> read(lock);
        Runnable writeR = () -> write(lock, new Random().nextInt());*/

        Runnable readR = () -> read(readLock);
        Runnable writeR = () -> write(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            // 18个读线程
            new Thread(readR).start();
        }

        for (int i = 0; i < 2; i++) {
            // 2个写线程
            new Thread(writeR).start();
        }
    }

    static void read(Lock lock) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read over!");
            // 模拟读操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static void write(Lock lock, int updateValue) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value = updateValue;
            System.out.println("write over!");
            // 模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
