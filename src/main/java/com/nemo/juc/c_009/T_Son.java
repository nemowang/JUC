package com.nemo.juc.c_009;

import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 18:02
 * @Description synchronized重入锁机制在继承中的验证
 * 子类同步方法调用父类的同步方法
 * 结果显示是可以进入父类同步方法super.m()中的
 * 证明子类同步方法调用父类方法时获取到的锁是一样的
 * 根据《Java并发编程实战》，这个锁是父类对象的锁
 */
public class T_Son extends T_Father {

    synchronized void m1() {
        System.out.println("T_Son m1 start");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.m();
        System.out.println("T_Son m1 end");
    }

    public static void main(String[] args) {
        T_Son son = new T_Son();
        new Thread(son::m1).start();
    }
}
