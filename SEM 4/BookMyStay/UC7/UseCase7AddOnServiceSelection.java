import java.util.HashMap;
import java.util.Map;

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("      Welcome to Book My Stay");
        System.out.println("    Hotel Booking System v7.0");
        System.out.println(" Add-On Service Selection Demo");
        System.out.println("======================================\n");

        // Simulated confirmed reservations (from UC6 or similar)
        Map<String, Reservation> confirmedReservations = new HashMap<>();
        confirmedReservations.put("R-1001", new Reservation("R-1001", "Alice", "Suite Room", "SU1001"));
        confirmedReservations.put("R-1002", new Reservation("R-1002", "Bob", "Single Room", "SR1002"));
        confirmedReservations.put("R-1003", new Reservation("R-1003", "Charlie", "Double Room", "DR1003"));

        // Add-On Service manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        System.out.println("[System] Adding add-on services to existing reservations...");
        serviceManager.addService("R-1001", new AddOnService("Airport Pickup", 30.00));
        serviceManager.addService("R-1001", new AddOnService("Breakfast Buffet", 15.00));
        serviceManager.addService("R-1002", new AddOnService("Gym Access", 10.00));
        serviceManager.addService("R-1003", new AddOnService("Extra Bed", 25.00));
        serviceManager.addService("R-1003", new AddOnService("Late Checkout", 20.00));

        // Display reservation + add-on mapping
        for (String reservationId : confirmedReservations.keySet()) {
            System.out.println("\n" + confirmedReservations.get(reservationId));
            serviceManager.displayReservationServices(reservationId);
        }

        // Summarize that core booking/inventory state remains unchanged
        System.out.println("\n[Result] Core room inventory and allocation state unaffected by add-on selection.");
        System.out.println("See separate add-on manager mapping for enhancements.");
    }
}
