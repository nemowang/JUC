package com.nemo.juc.c_024_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/27 17:48
 * @Description
 * 使用synchronized保证size()与remove()操作的原子性
 */
public class TicketSeller3SynchronizedList {
    static List<String> ticket = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            ticket.add("票 编号" + i);
        }
    }

     public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (ticket) {
                        if (ticket.size() <= 0) break;
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        String remove = ticket.remove(0);
                        System.out.println("销售了--" + remove);
                    }
                }
            }).start();
        }
    }
}
