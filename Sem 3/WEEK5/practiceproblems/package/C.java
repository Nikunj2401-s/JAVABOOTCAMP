package WEEK5.practiceproblems.package;

package packageB;

import packageA.A;

public class C extends A {
    public void test() {
        System.out.println(pub);   // ✅ Allowed
        System.out.println(pro);   // ✅ Allowed (via inheritance)
        // System.out.println(def); // ❌ Not allowed
        // System.out.println(pri); // ❌ Not allowed
    }
}

