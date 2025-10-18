package WEEK8.practiceproblems;

// Abstract class
abstract class BankAccount {
    double balance;

    BankAccount(double balance) {
        this.balance = balance;
    }

    // Abstract method
    abstract void calculateInterest();
}

// Subclass SavingsAccount extending BankAccount
class SavingsAccount extends BankAccount {
    double interestRate = 0.05; // 5% interest

    SavingsAccount(double balance) {
        super(balance);
    }

    void calculateInterest() {
        double interest = balance * interestRate;
        System.out.println("Savings Account Interest: " + interest);
    }
}

// Subclass CurrentAccount extending BankAccount
class CurrentAccount extends BankAccount {
    double interestRate = 0.02; // 2% interest

    CurrentAccount(double balance) {
        super(balance);
    }

    void calculateInterest() {
        double interest = balance * interestRate;
        System.out.println("Current Account Interest: " + interest);
    }
}

// Main class to demonstrate abstraction
public class BankAb{
    public static void main(String[] args) {
        // Using abstract class references
        BankAccount acc1 = new SavingsAccount(10000);
        BankAccount acc2 = new CurrentAccount(15000);

        acc1.calculateInterest();
        acc2.calculateInterest();
    }
}

