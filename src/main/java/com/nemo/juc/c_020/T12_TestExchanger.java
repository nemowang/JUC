package com.nemo.juc.c_020;

import java.util.concurrent.Exchanger;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 11:16
 * @Description Exchanger 交换器
 * 两个线程间通信，交换值
 * 线程t1运行到exchanger.exchange(s)，将s的值放入交换器，阻塞，等待另外一个线程
 * 线程t2运行到exchanger.exchange(s)，将s的值放入交换器，阻塞
 * 交换器将两个值进行交换，阻塞释放，两个线程分别继续运行
 */
public class T12_TestExchanger {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);

                System.out.println(Thread.currentThread().getName() + " " + s);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);

                System.out.println(Thread.currentThread().getName() + " " + s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
