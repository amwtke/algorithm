package jvm.multithread;

import java.util.concurrent.TimeUnit;

/*
wait即object的wait()和notify()或者notifyall()一起搭配使用
wait方法会将当前线程放入wait set等待被唤醒

1.将当前线程封装成objectwaiter对象node
2.通过objectmonitor::addwaiter方法将node添加到_WaitSet列表中
3.通过ObjectMonitor:exit方法释放当前的ObjectMonitor对象，这样其他竞争线程就可以获取该ObjectMonitor对象
4.最终底层的park方法会挂起线程
notiry方法就是随机唤醒等待池中的一个线程
 */

/*
详细的原理笔记
https://app.yinxiang.com/shard/s65/nl/15273355/3076c760-ddcd-48e3-acff-05b365a57a28/
 */
public class ExchangeCharSeq_objectWaitNotify {
    static char[] aI = "1234567".toCharArray();
    static char[] aC = "ABCDEFG".toCharArray();

    static final Object lockObj = new Object();
    static Thread t1 = new Thread(() -> {
        synchronized (lockObj) {
            for (char c : aI) {
                System.out.println(c);
                lockObj.notify();
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockObj.notify();
            }
        }
    }, "t1");

    static Thread t2 = new Thread(() -> {
        synchronized (lockObj) {
            for (char c : aC) {
                System.out.println(c);
                lockObj.notify();
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockObj.notify();
            }
        }
    }, "t2");

    public static void main(String[] args) {
        t1.start();
        t2.start();
    }
}
