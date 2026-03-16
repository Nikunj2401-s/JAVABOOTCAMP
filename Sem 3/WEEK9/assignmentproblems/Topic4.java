import java.util.*;

class Book implements Cloneable {
    String title;

    Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Library implements Cloneable {
    List<Book> books;

    Library(List<Book> books) {
        this.books = books;
    }

    // Shallow copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Deep copy
    protected Library deepClone() throws CloneNotSupportedException {
        List<Book> copiedBooks = new ArrayList<>();
        for (Book b : this.books) {
            copiedBooks.add((Book) b.clone());
        }
        return new Library(copiedBooks);
    }

    @Override
    public String toString() {
        return books.toString();
    }
}

public class Topic4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Java Basics"));
        bookList.add(new Book("Python Advanced"));
        bookList.add(new Book("C++ Fundamentals"));

        Library original = new Library(bookList);

        // Shallow clone
        Library shallowClone = (Library) original.clone();

        // Deep clone
        Library deepClone = original.deepClone();

        // Modify one book in cloned lists
        shallowClone.books.get(0).title = "Java Modified";
        deepClone.books.get(1).title = "Python Modified";

        System.out.println("Original Library (after shallow change): " + original);
        System.out.println("Shallow Cloned Library: " + shallowClone);
        System.out.println("Deep Cloned Library: " + deepClone);
    }
}
