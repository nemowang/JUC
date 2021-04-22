package com.nemo.juc.c_020;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 8:53
 * @Description Phaser 阶段栅栏
 */
public class T09_TestPhaser2 {
    static Random r = new Random();
    static Phaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        // 指定初始阶段线程数
        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    private static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("所有人到到齐！ 现阶段人数：" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人到吃完！ 现阶段人数：" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人到离开！ 现阶段人数：" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘入洞房！ 现阶段人数：" + registeredParties);
                    System.out.println();
                    return true;
                default:
                    System.out.println("default");
                    return true;
            }
        }
    }

    private static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场!\n", name);

            // 完成并等待下一阶段运行。等待所有其他线程到达此阶段，然后开始下一阶段
            phaser.arriveAndAwaitAdvance();
        }

        private void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);

            phaser.arriveAndAwaitAdvance();
        }

        private void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开!\n", name);

            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if ("新郎".equals(name) || "新娘".equals(name)) {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房!\n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
                // 完成并解注册
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();
        }
    }

    static void milliSleep(int time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
