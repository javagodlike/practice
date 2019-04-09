package com.zsm.practice.practice.basic.concurrent;

/**
 * 使用wait notify 实现3个线程的同步工作
 *
 * @author shiming.zhao
 * @date 2019/04/09
 */
public class MultiThreadCoordinator2 {

    static Object moniter = new Object();

    static String lastCharacter = "3";

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (moniter) {
               while(true) {
                   try {
                       if (lastCharacter.equals("3")) {
                           System.out.println("1");
                           lastCharacter = "1";
                       }
                       moniter.notifyAll();
                       moniter.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }).start();

        new Thread(() -> {
            synchronized (moniter) {
                while (true) {
                    try {
                        if (lastCharacter.equals("1")) {
                            System.out.println("2");
                            lastCharacter = "2";
                        }
                        moniter.notifyAll();
                        moniter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (moniter) {
               while(true) {
                   try {
                       if (lastCharacter.equals("2")) {
                           System.out.println("3");
                           lastCharacter = "3";
                       }
                       moniter.notifyAll();
                       moniter.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }).start();
    }

}
