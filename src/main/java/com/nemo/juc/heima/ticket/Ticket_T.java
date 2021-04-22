package com.nemo.juc.heima.ticket;

/**
 * @Author Nemo Wong
 * @Date 2021/4/19 11:39
 * @Description
 */
public class Ticket_T {

    public static void main(String[] args) {
        SaleTickets s = new SaleTickets();
        // 5个卖票窗口同时卖票
        new Thread(s, "S1").start();
        new Thread(s, "S2").start();
        new Thread(s, "S3").start();
        new Thread(s, "S4").start();
        new Thread(s, "S5").start();
    }
}
