import java.util.ArrayList;
import java.util.List;

public class TrainApp {
    private List<GoodsBogie> goodsBogies;

    public TrainApp() {
        this.goodsBogies = new ArrayList<>();
    }

    public void addGoodsBogie(GoodsBogie bogie) {
        goodsBogies.add(bogie);
    }

    public void displayBogies(String title) {
        System.out.println("\n" + title);
        System.out.println("ID       | Shape        | Cargo        | Capacity");
        System.out.println("----------------------------------------------------");
        for (GoodsBogie bogie : goodsBogies) {
            System.out.println(bogie);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Total bogies: " + goodsBogies.size() + "\n");
    }

    public void assignCargoToBogie(GoodsBogie bogie, String cargo) {
        try {
            System.out.println("Attempting to assign cargo '" + cargo + "' to bogie " + bogie.getId() + "...");
            bogie.assignCargo(cargo);
            System.out.println("✓ Cargo assigned successfully to " + bogie.getId());
        } catch (CargoSafetyException ex) {
            System.out.println("✗ Cargo safety exception: " + ex.getMessage());
        } finally {
            System.out.println("[LOG] Cargo assignment attempt completed for bogie " + bogie.getId() + "\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");
        System.out.println("UC15: Safe Cargo Assignment Using try-catch-finally\n");

        TrainApp train = new TrainApp();
        GoodsBogie cylindricalBogie = new GoodsBogie("GB101", "Cylindrical", 40000);
        GoodsBogie rectangularBogie = new GoodsBogie("GB102", "Rectangular", 30000);

        train.addGoodsBogie(cylindricalBogie);
        train.addGoodsBogie(rectangularBogie);

        train.displayBogies("Initial Goods Bogies");

        train.assignCargoToBogie(cylindricalBogie, "Petroleum");
        train.assignCargoToBogie(rectangularBogie, "Coal");
        train.assignCargoToBogie(rectangularBogie, "Petroleum");

        train.displayBogies("Final Goods Bogies After Cargo Assignments");

        System.out.println("Program continues safely after handling runtime cargo assignment errors...");
    }
}
