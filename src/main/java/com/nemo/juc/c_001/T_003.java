package com.nemo.juc.c_001;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 17:04
 * @Description
 */
public class T_003 {

    private int count = 10;

    // 方法声明中用synchronized修饰，等同于 synchronized(this)
    public synchronized void m() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }
}
