package WEEK7.assignmentproblems;

class HotelBooking {
    // Standard booking
    void calculatePrice(String roomType, int nights) {
        double rate = getRate(roomType);
        double cost = rate * nights;
        System.out.println("Standard Booking → Room: " + roomType + ", Nights: " + nights + ", Total: ₹" + cost);
    }

    // Seasonal booking
    void calculatePrice(String roomType, int nights, double seasonalMultiplier) {
        double rate = getRate(roomType);
        double cost = rate * nights * seasonalMultiplier;
        System.out.println("Seasonal Booking → Room: " + roomType + ", Nights: " + nights +
                ", Multiplier: " + seasonalMultiplier + ", Total: ₹" + cost);
    }

    // Corporate booking
    void calculatePrice(String roomType, int nights, double discount, boolean mealPackage) {
        double rate = getRate(roomType);
        double base = rate * nights;
        double discounted = base - (base * discount / 100);
        if(mealPackage) discounted += 500; // flat meal package
        System.out.println("Corporate Booking → Room: " + roomType + ", Nights: " + nights +
                ", Discount: " + discount + "%, Meals: " + (mealPackage?"Yes":"No") +
                ", Total: ₹" + discounted);
    }

    // Wedding package
    void calculatePrice(String roomType, int nights, int guests, double decorationFee, double cateringFee) {
        double rate = getRate(roomType);
        double base = rate * nights;
        double total = base + decorationFee + (guests * cateringFee);
        System.out.println("Wedding Package → Room: " + roomType + ", Nights: " + nights +
                ", Guests: " + guests + ", Decoration: ₹" + decorationFee +
                ", Catering: ₹" + (guests*cateringFee) + ", Total: ₹" + total);
    }

    private double getRate(String roomType) {
        return switch(roomType.toLowerCase()) {
            case "suite" -> 5000;
            case "deluxe" -> 3000;
            default -> 1500;
        };
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        HotelBooking h = new HotelBooking();
        h.calculatePrice("Deluxe", 3);
        h.calculatePrice("Suite", 2, 1.5);
        h.calculatePrice("Standard", 5, 10, true);
        h.calculatePrice("Suite", 2, 100, 2000, 800);
    }
}

