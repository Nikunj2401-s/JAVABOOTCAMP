public class PassengerBogie {
    private String bogieType;
    private int seatCapacity;
    
    public PassengerBogie(String bogieType, int seatCapacity) {
        this.bogieType = bogieType;
        this.seatCapacity = seatCapacity;
    }
    
    public String getBogieType() {
        return bogieType;
    }
    
    public int getSeatCapacity() {
        return seatCapacity;
    }
    
    @Override
    public String toString() {
        return bogieType + " (Capacity: " + seatCapacity + " seats)";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PassengerBogie that = (PassengerBogie) obj;
        return bogieType.equals(that.bogieType);
    }
}
