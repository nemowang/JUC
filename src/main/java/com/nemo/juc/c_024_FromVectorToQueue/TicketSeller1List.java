package com.nemo.juc.c_024_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nemo Wong
 * @Date 2021/4/27 17:38
 * @Description
 * 用list作容器会有线程安全问题
 * 有超卖的现象
 */
public class TicketSeller1List {
    static List<String> ticket = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            ticket.add("票 编号:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (ticket.size() > 0) {
                    String remove = ticket.remove(0);
                    System.out.println("销售了----" + remove);
                }
            }).start();
        }
    }
}
