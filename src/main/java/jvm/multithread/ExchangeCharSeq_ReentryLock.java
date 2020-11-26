package jvm.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExchangeCharSeq_ReentryLock {
    static char[] aI = "1234567".toCharArray();
    static char[] aC = "ABCDEFG".toCharArray();
    static char[] aD = "一二三四五六七".toCharArray();

    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition condition1 = reentrantLock.newCondition();
    static Condition condition2 = reentrantLock.newCondition();
    static Condition condition3 = reentrantLock.newCondition();

    static Thread t1 = new Thread(() -> {
        reentrantLock.lock();
        try {
            for (char c : aI) {
                System.out.println(c);
                condition2.signal();
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition2.signal();
            condition3.signal();
        } finally {
            reentrantLock.unlock();
        }
    }, "t1");

    static Thread t2 = new Thread(() -> {
        reentrantLock.lock();
        try {
            for (char c : aC) {
                System.out.println(c);
                condition3.signal();
                condition2.await();
            }
            condition3.signal();
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }, "t2");

    static Thread t3 = new Thread(() -> {
        reentrantLock.lock();
        try {
            for (char c : aD) {
                System.out.println(c);
                condition1.signal();
                condition3.await();
            }
            condition2.signal();
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }, "t3");

    public static void main(String[] args) {
        t1.start();
        t2.start();
        t3.start();
    }
}
