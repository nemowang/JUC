package com.nemo.juc.c_011;

import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/13 15:49
 * @Description
 */
public class T {

    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count ++;
            System.out.println(Thread.currentThread().getName() + " count=" + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                // 此处抛异常，锁将被释放。要想不被释放，可以在这里加catch，然后让循环继续
                int i = 1 / 0;
                System.out.println(i);
            }

        }
    }

    public static void main(String[] args) {
        T t = new T();

        Runnable r = () -> t.m();


        new Thread(r, "t1").start();

        new Thread(r, "t2").start();
    }
}
