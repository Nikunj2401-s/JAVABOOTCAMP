public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v6.1");
        System.out.println("   Room Allocation & Reservations");
        System.out.println("======================================");

        // Initialize systems
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService(inventory);

        // Setup room inventory
        System.out.println("\n[System] Initializing room inventory...");
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 4);
        inventory.addRoomType("Suite Room", 3);
        inventory.displayInventory();

        // Simulate incoming booking requests
        System.out.println("\n[System] Receiving incoming booking requests during peak hours...");
        queue.addRequest(new Reservation("Alice", "Suite Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Double Room"));
        queue.addRequest(new Reservation("Diana", "Suite Room"));
        queue.addRequest(new Reservation("Eve", "Single Room"));

        // Display queue status
        queue.displayQueueStatus();

        // Process bookings with room allocation
        System.out.println("\n[System] Processing booking requests with room allocation...\n");
        
        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            boolean success = allocationService.allocateRoom(request);
            
            if (!success) {
                System.out.println("[Booking Failed] No available rooms for " + request.getRoomType());
            }
        }

        // Display final states
        System.out.println("\n--- Final System State ---");
        inventory.displayInventory();
        allocationService.displayAllocatedRooms();
    }
}
