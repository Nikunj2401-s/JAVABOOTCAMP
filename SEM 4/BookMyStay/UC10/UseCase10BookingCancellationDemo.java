public class UseCase10BookingCancellationDemo {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v10.0");
        System.out.println(" Booking Cancellation & Rollback Demo");
        System.out.println("======================================\n");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 2);
        inventory.addRoomType("Double Room", 2);
        inventory.addRoomType("Suite Room", 1);

        BookingHistory history = new BookingHistory();
        CancellationService cancellationService = new CancellationService(inventory, history);

        Reservation r1 = new Reservation("R-201", "Alice", "Suite Room", "SU2001");
        Reservation r2 = new Reservation("R-202", "Bob", "Single Room", "SR2002");
        Reservation r3 = new Reservation("R-203", "Charlie", "Double Room", "DR2003");

        System.out.println("[Setup] Confirming bookings and registering currently active reservations...");
        cancellationService.registerConfirmed(r1);
        cancellationService.registerConfirmed(r2);
        cancellationService.registerConfirmed(r3);

        inventory.displayInventory();
        history.display();

        System.out.println("\n[Action] Cancel R-202 and R-203, then invalid cancel attempt for R-999...");
        cancellationService.cancelReservation("R-202");
        cancellationService.cancelReservation("R-203");
        cancellationService.cancelReservation("R-999");

        inventory.displayInventory();
        history.display();

        cancellationService.displayRollbackStack();

        System.out.println("\n[Result] Cancellation and inventory rollback succeed, invalid cancellation safely rejected.");
    }
}
