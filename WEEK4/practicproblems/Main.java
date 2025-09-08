package javabootcamp.WEEK4.practiceproblems;
class Book {
    // Attributes
    String title;
    String author;
    double price;

    // Default constructor
    Book() {
        title = "Unknown Title";
        author = "Unknown Author";
        price = 0.0;
    }

    // Parameterized constructor
    Book(String t, String a, double p) {
        title = t;
        author = a;
        price = p;
    }

    // Method to display book details
    void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("----------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        // Object created using default constructor
        Book book1 = new Book();

        // Object created using parameterized constructor
        Book book2 = new Book("The Alchemist", "Paulo Coelho", 499.99);

        // Display details of both books
        System.out.println("Book 1 (Default Constructor):");
        book1.display();

        System.out.println("Book 2 (Parameterized Constructor):");
        book2.display();
    }
}
