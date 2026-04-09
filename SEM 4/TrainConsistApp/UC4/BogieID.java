public class BogieID {
    private String id;
    private String type;
    
    public BogieID(String id, String type) {
        this.id = id;
        this.type = type;
    }
    
    public String getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return id + " (" + type + ")";
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
