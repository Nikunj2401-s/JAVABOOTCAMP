package WEEK8.practiceproblems;

// Interface declaration
interface PaymentGateway {
    void pay(double amount);
    void refund(double amount);
}

// Class implementing PaymentGateway for Credit Card payments
class CreditCardPayment implements PaymentGateway {
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card.");
    }

    public void refund(double amount) {
        System.out.println("Refunded ₹" + amount + " to Credit Card.");
    }
}

// Class implementing PaymentGateway for UPI payments
class UPIPayment implements PaymentGateway {
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI.");
    }

    public void refund(double amount) {
        System.out.println("Refunded ₹" + amount + " to UPI account.");
    }
}

// Main class to demonstrate multiple payment methods
public class PayGate {
    public static void main(String[] args) {
        // Using interface reference
        PaymentGateway payment1 = new CreditCardPayment();
        PaymentGateway payment2 = new UPIPayment();

        payment1.pay(1500);
        payment1.refund(500);

        payment2.pay(800);
        payment2.refund(200);
    }
}
