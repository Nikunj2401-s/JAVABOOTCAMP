import java.util.*;

// ---------------------------
// Book Class
// ---------------------------
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    public void setBorrowed(boolean status) {
        this.isBorrowed = status;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public void getDetails() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Borrowed: " + isBorrowed);
    }
}

// ---------------------------
// Library Class (Composition → owns Books)
// ---------------------------
class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to " + name);
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println(book.getTitle() + " removed from " + name);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void showBooks() {
        System.out.println("\nBooks in " + name + ":");
        for (Book book : books) {
            book.getDetails();
        }
    }

    // Composition: when library is deleted, all books are also deleted
    public void deleteLibrary() {
        books.clear();
        System.out.println(name + " and all its books have been deleted.");
    }
}

// ---------------------------
// Member Class (Association → borrows Books)
// ---------------------------
class Member {
    private int memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            borrowedBooks.add(book);
            book.setBorrowed(true);
            System.out.println(name + " borrowed: " + book.getTitle());
        } else {
            System.out.println(book.getTitle() + " is already borrowed.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setBorrowed(false);
            System.out.println(name + " returned: " + book.getTitle());
        } else {
            System.out.println(name + " didn't borrow this book.");
        }
    }

    public void showBorrowedBooks() {
        System.out.println("\n" + name + "'s Borrowed Books:");
        for (Book book : borrowedBooks) {
            book.getDetails();
        }
    }
}

// ---------------------------
// Main Class
// ---------------------------
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Create Library
        Library library = new Library("City Library");

        // Create Books
        Book b1 = new Book("1984", "George Orwell", "ISBN001");
        Book b2 = new Book("The Alchemist", "Paulo Coelho", "ISBN002");
        Book b3 = new Book("Clean Code", "Robert C. Martin", "ISBN003");

        // Add Books to Library
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.showBooks();

        // Create Member
        Member m1 = new Member(101, "Alice");

        // Borrow and Return Books
        m1.borrowBook(b1);
        m1.borrowBook(b2);
        m1.showBorrowedBooks();

        m1.returnBook(b1);
        m1.showBorrowedBooks();

        // Delete Library (Composition)
        library.deleteLibrary();
    }
}
