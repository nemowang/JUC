package com.nemo.juc.heima.threadpool;

/**
 * @Author Nemo Wong
 * @Date 2021/4/20 9:49
 * @Description
 */
public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "创建了一个新的线程");
    }
}
