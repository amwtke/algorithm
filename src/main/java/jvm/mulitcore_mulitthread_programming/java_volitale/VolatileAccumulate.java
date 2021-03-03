package jvm.mulitcore_mulitthread_programming.java_volitale;

public class VolatileAccumulate {
    static long COUNTER = 0L;
    static int THREADS = 10;
    static boolean FLAG = true;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(VolatileAccumulate::loopInc, "Thread-" + i);
            threads[i].start();
        }
        FLAG = false;
        for (int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        System.out.println("Counter:" + COUNTER);
    }

    private static void loopInc() {
        for (int i = 0; i < 10000; i++) {
            if (FLAG) {
                COUNTER++;//++操作不是原子的，是根本原因
            }
        }

        System.out.println("Thread:" + Thread.currentThread().getName() + " Counter:" + COUNTER + " flag:" + FLAG + " Time:" + System.currentTimeMillis());
    }
}
