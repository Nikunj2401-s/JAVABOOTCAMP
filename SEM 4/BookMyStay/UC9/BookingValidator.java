import java.util.HashSet;
import java.util.Set;

public class BookingValidator {
    private static final Set<String> VALID_ROOM_TYPES = new HashSet<>();

    static {
        VALID_ROOM_TYPES.add("Single Room");
        VALID_ROOM_TYPES.add("Double Room");
        VALID_ROOM_TYPES.add("Suite Room");
    }

    public void validateReservationInput(Reservation reservation) throws InvalidBookingException {
        if (reservation == null) {
            throw new InvalidBookingException("Reservation cannot be null.");
        }
        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new InvalidBookingException("Guest name is required.");
        }
        if (!VALID_ROOM_TYPES.contains(reservation.getRoomType())) {
            throw new InvalidBookingException("Invalid room type: " + reservation.getRoomType());
        }
    }

    public void validateInventoryForBooking(RoomInventory inventory, String roomType) throws InvalidBookingException {
        if (inventory == null) {
            throw new InvalidBookingException("Inventory system is not initialized.");
        }
        int available = inventory.getAvailableRooms(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
        if (available - 1 < 0) {
            throw new InvalidBookingException("Inventory count would become negative for room type: " + roomType);
        }
    }
}
