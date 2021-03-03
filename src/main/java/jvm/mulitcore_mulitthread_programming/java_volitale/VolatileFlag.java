package jvm.mulitcore_mulitthread_programming.java_volitale;

import java.util.concurrent.TimeUnit;

public class VolatileFlag {
    static boolean FLAG = true;
    static long COUNTER = 0L;
    static int THREADS = 10;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(VolatileFlag::loopInc, "Thread-" + i);
            threads[i].start();
        }
        TimeUnit.SECONDS.sleep(1L);
        FLAG = false;
        System.out.println("Counter:" + COUNTER + " FLAG:" + FLAG);

        for (int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        System.out.println("Counter:" + COUNTER);
    }

    private static void loopInc() {
        while (FLAG) {
            COUNTER = COUNTER + 1;
        }
        System.out.println("Thread:" + Thread.currentThread().getName() + " Counter:" + COUNTER + " FLAG:" + FLAG + " Time:" + System.currentTimeMillis());
    }
}
