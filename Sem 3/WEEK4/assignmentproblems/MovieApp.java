package WEEK4.assignmentproblems;

class Movie {
    String title;
    int price;
    int totalSeats;
    int bookedSeats;

    Movie(String title, int price, int totalSeats) {
        this.title = title;
        this.price = price;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    void bookTickets(int n) {
        if (bookedSeats + n <= totalSeats) {
            bookedSeats += n;
            System.out.println(n + " tickets booked!");
        } else {
            System.out.println("Not enough seats available.");
        }
    }

    void cancelTickets(int n) {
        if (n <= bookedSeats) {
            bookedSeats -= n;
            System.out.println(n + " tickets cancelled!");
        }
    }

    void showStatus() {
        System.out.println("Movie: " + title);
        System.out.println("Price: " + price);
        System.out.println("Booked: " + bookedSeats);
        System.out.println("Available: " + (totalSeats - bookedSeats));
    }
}

public class MovieApp {
    public static void main(String[] args) {
        Movie m = new Movie("Inception", 150, 10);
        m.bookTickets(3);
        m.bookTickets(5);
        m.cancelTickets(2);
        m.showStatus();
    }
}
