package com.zsm.practice.practice.basic.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程协调
 *
 * @author shiming.zhao
 * @date 2019/04/09
 */
public class MultiThreadCoordinator {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();
        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println(1);
                    c2.signal();
                    c1.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println(2);
                    c3.signal();
                    c2.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println(3);
                    c1.signal();
                    c3.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

    }

}
