    // VehicleRentalSystem.java
    class Vehicle {
        private String brand;
        private String model;
        private double dailyRate;

        // Constructor
        public Vehicle(String brand, String model, double dailyRate) {
            this.brand = brand;
            this.model = model;
            this.dailyRate = dailyRate;
        }

        // Getter
        public double getDailyRate() {
            return dailyRate;
        }

        // toString method
        @Override
        public String toString() {
            return "Vehicle [Brand: " + brand + ", Model: " + model + ", Daily Rate: $" + dailyRate + "]";
        }
    }

    // Subclass: Car
    class Car extends Vehicle {
        private int seats;
        private boolean hasAirConditioning;

        public Car(String brand, String model, double dailyRate, int seats, boolean hasAirConditioning) {
            super(brand, model, dailyRate);
            this.seats = seats;
            this.hasAirConditioning = hasAirConditioning;
        }

        @Override
        public String toString() {
            return "Car → " + super.toString() + 
                ", Seats: " + seats + 
                ", Air Conditioning: " + (hasAirConditioning ? "Yes" : "No");
        }
    }

    // Subclass: Bike
    class Bike extends Vehicle {
        private boolean isElectric;

        public Bike(String brand, String model, double dailyRate, boolean isElectric) {
            super(brand, model, dailyRate);
            this.isElectric = isElectric;
        }

        @Override
        public String toString() {
            return "Bike → " + super.toString() +
                ", Electric: " + (isElectric ? "Yes" : "No");
        }
    }

    // Rental class
    class Rental {
        private String customerName;
        private Vehicle vehicle;
        private int days;

        public Rental(String customerName, Vehicle vehicle, int days) {
            this.customerName = customerName;
            this.vehicle = vehicle;
            this.days = days;
        }

        public double calculateTotalCost() {
            return vehicle.getDailyRate() * days;
        }

        @Override
        public String toString() {
            return "Rental Details:\nCustomer: " + customerName + 
                "\n" + vehicle.toString() +
                "\nDays: " + days +
                "\nTotal Cost: $" + calculateTotalCost();
        }
    }

    // Main class
    public class VehicleRentalSystem {
        public static void main(String[] args) {
            Car car1 = new Car("Toyota", "Camry", 55.0, 5, true);
            Bike bike1 = new Bike("Yamaha", "FZ", 30.0, false);

            Rental rental1 = new Rental("Alice Johnson", car1, 3);
            Rental rental2 = new Rental("Bob Smith", bike1, 2);

            System.out.println(rental1);
            System.out.println("----------------------------");
            System.out.println(rental2);
        }
    }
