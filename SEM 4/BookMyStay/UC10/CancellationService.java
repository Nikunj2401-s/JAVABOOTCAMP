import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CancellationService {
    private RoomInventory inventory;
    private BookingHistory history;
    private Map<String, Reservation> activeReservations;
    private Map<String, Reservation> cancelledReservations;
    private Stack<String> rollbackStack;

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
        this.activeReservations = new HashMap<>();
        this.cancelledReservations = new HashMap<>();
        this.rollbackStack = new Stack<>();
    }

    public void registerConfirmed(Reservation reservation) {
        activeReservations.put(reservation.getReservationId(), reservation);
        history.add(reservation);
    }

    public void cancelReservation(String reservationId) {
        if (!activeReservations.containsKey(reservationId)) {
            System.out.println("[Cancellation Failed] Reservation not found or already canceled: " + reservationId);
            return;
        }

        Reservation removed = activeReservations.remove(reservationId);
        cancelledReservations.put(reservationId, removed);
        rollbackStack.push(removed.getRoomId());

        inventory.incrementRoom(removed.getRoomType());
        history.remove(removed);

        System.out.println("[Cancellation Success] " + reservationId + " rolled back. Room " + removed.getRoomId() + " is now available.");
    }

    public void displayRollbackStack() {
        System.out.println("\n--- Rollback Stack (recent first) ---");
        if (rollbackStack.isEmpty()) {
            System.out.println("<empty>");
        } else {
            rollbackStack.forEach(id -> System.out.println(" - " + id));
        }
        System.out.println("-----------------------------------");
    }
}
