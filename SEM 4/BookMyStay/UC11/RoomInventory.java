import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private final Map<String, Integer> inventory = new HashMap<>();

    public synchronized void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public synchronized int getAvailableRooms(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public synchronized boolean allocateRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available <= 0) {
            return false;
        }
        inventory.put(roomType, available - 1);
        return true;
    }

    public synchronized void displayInventory() {
        System.out.println("\n--- Inventory snapshot ---");
        inventory.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("--------------------------");
    }
}
