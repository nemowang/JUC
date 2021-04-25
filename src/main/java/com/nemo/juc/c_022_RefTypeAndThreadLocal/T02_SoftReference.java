package com.nemo.juc.c_022_RefTypeAndThreadLocal;

import java.lang.ref.SoftReference;

/**
 * @Author Nemo Wong
 * @Date 2021/4/23 17:06
 * @Description
 * 软引用
 *
 * 内存空间不足时，软引用会被gc回收
 *
 * 软引用非常适合缓存使用
 */
public class T02_SoftReference {

    public static void main(String[] args) {
        // 10M大小字节数组        m强引用SoftReference，SoftReference软引用byte[1024 * 1024 * 10]
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());

        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 内存空间充足的情况下，gc垃圾回收机制不会回收软引用
        System.out.println(m.get());

        // 强引用12M大小字节数组
        byte[] bytes = new byte[1024 * 1024 * 12];

        // 设置JVM最大内存20M   -Xms20m -Xmx20m
        // 内存不足，软引用被gc回收
        System.out.println(m.get());
    }
}
