package jvm.mulitcore_mulitthread_programming.java_volitale;

import java.util.concurrent.TimeUnit;

public class VolatileCounter {
    static final int LOOP = 10000;
    /**
     * 如果是long在单线程情况下是可以的，跟C++一样。单核没问题，多核因为core cache原因就data race
     * 只有对象才会把对象的primary拷贝到线程中，
     * 如果本来就是primary type，就引用。
     */
    static volatile Long COUNTER = 0L;
    static int THREADS = 100;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(VolatileCounter::loopInc, "Thread-" + i);
            threads[i].start();
        }
        TimeUnit.SECONDS.sleep(1L);

        for (int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        System.out.println("Counter:" + COUNTER);
    }

    private static void loopInc() {
        for (int i = 0; i < LOOP; i++) {
            COUNTER = COUNTER + 1;
        }
        System.out.println("Thread:" + Thread.currentThread().getName() + " Counter:" + COUNTER + " Time:" + System.currentTimeMillis());
    }
}
