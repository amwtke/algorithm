package jvm.valatile;

import sun.misc.Contended;

public class VolatileTest extends Thread {
    @Contended
//    int flag = 0;
            Integer flag = 0;
    //    Boolean flag = false;

            Integer i = 0;
//    long i = 0L;

    public void run() {
        System.out.println("Begin - " + Thread.currentThread().getName() + " flag hashcode:" + flag.hashCode());
        while (flag == 0) {
            i++;

//            if (i == 100000) {
//                System.out.println(Thread.currentThread().getName() + " flag hashcode:" + flag.hashCode());
//            }

//            if (i == 3793651106L) {
//                System.out.println(Thread.currentThread().getName() + " flag is:" + flag);
//            }

//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println(Thread.currentThread().getName() + " child out. i:" + i);
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(1000);
        vt.flag = 1;
        System.out.println("stop " + vt.i);
        System.out.println(Thread.currentThread().getName() + " flag hashcode:" + vt.flag.hashCode());
        vt.join();
        System.out.println("flag " + vt.flag);
//        System.out.println(ClassLayout.parseInstance(vt).toPrintable());
    }
}
