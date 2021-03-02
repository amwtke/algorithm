package jvm.mulitcore_mulitthread_programming.java_volitale;

import java.util.concurrent.TimeUnit;

public class VolatileAccumulate {
    static volatile long COUNTER = 0L;
    static int THREADS = 10;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(VolatileAccumulate::loopInc, "Thread-" + i);
            threads[i].start();
        }
        TimeUnit.SECONDS.sleep(1L);

        for (int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        System.out.println("Counter:" + COUNTER);
    }

    private static void loopInc() {
        for (int i = 0; i < 10000; i++) {
//            synchronized (VolatileAccumulate.class) {
                COUNTER ++;//++操作不是原子的，是根本原因
//            }
        }
//        System.out.println("Thread:" + Thread.currentThread().getName() + " Counter:" + COUNTER + " Time:" + System.currentTimeMillis());
    }
}
