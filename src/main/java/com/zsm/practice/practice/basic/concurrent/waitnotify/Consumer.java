package com.zsm.practice.practice.basic.concurrent.waitnotify;

/**
 * @author shiming.zhao
 * @date 2019/04/09
 */
public class Consumer implements Runnable {
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.get();
        }
    }
}
