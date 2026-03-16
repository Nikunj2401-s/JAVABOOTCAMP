package javabootcamp.WEEK3.Labproblems;


class BankAccount {
    int accountNumber;
    String accountHolder;
    double balance;

    BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New Balance: " + balance);
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    void displayAccount() {
        System.out.println("Account No: " + accountNumber +
                           ", Holder: " + accountHolder +
                           ", Balance: " + balance);
    }
}

class Bank {
    private BankAccount[] accounts;
    private int count;

    Bank(int size) {
        accounts = new BankAccount[size];
        count = 0;
    }

    void addAccount(BankAccount acc) {
        if (count < accounts.length) {
            accounts[count++] = acc;
            System.out.println("Account created for " + acc.accountHolder);
        } else {
            System.out.println("Bank is full, cannot add more accounts.");
        }
    }

    BankAccount findAccount(int accNumber) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    void displayAllAccounts() {
        for (int i = 0; i < count; i++) {
            accounts[i].displayAccount();
        }
    }
}

public class BankSystemDemo {
    public static void main(String[] args) {
        Bank bank = new Bank(5); // capacity for 5 accounts

        BankAccount a1 = new BankAccount(101, "Alice", 1000);
        BankAccount a2 = new BankAccount(102, "Bob", 2000);

        bank.addAccount(a1);
        bank.addAccount(a2);

        System.out.println("\n--- All Accounts ---");
        bank.displayAllAccounts();

        System.out.println("\n--- Transactions ---");
        BankAccount acc = bank.findAccount(101);
        if (acc != null) {
            acc.deposit(500);
            acc.withdraw(300);
        }

        System.out.println("\n--- Updated Accounts ---");
        bank.displayAllAccounts();
    }
}

