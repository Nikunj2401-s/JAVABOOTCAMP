import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String,Integer> inventory = new HashMap<>();

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailableRooms(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, getAvailableRooms(roomType) - 1);
    }

    public void incrementRoom(String roomType) {
        inventory.put(roomType, getAvailableRooms(roomType) + 1);
    }

    public Map<String,Integer> getInventoryMap() {
        return inventory;
    }

    public void display() {
        System.out.println("\n--- Inventory ---");
        inventory.forEach((r,c)-> System.out.println(r + ": " + c));
        System.out.println("-----------------");
    }
}
