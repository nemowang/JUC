package com.nemo.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 15:15
 * @Description t1给list加元素，t2监控list元素个数
 * 不成功 无论是否在list前加volatile修饰
 * 原因：
 *  1. 不加volatile。线程不可见性
 *  2. 加volatile。List是引用类型，volatile保证的是list引用的可见，而不是list指向值的可见。
 */
public class T01_WithoutVolatile {

    /*volatile*/ List<Object> list = new ArrayList<>();

    private int getSize() {
        return list.size();
    }

    private void add(Object o) {
        list.add(o);
    }

    public static void main(String[] args) {
        T01_WithoutVolatile c = new T01_WithoutVolatile();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("t1 add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (c.getSize() == 5) break;
            }
            System.out.println("t2 ended.");
        }, "t2").start();
    }
}
