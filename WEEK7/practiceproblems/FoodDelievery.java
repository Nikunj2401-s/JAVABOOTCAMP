package WEEK7.practiceproblems;

// Parent class
class DeliveryPartner {
    void deliver() {
        System.out.println("Delivery Partner delivers the order.");
    }
}

// Child class 1
class BikeDelivery extends DeliveryPartner {
    @Override
    void deliver() {
        System.out.println("Bike Delivery Partner delivers the order quickly by road.");
    }
}

// Child class 2
class DroneDelivery extends DeliveryPartner {
    @Override
    void deliver() {
        System.out.println("Drone Delivery Partner delivers the order through the air.");
    }
}

public class FoodDelievery {
    public static void main(String[] args) {
        DeliveryPartner dp;   // Reference of parent class

        dp = new DeliveryPartner();  
        dp.deliver();  // Calls parent method

        dp = new BikeDelivery();     
        dp.deliver();  // Calls BikeDelivery’s method

        dp = new DroneDelivery();    
        dp.deliver();  // Calls DroneDelivery’s method
    }
}
