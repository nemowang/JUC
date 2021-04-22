package com.nemo.juc.c_012;

import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/20 14:45
 * @Description
 * volatile adj. 可变的，易变的
 * 保证线程可见性
 * 禁止指令重排序
 */
public class T {

    // 对比有无volatile的区别
//    boolean running = true;
    volatile boolean running = true;

    void m() {
        System.out.println("m start");

        while (running) {

        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }
}
