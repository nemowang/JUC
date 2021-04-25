package com.nemo.juc.c_020_02_interview;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Nemo Wong
 * @Date 2021/4/23 10:25
 * @Description
 * 使用Condition解决notifyAll()的局限性
 * 每个Condition其实是一个线程等待队列
 * 将生产者队列和消费者队列，根据情况分别唤醒和阻塞相应队列，提升资源利用率
 */
public class MyContainer2<T> {
    private final static int MAX = 10;
    private final LinkedList<T> list = new LinkedList<>();
    private int count = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    private void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                // 使用while，不用if
                // 因为wait()被唤醒后执行下一步前应该再次判断list.size()

                // 如果容器已满，生产者队列阻塞
                producer.await();
            }

            list.add(t);
            count ++;

            // 唤醒消费者队列
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private T get() {
        try {
            lock.lock();
            T t = null;
            while (list.size() == 0) {
                // 如果容器已空，消费者队列阻塞
                consumer.await();
            }

            t = list.removeFirst();
            count --;
            // 唤醒生产者队列
            producer.signalAll();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();

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
                }
            }, "P" + i).start();
        }
    }
}
