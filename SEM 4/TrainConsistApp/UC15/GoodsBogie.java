public class GoodsBogie {
    private String id;
    private String shape;
    private String cargo;
    private int capacity;

    public GoodsBogie(String id, String shape, int capacity) {
        this.id = id;
        this.shape = shape;
        this.capacity = capacity;
        this.cargo = "Unassigned";
    }

    public String getId() {
        return id;
    }

    public String getShape() {
        return shape;
    }

    public String getCargo() {
        return cargo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void assignCargo(String cargo) {
        if (shape.equalsIgnoreCase("Rectangular") && cargo.equalsIgnoreCase("Petroleum")) {
            throw new CargoSafetyException("Unsafe cargo assignment: Rectangular bogie cannot carry Petroleum");
        }
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-12s | %-12s | Capacity: %d", id, shape, cargo, capacity);
    }
}
