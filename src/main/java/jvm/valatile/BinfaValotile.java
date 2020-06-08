package jvm.valatile;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BinfaValotile {
//    private static volatile int flag = 0;
    private static int flag = 0;
    public static ReentrantLock reentrantLock = new ReentrantLock();

    private static void setFlag(int flag) {
        BinfaValotile.flag = flag;
    }

    private static void increase() {
        BinfaValotile.flag++;
    }

    private static int getFlag() {
        return BinfaValotile.flag;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("启动线程");
        List<Thread> tList = new LinkedList<>();
        long begin = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 100_0000; j++) {
//                    synchronized (BinfaValotile.class) {
//                        BinfaValotile.increase();
//                    }

//                    BinfaValotile.reentrantLock.lock();
//                    try {
//                        BinfaValotile.increase();
//                    } finally {
//                        BinfaValotile.reentrantLock.unlock();
//                    }

                    BinfaValotile.increase();
                }
            }, "Thread-" + i);
            t.start();
            tList.add(t);
        }

        System.out.println("Begin wait……");
        for (Thread t : tList) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Flag final value:" + BinfaValotile.getFlag() + " time:" + (end - begin));
        System.out.println("Bye bye.");
    }
}
