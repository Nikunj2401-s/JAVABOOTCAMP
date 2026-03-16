class Cart {
    private double total = 0;

    public void addItem(double price) {
        total += price;
        System.out.println("Item added. Cart total: ₹" + total);
    }

    public double getTotal() {
        return total;
    }
}

class PaymentService {
    public boolean makePayment(double amount) {
        System.out.println("Payment of ₹" + amount + " successful.");
        return true;
    }
}

class OrderService {
    PaymentService payment = new PaymentService();

    public void checkout(Cart cart) {
        double total = cart.getTotal();
        System.out.println("Processing order...");
        if (payment.makePayment(total)) {
            System.out.println("Order confirmed and dispatched!");
        }
    }
}

public class OnlineShoppingDemo {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.addItem(500);
        cart.addItem(1000);

        OrderService order = new OrderService();
        order.checkout(cart);
    }
}
