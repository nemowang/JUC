package com.nemo.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Nemo Wong
 * @Date 2021/4/21 14:36
 * @Description lockInterruptibly
 * ReentrantLock可以使用lockInterruptibly方法对interrupt做出响应
 * lock.lockInterruptibly() 如果当前线程获取到锁，继续执行后续操作；如果当前线程被interrupt，则抛出InterruptedException异常
 *
 * lock()优先考虑获取锁，待获取锁成功后，才响应中断
 * lockInterruptibly()优先考虑响应中断，而不是响应锁的获取或重入获取
 */
public class T04_ReentrantLock4 {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 started");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 ended");
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("t2 started");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 ended");
            } catch (InterruptedException e) {
                System.out.println("t2 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

    }
}
