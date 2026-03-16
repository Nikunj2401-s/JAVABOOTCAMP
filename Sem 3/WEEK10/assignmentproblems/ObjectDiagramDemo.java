// Topic 2: Object Diagram - Runtime Instances
class Account {
    private int accNumber;
    private double balance;

    public Account(int accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public void displayAccountInfo() {
        System.out.println("Account No: " + accNumber + ", Balance: " + balance);
    }
}

class Customer {
    private String name;
    private Account account;

    public Customer(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public void displayCustomerInfo() {
        System.out.println("Customer Name: " + name);
        account.displayAccountInfo();
    }
}

class Bank {
    private String bankName;
    private Customer[] customers;

    public Bank(String bankName, Customer[] customers) {
        this.bankName = bankName;
        this.customers = customers;
    }

    public void displayBankDetails() {
        System.out.println("Bank: " + bankName);
        System.out.println("Customers:");
        for (Customer c : customers) {
            c.displayCustomerInfo();
            System.out.println("-------------------");
        }
    }
}

public class ObjectDiagramDemo {
    public static void main(String[] args) {
        // Create Account Objects
        Account acc1 = new Account(1001, 5000);
        Account acc2 = new Account(1002, 7000);

        // Create Customer Objects linked to Accounts
        Customer cust1 = new Customer("Aarav", acc1);
        Customer cust2 = new Customer("Diya", acc2);

        // Create Bank Object containing Customers
        Customer[] bankCustomers = { cust1, cust2 };
        Bank bank1 = new Bank("SBI Bank", bankCustomers);

        // Display runtime links
        bank1.displayBankDetails();
    }
}
