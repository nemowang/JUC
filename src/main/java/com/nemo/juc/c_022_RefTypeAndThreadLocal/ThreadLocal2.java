package com.nemo.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/25 9:31
 * @Description ThreadLocal
 * 每个线程内部存放线程的本地变量，这个变量不与其他线程共享
 * 保证资源在线程间的独立性
 */
public class ThreadLocal2 {
    private volatile static Person p = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person {
        String name = "张三";
    }
}
