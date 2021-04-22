package com.nemo.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Nemo Wong
 * @Date 2021/4/21 14:24
 * @Description tyrLock()
 * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
 * 也可以根据tryLock的返回值来判定是否锁定
 * 也可以指定tryLock的时间
 */
public class T03_ReentrantLock3 {

    ReentrantLock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 ... " + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 rl = new T03_ReentrantLock3();
        new Thread(rl::m1, "m1").start();

        new Thread(rl::m2, "m2").start();
    }
}
