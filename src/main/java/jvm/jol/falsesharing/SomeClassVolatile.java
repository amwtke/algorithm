package jvm.jol.falsesharing;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import sun.misc.Contended;

public class SomeClassVolatile {
    @Contended
    public long valueA;
    public long valueB;

    public static void main(String[] args) {
        SomeClassVolatile someClassVolatile = new SomeClassVolatile();
        someClassVolatile.valueA = 1L;
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseInstance(someClassVolatile).toPrintable());
    }
}
