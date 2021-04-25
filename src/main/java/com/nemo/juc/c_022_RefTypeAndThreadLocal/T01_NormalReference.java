package com.nemo.juc.c_022_RefTypeAndThreadLocal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nemo Wong
 * @Date 2021/4/23 16:57
 * @Description 普通引用
 * 强引用
 *
 * 直接gc不会被回收
 * 置空后，gc会回收空对象
 */
public class T01_NormalReference {

    public static void main(String[] args) throws IOException, InterruptedException {
        M m = new M();
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        // 直接gc不会被回收
        System.out.println(m);

        // 将其置空
        m = null;
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        // gc会回收空对象
        System.out.println(m);
    }
}
