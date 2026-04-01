import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOnServiceManager {
    private Map<String, List<AddOnService>> servicesByReservationId;

    public AddOnServiceManager() {
        this.servicesByReservationId = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        servicesByReservationId
            .computeIfAbsent(reservationId, k -> new ArrayList<>())
            .add(service);
        System.out.println("[Service Added] " + service + " to Reservation " + reservationId);
    }

    public List<AddOnService> getServices(String reservationId) {
        return servicesByReservationId.getOrDefault(reservationId, new ArrayList<>());
    }

    public double calculateTotalCost(String reservationId) {
        double total = 0;
        for (AddOnService service : getServices(reservationId)) {
            total += service.getPrice();
        }
        return total;
    }

    public void displayReservationServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);
        if (services.isEmpty()) {
            System.out.println("[No Add-Ons] Reservation " + reservationId + " has no add-on services.");
            return;
        }

        System.out.println("\n--- Add-On Services for Reservation " + reservationId + " ---");
        services.forEach(s -> System.out.println(" - " + s));
        System.out.println("Total add-on cost: $" + String.format("%.2f", calculateTotalCost(reservationId)));
        System.out.println("------------------------------------------------");
    }
}
