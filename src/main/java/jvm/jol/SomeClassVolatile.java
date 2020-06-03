package jvm.jol;

public class SomeClassVolatile {
    //    @Contended
    public long valueA;
    public long valueB;

    public static void main(String[] args) throws InterruptedException {
        SomeClassVolatile someClassVolatile = new SomeClassVolatile();
        someClassVolatile.valueA = 1L;
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " A is " + someClassVolatile.valueA);
            System.out.println(Thread.currentThread().getName() + " B is " + someClassVolatile.valueB);
            while (someClassVolatile.valueA == 1L) {
//                System.out.println();

//2                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

//3 not work      Thread.currentThread().getId();

//                System.out.println(Thread.currentThread().getId() + "----i am in.");
//                System.out.println("----i am in.");
                someClassVolatile.valueB++;
            }
            System.out.println(Thread.currentThread().getId() + "-----i am out.");
        };
        Thread t = new Thread(runnable, "my");
        t.start();
        Thread.sleep(1000);
        someClassVolatile.valueA = 2L;
        System.out.println("B:" + someClassVolatile.valueB);
        t.join();
//        System.out.println(VM.current().details());
//        System.out.println(ClassLayout.parseInstance(someClassVolatile).toPrintable());
    }
}
