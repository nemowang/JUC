package com.nemo.juc.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nemo Wong
 * @Date 2021/4/20 16:43
 * @Description
 * volatile可以保证线程的可见性，不能保证原子性。也就是说volatile不能替代synchronized
 * 解决：在m()中使用synchronized
 */
public class T {

    /*int count = 0;

    synchronized void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }*/

    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T t = new T();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach(Thread::start);

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
