public class UseCase8BookingHistoryReporting {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v8.0");
        System.out.println(" Booking History & Reporting Demo");
        System.out.println("======================================\n");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService(history);

        // Simulate confirmed reservations coming from booking flow
        Reservation r1 = new Reservation("R-1001", "Alice", "Suite Room", "SU1001");
        Reservation r2 = new Reservation("R-1002", "Bob", "Single Room", "SR1002");
        Reservation r3 = new Reservation("R-1003", "Charlie", "Double Room", "DR1003");

        history.add(r1);
        history.add(r2);
        history.add(r3);

        // Read-only retrieval and reporting
        reportService.displayForAdmin();

        System.out.println("\n[Result] Booking history stored in insertion order and reporting generated without modifying history.");
    }
}
