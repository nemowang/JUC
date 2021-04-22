package com.nemo.juc.c_001;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 17:09
 * @Description
 */
public class T_004 {

    private static int count = 10;

    // 静态方法声明中用synchronized修饰，等同于 synchronized(T_004.class)
    public static synchronized void m() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

    // 静态方法中没有this，因此不能使用synchronized(this)
    public static void mm() {
        synchronized (T_004.class) {
            count --;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}
