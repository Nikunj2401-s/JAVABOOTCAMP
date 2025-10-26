import java.util.*;

class Account {
    private int accNo;
    private double balance;

    public Account(int accNo, double balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + " to A/C " + accNo);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + " from A/C " + accNo);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void showBalance() {
        System.out.println("A/C " + accNo + " Balance: ₹" + balance);
    }
}

class Customer {
    private String name;
    private int id;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void openAccount(Account a) {
        accounts.add(a);
        System.out.println(name + " opened Account No. " + a);
    }

    public void showAccounts() {
        System.out.println(name + " has " + accounts.size() + " account(s).");
        for (Account a : accounts) {
            a.showBalance();
        }
    }
}

class Bank {
    private String name;
    private List<Customer> customers = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public void addCustomer(Customer c) {
        customers.add(c);
        System.out.println("Added Customer: " + c);
    }

    public void showCustomers() {
        System.out.println("\n--- " + name + " Bank Customers ---");
        for (Customer c : customers) {
            c.showAccounts();
        }
    }
}

public class BankSystemDemo {
    public static void main(String[] args) {
        Bank bank = new Bank("City Bank");

        Customer c1 = new Customer("Ravi", 101);
        Customer c2 = new Customer("Anita", 102);

        Account a1 = new Account(1111, 5000);
        Account a2 = new Account(2222, 8000);

        c1.openAccount(a1);
        c2.openAccount(a2);

        bank.addCustomer(c1);
        bank.addCustomer(c2);

        a1.deposit(2000);
        a1.withdraw(1000);
        a1.showBalance();

        bank.showCustomers();
    }
}
