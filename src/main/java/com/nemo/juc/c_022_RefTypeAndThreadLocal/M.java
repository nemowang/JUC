package com.nemo.juc.c_022_RefTypeAndThreadLocal;

/**
 * @Author Nemo Wong
 * @Date 2021/4/23 16:57
 * @Description 强弱软虚
 * 软引用 空间不够时释放内存
 * 虚引用 处理堆外内存
 * 弱引用
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
