package com.zsm.practice.practice.basic.concurrent.waitnotify;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author shiming.zhao
 * @date 2019/04/09
 */
public class EventStorage {

    private int maxSize;

    private LinkedList<Date> storage;

    public EventStorage(int maxSize) {
        this.maxSize = maxSize;
        this.storage = new LinkedList<>();
    }

    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.offer(new Date());
        System.out.printf("Set: %d", storage.size());
        notifyAll();
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s", storage.
            size(), ((LinkedList<?>)storage).poll());
        notifyAll();
    }

}