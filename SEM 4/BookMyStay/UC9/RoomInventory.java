import java.util.HashMap;
import java.util.Map;

public class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String type, int count) {
        if (count < 0) {
            System.out.println("Cannot add negative room count.");
            return;
        }
        inventory.put(type, count);
    }

    public int getAvailableRooms(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrementRoom(String type) throws InvalidBookingException {
        if (!inventory.containsKey(type)) {
            throw new InvalidBookingException("Room type not recognized in inventory: " + type);
        }
        int available = inventory.get(type);
        if (available <= 0) {
            throw new InvalidBookingException("Cannot decrement room inventory below zero for type: " + type);
        }
        inventory.put(type, available - 1);
    }
    
    public void displayInventory() {
        System.out.println("\n--- Room Inventory Status ---");
        inventory.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("------------------------------");
    }
}
