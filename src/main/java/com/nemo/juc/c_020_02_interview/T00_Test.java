package com.nemo.juc.c_020_02_interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Nemo Wong
 * @Date 2021/4/22 17:42
 * @Description
 * 写一个固定容量同步容器，拥有put, get, getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class T00_Test {

    // List<Object> list = new ArrayList<>(12);
    List<Object> syncList = Collections.synchronizedList(new ArrayList<>(12));

    private boolean put(Object obj) {
        return syncList.add(obj);
    }

    private Object get(int index) {
        return syncList.get(index);
    }

    private int getCount() {
        return syncList.size();
    }

}
