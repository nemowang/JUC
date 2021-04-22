package com.nemo.juc.c_009;

import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/12 17:57
 * @Description synchronized重入锁机制在继承中的验证
 * 父类方法加上synchronized锁
 */
public class T_Father {

    synchronized void m() {
        System.out.println("T_Father m start....");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("T_Father m end");
    }
}
