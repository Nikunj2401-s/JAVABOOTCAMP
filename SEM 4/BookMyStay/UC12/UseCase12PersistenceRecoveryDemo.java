public class UseCase12PersistenceRecoveryDemo {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v12.0");
        System.out.println("Persistence & Recovery Demo");
        System.out.println("======================================\n");

        String path = "d:\\javabootcamp\\SEM 4\\BookMyStay\\UC12\\persistence-state.bin";
        PersistenceService persistence = new PersistenceService(path);

        PersistenceService.State state = persistence.load();

        if (state.history.getConfirmed().isEmpty()) {
            System.out.println("[Session] No recovery data found. Initializing sample state...");
            state.inventory.addRoomType("Single Room", 2);
            state.inventory.addRoomType("Double Room", 1);
            state.inventory.addRoomType("Suite Room", 1);

            state.history.add(new Reservation("R-400", "Alice", "Single Room", "SR4001"));
            state.history.add(new Reservation("R-401", "Bob", "Double Room", "DR4002"));
            state.inventory.decrementRoom("Single Room");
            state.inventory.decrementRoom("Double Room");
            persistence.save(state);

        } else {
            System.out.println("[Session] Recovered state from persistence.");
        }

        System.out.println("\n--- Current recovered inventory and history ---");
        state.inventory.display();
        state.history.display();

        System.out.println("\n[Session] Simulating new booking and persisting again...");
        Reservation r2 = new Reservation("R-402", "Charlie", "Suite Room", "SU4003");
        state.history.add(r2);
        state.inventory.decrementRoom("Suite Room");
        persistence.save(state);

        System.out.println("\n[Result] Persistence cycle completed. State is recoverable on next restart.");
    }
}
