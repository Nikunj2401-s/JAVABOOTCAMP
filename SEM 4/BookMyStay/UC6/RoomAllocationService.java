import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomAllocationService {
    private RoomInventory inventory;
    private Map<String, Set<String>> roomTypeToAllocatedIds;  // Maps room type to allocated room IDs
    private int roomIdCounter;

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        this.roomTypeToAllocatedIds = new HashMap<>();
        this.roomIdCounter = 1000;
    }

    /**
     * Allocates a room to a guest, ensuring no double-booking
     */
    public boolean allocateRoom(Reservation reservation) {
        String roomType = reservation.getRoomType();
        String guestName = reservation.getGuestName();

        // Check if rooms are available
        int availableRooms = inventory.getAvailableRooms(roomType);
        if (availableRooms <= 0) {
            System.out.println("[Allocation Failed] No available " + roomType + " for " + guestName);
            return false;
        }

        // Generate unique room ID
        String roomId = generateUniqueRoomId(roomType);

        // Check if this room ID is already allocated (double-booking prevention)
        if (isRoomAllocated(roomType, roomId)) {
            System.out.println("[Allocation Failed] Room ID conflict for " + guestName);
            return false;
        }

        // Allocate room (atomic operation)
        allocateRoomId(roomType, roomId);
        
        // Update inventory immediately
        inventory.updateRoomAvailability(roomType, availableRooms - 1);

        // Confirm reservation
        System.out.println("[Booking Confirmed] " + guestName + " -> Room ID: " + roomId 
                         + " (" + roomType + ")");
        return true;
    }

    /**
     * Generates a unique room ID for a given room type
     */
    private String generateUniqueRoomId(String roomType) {
        roomIdCounter++;
        String prefix = getShortPrefix(roomType);
        return prefix + roomIdCounter;
    }

    /**
     * Gets a short prefix based on room type
     */
    private String getShortPrefix(String roomType) {
        if (roomType.contains("Single")) return "SR";
        if (roomType.contains("Double")) return "DR";
        if (roomType.contains("Suite")) return "SU";
        return "RM";
    }

    /**
     * Checks if a room ID is already allocated (prevents reuse)
     */
    private boolean isRoomAllocated(String roomType, String roomId) {
        Set<String> allocatedIds = roomTypeToAllocatedIds.getOrDefault(roomType, new HashSet<>());
        return allocatedIds.contains(roomId);
    }

    /**
     * Records a room ID as allocated
     */
    private void allocateRoomId(String roomType, String roomId) {
        roomTypeToAllocatedIds.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);
    }

    /**
     * Displays all allocated room IDs grouped by room type
     */
    public void displayAllocatedRooms() {
        System.out.println("\n--- Allocated Room IDs ---");
        
        if (roomTypeToAllocatedIds.isEmpty()) {
            System.out.println("No rooms allocated yet.");
        } else {
            for (Map.Entry<String, Set<String>> entry : roomTypeToAllocatedIds.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println("---------------------------");
    }

    /**
     * Returns total number of allocated rooms
     */
    public int getTotalAllocatedRooms() {
        int total = 0;
        for (Set<String> roomIds : roomTypeToAllocatedIds.values()) {
            total += roomIds.size();
        }
        return total;
    }
}
