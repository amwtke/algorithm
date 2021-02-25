package jvm.synchornized;

import java.util.concurrent.TimeUnit;

public class TestSynchronizedSysCall {
    private static int counter = 0;
    private static Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                synchronized (lock) {
                    for (int j = 0; j < 1; j++) {
                        counter++;
                        System.out.println(Thread.currentThread().getName() + "-" + counter);
                        try {
                            TimeUnit.MILLISECONDS.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "thread" + i).start();
            System.out.println("init" + i);
        }
    }
}
