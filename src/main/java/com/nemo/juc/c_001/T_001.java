package com.nemo.juc.c_001;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 16:58
 * @Description synchronized 锁
 */
public class T_001 {

    private int count = 10;
    private Object o = new Object();

    public void m() {
        // 锁定任意一个对象 被调用时看是否拿到这个对象的锁，拿到锁才可以运行
        synchronized (o) {
            count --;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}
