package com.nemo.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/25 9:31
 * @Description ThreadLocal
 * 不用ThreadLocal
 * 不同线程操作的是同一个对象
 */
public class ThreadLocal1 {
    private volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "李四";
        }).start();
    }

    static class Person {
        String name = "张三";
    }
}
