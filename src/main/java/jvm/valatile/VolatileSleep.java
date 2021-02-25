package jvm.valatile;

public class VolatileSleep extends Thread {
    boolean flag = true;
    long count = 0L;

    public void run() {
        while (flag) {
            if (count % 4300000000L == 0 && count != 0L) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileSleep vt = new VolatileSleep();
        vt.start();
        Thread.sleep(1000);
        setFlag(vt);
        vt.join();
        System.out.println("vt value: " + vt.count);
        System.out.println("stop and final flag is:" + vt.flag);
    }

    private static void setFlag(VolatileSleep vt) {
        vt.flag = false;
    }
}
