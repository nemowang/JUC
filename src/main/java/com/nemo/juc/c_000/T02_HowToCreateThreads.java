package com.nemo.juc.c_000;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 14:30
 * @Description 创建线程的三种写法
 * 1. 类A继承Thread，并重写run方法，new A().start()
 * 2. 类A实现Runnable接口，并实现run方法，new Thread(new A()).start()
 * 3. 方法2的匿名内部类/lambda表达式写法
 *      new Thread(()-> {
 *          System.out.println("");
 *      }).start();
 */
public class T02_HowToCreateThreads {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("This is MyThread running.");
        }
    }

    static class MyRun implements Runnable {
        public void run() {
            System.out.println("This is MyRun running.");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> {
            System.out.println("This is lambda Runnable.");
        }).start();
    }
}
/*
* 启动线程的三种方式
* 1. 继承Thread
* 2. 实现Runnable
* 3. 线程池 Executors.newCachedThread
*/
