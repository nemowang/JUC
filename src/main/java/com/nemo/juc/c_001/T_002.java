package com.nemo.juc.c_001;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 17:03
 * @Description
 */
public class T_002 {

    private int count = 10;

    public void m() {
        // 不必单独创建一个对象进行加锁，可以直接对本类this加锁
        synchronized (this) {
            count --;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}
