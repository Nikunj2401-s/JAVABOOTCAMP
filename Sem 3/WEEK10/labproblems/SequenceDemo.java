class PaymentGateway {
    public boolean processPayment() {
        System.out.println("Payment processed successfully.");
        return true;
    }
}

class InventoryService {
    public boolean checkStock() {
        System.out.println("Stock available for the product.");
        return true;
    }
}

class OrderService {
    PaymentGateway payment = new PaymentGateway();
    InventoryService inventory = new InventoryService();

    public void placeOrder() {
        System.out.println("Order request received.");
        if (inventory.checkStock()) {
            if (payment.processPayment()) {
                System.out.println("Order confirmed and processed.");
            }
        }
    }
}

public class SequenceDemo {
    public static void main(String[] args) {
        OrderService order = new OrderService();
        order.placeOrder();
    }
}
