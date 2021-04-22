package com.nemo.juc.heima.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author Nemo Wong
 * @Date 2021/4/20 9:46
 * @Description
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> submit1 = executorService.submit(new RunnableImpl());
        Future<?> submit2 = executorService.submit(new RunnableImpl());
        Future<?> submit3 = executorService.submit(new RunnableImpl());
    }
}
