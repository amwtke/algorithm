package jvm.multithread;

import java.util.concurrent.TimeUnit;

public class ExchangeCharSeq_objectWaitNotify {
    static char[] aI = "1234567".toCharArray();
    static char[] aC = "ABCDEFG".toCharArray();

    static final Object lockObj = new Object();
    static Thread t1 = new Thread(() -> {
        synchronized (lockObj) {
            for (char c : aI) {
                System.out.println(c);
                lockObj.notify();
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockObj.notify();
            }
        }
    }, "t1");

    static Thread t2 = new Thread(() -> {
        synchronized (lockObj) {
            for (char c : aC) {
                System.out.println(c);
                lockObj.notify();
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockObj.notify();
            }
        }
    }, "t2");

    public static void main(String[] args) {
        t1.start();
        t2.start();
    }
}
