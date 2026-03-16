package WEEK4.assignmentproblems;

class BankAccount {
    String name;
    double balance;

    BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amt) {
        balance += amt;
        System.out.println("Deposited: " + amt);
    }

    void withdraw(double amt) {
        if (amt <= balance) {
            balance -= amt;
            System.out.println("Withdrawn: " + amt);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    void showBalance() {
        System.out.println("Account: " + name + " | Balance: " + balance);
    }
}

public class BankApp {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Rohan", 1000);
        acc.deposit(500);
        acc.withdraw(300);
        acc.withdraw(1500);
        acc.showBalance();
    }
}
