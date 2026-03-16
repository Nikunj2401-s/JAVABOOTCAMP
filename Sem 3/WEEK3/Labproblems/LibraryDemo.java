package javabootcamp.WEEK3.Labproblems;

// Author class
class Author {
    String name;

    Author(String name) {
        this.name = name;
    }
}

// Book class
class Book {
    String title;
    Author author;

    Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    void displayInfo() {
        System.out.println("Book: " + title + " | Author: " + author.name);
    }
}

// Library class
class Library {
    Book[] books;
    int count;

    Library(int size) {
        books = new Book[size];
        count = 0;
    }

    void addBook(Book book) {
        if (count < books.length) {
            books[count++] = book;
            System.out.println("Added: " + book.title);
        } else {
            System.out.println("Library is full!");
        }
    }

    void showBooks() {
        System.out.println("\n--- Library Books ---");
        for (int i = 0; i < count; i++) {
            books[i].displayInfo();
        }
    }
}

// Main class
public class LibraryDemo {
    public static void main(String[] args) {
        Author a1 = new Author("J.K. Rowling");
        Author a2 = new Author("George Orwell");

        Book b1 = new Book("Harry Potter", a1);
        Book b2 = new Book("1984", a2);

        Library library = new Library(5);
        library.addBook(b1);
        library.addBook(b2);

        library.showBooks();
    }
}

