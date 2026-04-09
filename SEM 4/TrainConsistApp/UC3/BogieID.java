public class BogieID {
    private String id;
    
    public BogieID(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return id;
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
