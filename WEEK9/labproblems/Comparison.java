class Book {
    String title;
    String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // same reference
        if (obj == null || getClass() != obj.getClass())
            return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }
}

public class Comparison {
    public static void main(String[] args) {
        Book b1 = new Book("1984", "George Orwell");
        Book b2 = new Book("1984", "George Orwell");
        Book b3 = b1;

        System.out.println("b1 == b2 → " + (b1 == b2)); // false (different objects)
        System.out.println("b1.equals(b2) → " + b1.equals(b2)); // true (same content)
        System.out.println("b1 == b3 → " + (b1 == b3)); // true (same reference)
    }
}
