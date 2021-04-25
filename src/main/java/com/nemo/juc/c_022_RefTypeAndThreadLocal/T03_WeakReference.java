package com.nemo.juc.c_022_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * @Author Nemo Wong
 * @Date 2021/4/23 18:00
 * @Description 弱引用
 *
 * 不管对象是否为空null，gc都会直接回收
 */
public class T03_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> weakReference = new WeakReference<>(new M());

        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
