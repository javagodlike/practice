package com.zsm.practice.practice.basic.concurrent.waitnotify;

/**
 * @author shiming.zhao
 * @date 2019/04/09
 */
public class Main {

    public static void main(String[] args) {
        EventStorage storage = new EventStorage(50);
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);

        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();
    }

}
