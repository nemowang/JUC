package com.nemo.juc.c_000;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 14:52
 * @Description
 */
public class T03_Sleep_Yield_Join {

    public static void main(String[] args) {
        // sleepTest();
        // yieldTest();
        joinTest();
    }

    public static void sleepTest() {
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * yield命令使线程回到就绪状态 重新回到线程等待队列
     */
    public static void yieldTest() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("------------B" + i);
                if (i % 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    /**
     * 将另一个线程加入到当前线程
     * 常用来等待另一个线程的结束
     * 例如： 需要t1, t2, t3线程按t3、t2、t1顺序运行
     *      可以在t1中先调t2.join()，t2中先调t3.join()，即可按t3、t2、t1顺序运行
     */
    public static void joinTest() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("-----------B" + i);
                try {
                    Thread.sleep(500);
                    if (i == 10) {
                        t1.join();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();
    }
}
