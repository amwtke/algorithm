package jvm.jol.falsesharing;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class SomeClass {
    public volatile long valueA;
    public volatile long valueB;

    public static void main(String[] args){
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseInstance(new SomeClass()).toPrintable());
    }
}
