public class Bogie {
    private String name;
    private int capacity;
    private String type;
    
    public Bogie(String name, int capacity, String type) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public String getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.format("%-20s | Capacity: %3d | Type: %-15s", name, capacity, type);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bogie bogie = (Bogie) obj;
        return name.equals(bogie.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
