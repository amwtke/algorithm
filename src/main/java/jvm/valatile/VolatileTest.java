package jvm.valatile;

import sun.misc.Contended;

public class VolatileTest extends Thread {
    @Contended
//    int flag = 0;
    volatile Integer flag = 0;
    //    Boolean flag = false;

    //            Integer i = 0;
    long i = 0L;

    public void run() {
//        System.out.println("Begin - " + Thread.currentThread().getName() + " flag hashcode:" + flag.hashCode());
        while (flag == 0) {
            i++;


//            if (i == 100) {
//                System.out.println(Thread.currentThread().getName() + " flag is:" + flag);
//            }
//
//            if (i == 3793651106L) {
////                System.out.println(Thread.currentThread().getName() + " flag is:" + flag);
//                break;
//            }

//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
//        System.out.println(Thread.currentThread().getName() + " child out. i:" + i);
//        if (flag == 1) {
//            System.out.println(Thread.currentThread().getName() + " flag is:" + flag);
//        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(1000);
        setFlag(vt);
        System.out.println("stop " + vt.i);
        System.out.println(Thread.currentThread().getName() + " flag hashcode:" + vt.flag.hashCode());
        vt.join();
        System.out.println("flag " + vt.flag);
//        System.out.println(ClassLayout.parseInstance(vt).toPrintable());
    }

    private static void setFlag(VolatileTest vt) {
        vt.flag = 1;
    }
}
