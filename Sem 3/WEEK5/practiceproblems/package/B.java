package WEEK5.practiceproblems.package;

package packageB;

import packageA.A;

public class B {
    public static void main(String[] args) {
        A obj = new A();
        System.out.println(obj.pub);   // ✅ Allowed
        // System.out.println(obj.pro); // ❌ Not allowed
        // System.out.println(obj.def); // ❌ Not allowed
        // System.out.println(obj.pri); // ❌ Not allowed
    }
}

