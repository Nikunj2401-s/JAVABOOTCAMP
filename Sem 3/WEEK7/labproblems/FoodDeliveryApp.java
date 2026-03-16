package WEEK7.labproblems;

class DeliveryCalculator {
    // Basic delivery (distance only)
    void calculateDelivery(double distance) {
        double cost = distance * 5;
        System.out.println("Basic Delivery: Distance " + distance + " km → Cost = ₹" + cost);
    }

    // Premium delivery (distance + priority fee)
    void calculateDelivery(double distance, double priorityFee) {
        double cost = (distance * 5) + priorityFee;
        System.out.println("Premium Delivery: Distance " + distance + " km + Priority Fee " + priorityFee + " → Cost = ₹" + cost);
    }

    // Group delivery (distance + number of orders discount)
    void calculateDelivery(double distance, int orders) {
        double cost = (distance * 5) - (orders * 2);
        System.out.println("Group Delivery: Distance " + distance + " km, Orders " + orders + " → Cost = ₹" + cost);
    }

    // Festival special (distance + discount + free delivery)
    void calculateDelivery(double distance, double discount, double orderAmount) {
        double cost = distance * 5;
        if (orderAmount > 500) {
            cost = 0;
            System.out.println("Festival Special: Free Delivery on order above ₹500!");
        } else {
            cost -= (cost * discount / 100);
            System.out.println("Festival Special: Distance " + distance + " km, Discount " + discount + "% → Cost = ₹" + cost);
        }
    }
}

public class FoodDeliveryApp {
    public static void main(String[] args) {
        DeliveryCalculator d = new DeliveryCalculator();
        d.calculateDelivery(10);
        d.calculateDelivery(10, 50);
        d.calculateDelivery(10, 3);
        d.calculateDelivery(10, 20, 400);
        d.calculateDelivery(10, 20, 600);
    }
}
