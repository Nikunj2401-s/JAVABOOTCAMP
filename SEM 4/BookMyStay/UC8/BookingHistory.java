import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory {
    private List<Reservation> history;

    public BookingHistory() {
        this.history = new ArrayList<>();
    }

    public void add(Reservation reservation) {
        history.add(reservation);
        System.out.println("[History] Added confirmed reservation: " + reservation.getReservationId());
    }

    public List<Reservation> getAll() {
        return Collections.unmodifiableList(history);
    }

    public int getTotalReservations() {
        return history.size();
    }

    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("[History] No confirmed reservations yet.");
            return;
        }

        System.out.println("\n--- Booking History (chronological) ---");
        for (Reservation res : history) {
            System.out.println(" - " + res);
        }
        System.out.println("--------------------------------------");
    }
}
