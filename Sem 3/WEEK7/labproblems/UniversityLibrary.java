package WEEK7.labproblems;

class LibraryUser {
    void enterLibrary() {
        System.out.println("User enters the library.");
    }
}

class Student extends LibraryUser {
    void borrowBooks() { System.out.println("Student borrows books."); }
    void accessComputers() { System.out.println("Student accesses computers."); }
}

class Faculty extends LibraryUser {
    void reserveBooks() { System.out.println("Faculty reserves books."); }
    void accessResearchDB() { System.out.println("Faculty accesses research databases."); }
}

class Guest extends LibraryUser {
    void browseBooks() { System.out.println("Guest browses books."); }
}

public class UniversityLibrary {
    public static void main(String[] args) {
        LibraryUser u1 = new Student();  // upcasting
        LibraryUser u2 = new Faculty();
        LibraryUser u3 = new Guest();

        u1.enterLibrary();
        u2.enterLibrary();
        u3.enterLibrary();
    }
}

