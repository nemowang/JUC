package com.nemo.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 17:18
 * @Description
 */
public class T06_LockSupport {
    /*volatile*/ List<Object> list = new ArrayList<>();

    private int getSize() {
        return list.size();
    }

    private void add(Object o) {
        list.add(o);
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        T06_LockSupport c = new T06_LockSupport();

        t1 = new Thread(() -> {
            System.out.println("t1 开始");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                if (i == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
            System.out.println("t1 结束");
        }, "T1");

        t2 = new Thread(() -> {
            System.out.println("t2 开始");
            if (c.getSize() != 5) {
                LockSupport.park();
            }
            System.out.println("t2 结束");
            LockSupport.unpark(t1);
        }, "T2");

        t2.start();
        t1.start();
    }
}
