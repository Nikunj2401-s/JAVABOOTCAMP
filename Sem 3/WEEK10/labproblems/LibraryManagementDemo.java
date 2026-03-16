import java.util.*;

class Book {
    private String title;
    private String author;
    private int id;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public void getDetails() {
        System.out.println("Book ID: " + id + " | " + title + " by " + author);
    }

    public String getTitle() {
        return title;
    }
}

class Member {
    private String name;
    private int memberId;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        System.out.println(name + " borrowed " + book.getTitle());
    }

    public void showBorrowedBooks() {
        System.out.println("Books borrowed by " + name + ":");
        for (Book b : borrowedBooks) {
            b.getDetails();
        }
    }
}

class Librarian {
    private String name;
    private int employeeId;
    private List<Book> managedBooks = new ArrayList<>();

    public Librarian(String name, int employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    public void addBook(Book b) {
        managedBooks.add(b);
        System.out.println(name + " added book: " + b.getTitle());
    }
}

public class LibraryManagementDemo {
    public static void main(String[] args) {
        Librarian lib = new Librarian("Ravi", 1001);
        Book b1 = new Book("Java Basics", "James Gosling", 1);
        Book b2 = new Book("Python Guide", "Guido van Rossum", 2);

        lib.addBook(b1);
        lib.addBook(b2);

        Member m1 = new Member("Anita", 501);
        m1.borrowBook(b1);
        m1.borrowBook(b2);

        m1.showBorrowedBooks();
    }
}
