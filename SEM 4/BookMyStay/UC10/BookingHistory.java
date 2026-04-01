import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory {
    private final List<Reservation> confirmed = new ArrayList<>();

    public void add(Reservation reservation) {
        confirmed.add(reservation);
        System.out.println("[History] Booking confirmed: " + reservation.getReservationId());
    }

    public void remove(Reservation reservation) {
        confirmed.remove(reservation);
        System.out.println("[History] Booking cancelled: " + reservation.getReservationId());
    }

    public List<Reservation> getAll() {
        return Collections.unmodifiableList(confirmed);
    }

    public void display() {
        System.out.println("\n--- Booking History ---");
        if (confirmed.isEmpty()) {
            System.out.println("No historical bookings.");
        } else {
            confirmed.forEach(r -> System.out.println(" - " + r));
        }
        System.out.println("-----------------------");
    }
}
