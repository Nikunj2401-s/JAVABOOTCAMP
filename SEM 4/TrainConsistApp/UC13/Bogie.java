public class Bogie {
    private String id;
    private String type;
    private int capacity;

    public Bogie(String id, String type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-15s | Capacity: %d", id, type, capacity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bogie bogie = (Bogie) obj;
        return id.equals(bogie.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
