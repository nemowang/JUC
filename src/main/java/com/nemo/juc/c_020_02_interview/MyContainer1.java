package com.nemo.juc.c_020_02_interview;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Nemo Wong
 * @Date 2021/4/23 10:04
 * @Description
 * 写一个固定容量同步容器，拥有put, get, getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 生产者不断调用put方法，
 * 消费者不断调用get方法
 *
 * 局限性：
 *  put后唤醒的是所有线程，也就是说既会唤醒消费者，也会唤醒生产者。如果容器已满，而生产者线程又争抢到锁，会造成资源浪费
 *  同理，get后唤醒的是所有线程，也就是说既会唤醒生产者，也会唤醒消费者。如果容器已空，而消费者线程又争抢到锁，会造成资源浪费
 */
public class MyContainer1<T> {
    private final static int MAX = 10;

    private final LinkedList<T> list = new LinkedList<>();
    private int count = 0;

    private synchronized void put(T t) {
        while (list.size() == MAX) {
            // 使用while，不用if
            // 因为wait()被唤醒后执行下一步前应该再次判断list.size()
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        count ++;
        this.notifyAll();
    }

    private synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count --;
        this.notifyAll();
        return t;
    }

    private int getCount() {
        return count;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1();

        for (int i = 0; i < 10; i++) {
            // 10个消费者
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " get " + c.get());
                }
            }, "C" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            // 2个生产者
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                    // System.out.println(Thread.currentThread().getName() + " put " + j);
                }
            }, "P" + i).start();
        }
    }
}
