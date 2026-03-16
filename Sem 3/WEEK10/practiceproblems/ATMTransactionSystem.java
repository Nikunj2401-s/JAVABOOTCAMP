package WEEK10.practiceproblems;

// ---------------------------
// BankAccount Class
// ---------------------------
class BankAccount {
    private String accountNumber;
    private int pin;
    private double balance;

    public BankAccount(String accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePIN(int enteredPIN) {
        System.out.println("BankAccount: Validating PIN...");
        return this.pin == enteredPIN;
    }

    public boolean debit(double amount) {
        System.out.println("BankAccount: Processing debit of ₹" + amount);
        if (balance >= amount) {
            balance -= amount;
            System.out.println("BankAccount: Debit successful. Remaining balance: ₹" + balance);
            return true;
        } else {
            System.out.println("BankAccount: Insufficient funds!");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}

// ---------------------------
// ATM Class
// ---------------------------
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void insertCard() {
        System.out.println("\nATM: Card inserted.");
    }

    public void enterPIN(int pin) {
        System.out.println("ATM: PIN entered by customer.");
        boolean isValid = account.validatePIN(pin);
        if (isValid) {
            System.out.println("ATM: PIN validated successfully.");
        } else {
            System.out.println("ATM: Invalid PIN. Transaction terminated.");
        }
    }

    public void requestWithdrawal(double amount, int enteredPIN) {
        System.out.println("\nATM: Withdrawal request for ₹" + amount);
        if (account.validatePIN(enteredPIN)) {
            boolean success = account.debit(amount);
            if (success) {
                dispenseCash(amount);
                showConfirmation(true);
            } else {
                showConfirmation(false);
            }
        } else {
            System.out.println("ATM: PIN verification failed. Cannot proceed with withdrawal.");
        }
    }

    public void dispenseCash(double amount) {
        System.out.println("ATM: Dispensing ₹" + amount + " cash...");
    }

    public void showConfirmation(boolean success) {
        if (success)
            System.out.println("ATM: Transaction successful! Please take your cash and card.\n");
        else
            System.out.println("ATM: Transaction failed. Please check your account.\n");
    }
}

// ---------------------------
// Customer Class
// ---------------------------
class Customer {
    private String name;
    private int pin;
    private ATM atm;

    public Customer(String name, int pin, ATM atm) {
        this.name = name;
        this.pin = pin;
        this.atm = atm;
    }

    public void startTransaction(double amount) {
        System.out.println("Customer: Initiating ATM transaction...");
        atm.insertCard();
        atm.enterPIN(pin);
        atm.requestWithdrawal(amount, pin);
    }
}

// ---------------------------
// Main Class
// ---------------------------
public class ATMTransactionSystem {
    public static void main(String[] args) {

        // Create a Bank Account
        BankAccount account = new BankAccount("ACC123", 1234, 10000);

        // Create ATM linked to BankAccount
        ATM atm = new ATM(account);

        // Create Customer
        Customer customer = new Customer("Rahul", 1234, atm);

        // Start Transaction
        customer.startTransaction(3000);
    }
}
