public class Bogie {
    private String bogieType;
    private int bogieId;
    
    public Bogie(String bogieType, int bogieId) {
        this.bogieType = bogieType;
        this.bogieId = bogieId;
    }
    
    public String getBogieType() {
        return bogieType;
    }
    
    public int getBogieId() {
        return bogieId;
    }
    
    @Override
    public String toString() {
        return "Bogie{" +
                "bogieType='" + bogieType + '\'' +
                ", bogieId=" + bogieId +
                '}';
    }
}
