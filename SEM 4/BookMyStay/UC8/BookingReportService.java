import java.util.List;

public class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void printSummary() {
        List<Reservation> bookings = history.getAll();
        System.out.println("\n--- Booking Report Summary ---");
        System.out.println("Total confirmed bookings: " + bookings.size());

        long suiteCount = bookings.stream().filter(b -> b.getRoomType().equals("Suite Room")).count();
        long singleCount = bookings.stream().filter(b -> b.getRoomType().equals("Single Room")).count();
        long doubleCount = bookings.stream().filter(b -> b.getRoomType().equals("Double Room")).count();

        System.out.println("Suite bookings: " + suiteCount);
        System.out.println("Single bookings: " + singleCount);
        System.out.println("Double bookings: " + doubleCount);

        System.out.println("--------------------------------");
    }

    public void displayForAdmin() {
        System.out.println("\n[Admin] Booking history for review:");
        history.displayHistory();
        printSummary();
    }
}
