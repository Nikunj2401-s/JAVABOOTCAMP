import java.util.Scanner;

// -----------------------------
// ATM CLASS
// -----------------------------
class ATM {
    private double balance;

    // Constructor
    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    // Use Case 1: Check Balance
    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
    }

    // Use Case 2: Deposit Money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited ₹" + amount);
            printReceipt("Deposit", amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Use Case 3: Withdraw Money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew ₹" + amount);
            printReceipt("Withdrawal", amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Optional Use Case: Print Receipt
    private void printReceipt(String transactionType, double amount) {
        System.out.println("\n----- Transaction Receipt -----");
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: ₹" + amount);
        System.out.println("Remaining Balance: ₹" + balance);
        System.out.println("-------------------------------\n");
    }
}

// -----------------------------
// MAIN CLASS (User Interaction)
// -----------------------------
public class ATMSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM(5000.0); // Initial balance ₹5000

        System.out.println("=== Welcome to the ATM System ===");

        int choice;
        do {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = sc.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = sc.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
