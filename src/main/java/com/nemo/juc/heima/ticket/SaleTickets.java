package com.nemo.juc.heima.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Nemo Wong
 * @Date 2021/4/19 11:36
 * @Description 多线程卖票
 * 多线程访问共享数据会产生线程安全问题
 * 解决方案：
 * 1.同步代码块
 * 2.同步方法
 * 3.锁机制
 */
public class SaleTickets implements Runnable {

    //初始票数100张
    private int amount = 100;

    Lock lock = new ReentrantLock();

    // synchronized同步代码块、同步方法
    /*@Override
    public synchronized void run() {
        while (amount > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖出第" + amount + "张票");
            amount--;
        }
    }*/

    /**
     * Lock锁
     * 在需要保证线程安全的代码块前加锁，代码块后释放锁
     */
    @Override
    public void run() {
        lock.lock();
        try {
            while (amount > 0) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "卖出第" + amount + "张票");
                amount--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 在finally中释放锁，保证不管是否有异常，锁都可以正常释放s
            lock.unlock();
        }
    }
}
