import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersistenceService {
    private final File file;

    public PersistenceService(String filePath) {
        this.file = new File(filePath);
    }

    public void save(State state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(state);
            System.out.println("[Persistence] State saved to " + file.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("[Persistence] Save failed: " + e.getMessage());
        }
    }

    public State load() {
        if (!file.exists()) {
            System.out.println("[Persistence] No persistence file found; starting with fresh state.");
            return new State();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object object = in.readObject();
            if (object instanceof State) {
                System.out.println("[Persistence] State loaded from " + file.getAbsolutePath());
                return (State) object;
            } else {
                System.out.println("[Persistence] Invalid data format; starting fresh.");
                return new State();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[Persistence] Load failed: " + e.getMessage());
            return new State();
        }
    }

    public static class State implements Serializable {
        private static final long serialVersionUID = 1L;

        public RoomInventory inventory = new RoomInventory();
        public BookingHistory history = new BookingHistory();
    }
}
