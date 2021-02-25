package jvm.mulitcore_mulitthread_programming.java_volitale;

public class VolatileOkUsePrint extends Thread {
    boolean flag = true;
    long count = 0L;

    public void run() {
        while (flag) {
            if (count % 4300000000L == 0 && count != 0L) {//让程序运行久一点
                System.out.println(count);
            }
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileOkUsePrint vt = new VolatileOkUsePrint();
        vt.start();
        Thread.sleep(1000);
        setFlag(vt);
        vt.join();
        System.out.println("vt value: " + vt.count);
        System.out.println("stop and final flag is:" + vt.flag);
    }

    private static void setFlag(VolatileOkUsePrint vt) {
        vt.flag = false;
    }
}
