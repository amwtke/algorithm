package jvm.mulitcore_mulitthread_programming.hsdis;

public class VolatileNotOk extends Thread {
    boolean flag = true;
    long i = 0L;

    public static void main(String[] args) throws Exception {
        VolatileNotOk vt = new VolatileNotOk();
        System.out.println(vt.hashCode());
        vt.start();
        Thread.sleep(1000);//挂起下主线程，确保子线程完成初始化并启动成功。
        setFlag(vt);//设置flag，尝试停止线程
        System.out.println("vt value: " + vt.i);
        vt.join();
        System.out.println("stop and final flag is:" + vt.flag);
    }

    @Override
    public void run() {
        System.out.println(this.hashCode());
        while (getFlag(this)) {
            i++;
        }
    }

    private static void setFlag(VolatileNotOk vt) {
        vt.flag = false;
    }

    private static boolean getFlag(VolatileNotOk vt) {
        return vt.flag;
    }
}
