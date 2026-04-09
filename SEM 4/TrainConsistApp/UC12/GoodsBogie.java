public class GoodsBogie {
    private String id;
    private String type;      // "Cylindrical", "Rectangular", "Open", "Box"
    private String cargo;     // "Petroleum", "Coal", "Grain", etc.
    private int loadCapacity;
    
    public GoodsBogie(String id, String type, String cargo, int loadCapacity) {
        this.id = id;
        this.type = type;
        this.cargo = cargo;
        this.loadCapacity = loadCapacity;
    }
    
    public String getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }
    
    public String getCargo() {
        return cargo;
    }
    
    public int getLoadCapacity() {
        return loadCapacity;
    }
    
    @Override
    public String toString() {
        return String.format("%-15s | Type: %-15s | Cargo: %-15s | Capacity: %d", 
                           id, type, cargo, loadCapacity);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GoodsBogie bogie = (GoodsBogie) obj;
        return id.equals(bogie.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
