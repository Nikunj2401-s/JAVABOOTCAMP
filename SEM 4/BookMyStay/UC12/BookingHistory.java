import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Reservation> confirmed = new ArrayList<>();

    public void add(Reservation reservation) {
        confirmed.add(reservation);
    }

    public List<Reservation> getConfirmed() {
        return Collections.unmodifiableList(confirmed);
    }

    public void display() {
        System.out.println("\n--- Booking History ---");
        confirmed.forEach(r -> System.out.println(" - " + r));
        System.out.println("-----------------------");
    }
}
