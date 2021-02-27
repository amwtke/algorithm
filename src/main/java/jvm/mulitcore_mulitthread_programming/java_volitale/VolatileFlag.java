package jvm.mulitcore_mulitthread_programming.java_volitale;

import java.util.concurrent.TimeUnit;

public class VolatileFlag {
    static volatile boolean FLAG = true;
    static volatile long COUNTER = 0L;
    static int THREADS = 100;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(VolatileFlag::loopInc, "Thread-" + i);
            threads[i].start();
        }
        TimeUnit.SECONDS.sleep(1L);
        FLAG = false;
        for (int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        System.out.println("Counter:" + COUNTER);
    }

    private static void loopInc() {
        while (FLAG) {
            COUNTER = COUNTER + 1;
        }
        System.out.println("Thread:" + Thread.currentThread().getName() + " Counter:" + COUNTER + " Time:" + System.currentTimeMillis());
    }
}