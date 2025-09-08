package WEEK4.assignmentproblems;

import java.util.*;

class Library {
    ArrayList<String> books = new ArrayList<>();

    void addBook(String b) {
        books.add(b);
        System.out.println(b + " added.");
    }

    void borrowBook(String b) {
        if (books.remove(b)) {
            System.out.println("Borrowed: " + b);
        } else {
            System.out.println("Book not available.");
        }
    }

    void returnBook(String b) {
        books.add(b);
        System.out.println("Returned: " + b);
    }

    void showBooks() {
        System.out.println("Available books: " + books);
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Library L = new Library();
        L.addBook("Dune");
        L.addBook("1984");
        L.borrowBook("1984");
        L.returnBook("1984");
        L.showBooks();
    }
}

