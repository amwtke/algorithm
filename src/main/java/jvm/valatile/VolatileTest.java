package jvm.valatile;

public class VolatileTest extends Thread {
    boolean flag = true;
    long count = 0L;

    @Override
    public void run() {
        while (flag) {
            if (count % 4300000000L == 0 && count != 0L) {
                sleepThread();
            }
            count++;
        }
    }

    private static void sleepThread() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileTest vt = new VolatileTest();
        vt.start();
        Thread.sleep(1000);
        setFlag(vt);
        vt.join();
        System.out.println("vt value: " + vt.count);
        System.out.println("stop and final flag is:" + vt.flag);
    }

    private static void setFlag(VolatileTest vt) {
        vt.flag = false;
    }
}
