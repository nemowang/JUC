package com.nemo.juc.c_022_RefTypeAndThreadLocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nemo Wong
 * @Date 2021/4/23 17:39
 * @Description
 * 虚引用
 *
 * 虚引用指向堆外内存(JVM外内存)
 *
 * 内存不足时开始gc垃圾回收。虚引用的对象就会被放入ReferenceQueue队列做特殊处理。
 * gc线程从ReferenceQueue队列读数据，
 */
public class T04_PhantomReference {
    static ReferenceQueue QUEUE = new ReferenceQueue();
    static List<byte[]> LIST = new ArrayList<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<M>(new M(), QUEUE);
        System.out.println(phantomReference.get());

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        // 垃圾回收线程
        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("----- 虚引用对象被JVM回收了 ------" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
