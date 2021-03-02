package jvm.mulitcore_mulitthread_programming.java_volitale;

import java.util.concurrent.TimeUnit;

public class VolatileMoreThanOneThread {
    static volatile long flag = 0L;
    static long counter = 0L;
    static int THREADS = 10;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(VolatileMoreThanOneThread::loop, "Thread-" + i);
            threads[i].start();
        }
        TimeUnit.SECONDS.sleep(1L);

        setFlag();

        for (int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        System.out.println("Final Counter:" + counter);
    }

    public static void loop() {
        while (flag == 0L) {
            counter++;
        }
        System.out.println("Counter:" + counter);
    }

    private static void setFlag() {
        flag++;
    }
}
