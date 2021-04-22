package com.nemo.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author Nemo Wong
 * @Date 2021/4/21 16:19
 * @Description
 * 循环栅栏
 * 满人发车
 * 将Thread看作人，将CyclicBarrier看作汽车
 * 初始化时设置汽车座位数(线程数)，满人后的动作(线程满后的指令)
 */
public class T07_TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> System.out.println("满人，发车"));

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    // 等待足够数量的线程。数量由初始化时设定
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
