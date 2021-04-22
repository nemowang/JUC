package com.nemo.juc.c_000;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 15:18
 * @Description
 */
public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println(i);
                System.out.println("MyThread:" + this.getState());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        System.out.println("main:-----" + t.getState());

        t.start();
        System.out.println("main:-----" + t.getState());

        try {
            t.join();
            System.out.println("main:-----" + t.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main:-----" + t.getState());
    }
}
