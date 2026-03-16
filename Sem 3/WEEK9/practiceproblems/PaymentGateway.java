package WEEK9.practiceproblems;

// PaymentGateway.java

// Base class
abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void processPayment();

    @Override
    public String toString() {
        return "Payment [Amount: $" + amount + "]";
    }
}

// Subclass 1: Credit Card Payment
class CreditCardPayment extends Payment {
    private String cardNumber;

    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment of $" + amount + " using card: " + cardNumber);
    }

    @Override
    public String toString() {
        return "CreditCardPayment [Amount: $" + amount + ", Card: " + cardNumber + "]";
    }
}

// Subclass 2: UPI Payment
class UpiPayment extends Payment {
    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI payment of $" + amount + " via: " + upiId);
    }

    @Override
    public String toString() {
        return "UpiPayment [Amount: $" + amount + ", UPI ID: " + upiId + "]";
    }
}

// Subclass 3: PayPal Payment
class PayPalPayment extends Payment {
    private String email;

    public PayPalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment of $" + amount + " from: " + email);
    }

    @Override
    public String toString() {
        return "PayPalPayment [Amount: $" + amount + ", Email: " + email + "]";
    }
}

// Main class
public class PaymentGateway {
    public static void main(String[] args) {
        Payment p1 = new CreditCardPayment(500.0, "1234-5678-9876-5432");
        Payment p2 = new UpiPayment(250.0, "user@upi");
        Payment p3 = new PayPalPayment(1000.0, "user@gmail.com");

        // Array of payments
        Payment[] payments = { p1, p2, p3 };

        System.out.println("=== Payment Gateway System ===");
        for (Payment payment : payments) {
            // Identify runtime class using getClass()
            System.out.println("Payment Type: " + payment.getClass().getSimpleName());
            System.out.println("Full Class Name: " + payment.getClass().getName());
            payment.processPayment();
            System.out.println();
        }
    }
}
