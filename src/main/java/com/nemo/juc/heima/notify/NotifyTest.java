package com.nemo.juc.heima.notify;

/**
 * @Author Nemo Wong
 * @Date 2021/4/19 14:54
 * @Description
 * 线程A的锁对象调用wait()方法，使线程A无限等待
 * 线程B的锁对象调用notify()方法唤醒正在等待的线程A，继续执行wait()之后的代码
 */
public class NotifyTest {

    /**
     * 顾客告诉老板需要的包子的数量和种类，等待老板去做wait()
     * 老板5秒后做好包子后告诉顾客notify()
     * @param args
     */
    public static void main(String[] args) {
        Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println("顾客1告诉老板需要的包子的数量和种类");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("顾客1拿到老板做好的包子，开吃");
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println("顾客2告诉老板需要的包子的数量和种类");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("顾客2拿到老板做好的包子，开吃");
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                try {
                    System.out.println("老板开始做包子");
                    // 老板花费5秒钟做包子
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                obj.notifyAll();
            }
        }).start();
    }
}
