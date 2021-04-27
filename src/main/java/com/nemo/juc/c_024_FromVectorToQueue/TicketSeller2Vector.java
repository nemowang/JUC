package com.nemo.juc.c_024_FromVectorToQueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/27 17:42
 * @Description
 * Array index out of range: 0
 * Vector的每个方法上面都有synchronized
 * 代码中ticket.size() 与 ticket.remove()中间没有加锁，所以整个写法不是线程安全的
 */
public class TicketSeller2Vector {
    static Vector<String> ticket = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            ticket.add("票 编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (ticket.size() > 0) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String remove = ticket.remove(0);
                    System.out.println("销售了---" + remove);
                }
            }).start();
        }
    }
}
