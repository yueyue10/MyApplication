package com.encdata.zyj.myapplication.lombok;

import lombok.Synchronized;

/**
 * @Synchronized 给方法加上同步锁
 */
public class SynchronizedExample {

    private static final Object readLock = new Object();

    @Synchronized
    public static void hello() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
        }
    }

    @Synchronized
    public static int answerToLife() {
        System.out.println(42 + "");
        return 42;
    }

    @Synchronized("readLock")
    public static void foo() {
        System.out.println("bar");
    }
}
