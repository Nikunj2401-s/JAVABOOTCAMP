public class BogieID {
    private String id;
    private String type;
    private int capacity;
    
    public BogieID(String id, String type, int capacity) {
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
        return id + " (" + type + ", Capacity: " + capacity + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BogieID bogieID = (BogieID) obj;
        return id.equals(bogieID.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
