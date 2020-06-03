package jvm.valatile;

public class VolatileTest extends Thread {
    boolean flag = false;
    int i = 0;

    public void run() {
        System.out.println(Thread.currentThread().getName() + " 1111begin.");
        while (!flag) {
            i++;
//            try {
//                Thread.sleep(1000);
//                System.out.println(Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());
        vt.flag = true;
        System.out.println("stope " + vt.i);
    }
}
