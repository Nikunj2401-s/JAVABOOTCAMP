public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v9.0");
        System.out.println(" Error Handling & Validation Demo");
        System.out.println("======================================\n");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 1);
        inventory.addRoomType("Double Room", 0);
        inventory.addRoomType("Suite Room", 2);

        BookingValidator validator = new BookingValidator();

        Reservation valid = new Reservation("R-1010", "Grace", "Suite Room");
        Reservation invalidType = new Reservation("R-1011", "Henry", "Penthouse");
        Reservation outOfStock = new Reservation("R-1012", "Ivy", "Double Room");

        processReservation(valid, inventory, validator);
        processReservation(invalidType, inventory, validator);
        processReservation(outOfStock, inventory, validator);

        System.out.println("\n[Final Inventory State]");
        inventory.displayInventory();
    }

    private static void processReservation(Reservation reservation, RoomInventory inventory, BookingValidator validator) {
        try {
            System.out.println("\n[Processing] " + reservation);
            validator.validateReservationInput(reservation);
            validator.validateInventoryForBooking(inventory, reservation.getRoomType());
            inventory.decrementRoom(reservation.getRoomType());
            System.out.println("[Success] Reservation " + reservation.getReservationId() + " confirmed for " + reservation.getGuestName());

        } catch (InvalidBookingException ex) {
            System.out.println("[Error] " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("[Unexpected Error] " + ex.getMessage());
        }
    }
}
