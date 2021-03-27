package jvm.jol.falsesharing;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import sun.misc.Contended;

public class SomeClass {
    @Contended
    public volatile long valueA;
    public volatile long valueB;

    public static void main(String[] args){
        SomeClass instance = new SomeClass();
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseInstance(instance).toPrintable());
    }
}
