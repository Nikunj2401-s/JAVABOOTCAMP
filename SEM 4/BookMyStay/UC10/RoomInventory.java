import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        if (count < 0) throw new IllegalArgumentException("Count must be >= 0");
        inventory.put(roomType, count);
    }

    public int getAvailableRooms(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        if (!inventory.containsKey(roomType)) {
            throw new IllegalArgumentException("Room type not found: " + roomType);
        }
        int available = inventory.get(roomType);
        if (available <= 0) {
            throw new IllegalStateException("No inventory left for " + roomType);
        }
        inventory.put(roomType, available - 1);
    }

    public void incrementRoom(String roomType) {
        if (!inventory.containsKey(roomType)) {
            throw new IllegalArgumentException("Room type not found: " + roomType);
        }
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public void displayInventory() {
        System.out.println("\n--- Room Inventory State ---");
        inventory.forEach((k,v)-> System.out.println(k + ": " + v));
        System.out.println("-----------------------------");
    }
}
