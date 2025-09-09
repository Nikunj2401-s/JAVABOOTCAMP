package WEEK5.practiceproblems;

// AccessModifiersOneFile.java
// One file demonstrating private, default (package-private), protected, public.

public class AccessModifiersOneFile {
    public static void main(String[] args) {
        System.out.println("Access Modifiers Demo (single file)\n");

        SamePackageNonSubclass.run();
        SamePackageSubclass.run();

        new DifferentPackageSubclassSim().run();
        DifferentPackageNonSubclassSim.run();
    }
}

/* ======================= BASE CLASS (the source of truth) ======================= */
class Base {
    public    int pubField     = 1;
    protected int protField    = 2;
              int defaultField = 3;   // package-private (aka "default")
    private   int privField    = 4;

    public void insideClass() {
        System.out.println("[Inside Base] public=" + pubField +
                ", protected=" + protField +
                ", default=" + defaultField +
                ", private=" + privField);
    }
}

/* ======================= SAME PACKAGE, NON-SUBCLASS ======================= */
class SamePackageNonSubclass {
    static void run() {
        Base b = new Base();

        // In the SAME package (this file), public/protected/default are accessible.
        System.out.println("[Same pkg, non-subclass] public=" + b.pubField +
                ", protected=" + b.protField +
                ", default=" + b.defaultField);
        // b.privField; // ❌ NOT allowed anywhere outside Base

        // Method inside Base can of course see everything:
        b.insideClass();
    }
}

/* ======================= SAME PACKAGE, SUBCLASS ======================= */
class SamePackageSubclass extends Base {
    static void run() {
        SamePackageSubclass s = new SamePackageSubclass();

        // In the SAME package, a subclass can access public/protected/default.
        System.out.println("[Same pkg, subclass] public=" + s.pubField +
                ", protected=" + s.protField +
                ", default=" + s.defaultField);
        // s.privField; // ❌ NOT allowed (private is only within Base)
    }
}

/* ======================= "DIFFERENT PACKAGE" — SIMULATED =======================
   NOTE: This is still the same file/package so it compiles.
   The comments show what would be ILLEGAL if this class lived in a DIFFERENT package.
   Key rule: In a different package, 'protected' is accessible only via inheritance
   (i.e., on 'this' or subclass reference), NOT via a Base reference.
=============================================================================== */
class DifferentPackageSubclassSim extends Base {
    void run() {
        // ✅ Would be allowed from a different package because we're a subclass:
        System.out.println("[Different pkg (sim), subclass] public=" + this.pubField +
                ", protected=" + this.protField);

        // ❌ Would be ILLEGAL if this were truly in another package:
        // int x = new Base().protField;   // not allowed via Base ref across packages
        // int y = this.defaultField;      // default/package-private not visible across packages
        // int z = this.privField;         // never allowed
    }
}

/* ======================= "DIFFERENT PACKAGE", NON-SUBCLASS — SIMULATED ========
   If this were in another package AND not a subclass:
   - Only public would be accessible.
   - protected/default/private would NOT be accessible.
=============================================================================== */
class DifferentPackageNonSubclassSim {
    static void run() {
        Base b = new Base();
        System.out.println("[Different pkg (sim), non-subclass] public=" + b.pubField);
        // b.protField;    // ❌ would be illegal from another package
        // b.defaultField; // ❌ would be illegal from another package
        // b.privField;    // ❌ never allowed
    }
}

