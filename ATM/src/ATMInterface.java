import java.util.Scanner;

class User {
    String userId;
    int pin;

    public User(String userId, int pin) {
        this.userId = userId;
        this.pin = pin;
    }
}

class ATM {
    User currentUser;
    double balance;
    StringBuilder transactionHistory;

    public ATM(User user) {
        this.currentUser = user;
        this.balance = 1000; // Initial balance
        this.transactionHistory = new StringBuilder("Transaction History:\n");
    }

    public void displayMenu() {
        System.out.println("\nATM Interface Menu:");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    public void showTransactionHistory() {
        System.out.println(transactionHistory.toString());
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdraw: -$").append(amount).append("\n");
            System.out.println("Withdrawal successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.append("Deposit: +$").append(amount).append("\n");
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(double amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipient's user ID: ");
        String recipientUserId = scanner.next();
        System.out.print("Enter recipient's account number: ");
        // Assuming account number is entered here, you can modify this as per your requirement.
        String recipientAccountNumber = scanner.next();

        // Implement transfer logic based on your application requirements.

        transactionHistory.append("Transfer: -$").append(amount).append(" to ").append(recipientUserId).append("\n");
        System.out.println("Transfer successful. Remaining balance: $" + balance);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample user creation. In a real application, you would likely retrieve this information from a database.
        User user = new User("123456", 1234);

        System.out.print("Enter user ID: ");
        String enteredUserId = scanner.next();
        System.out.print("Enter user PIN: ");
        int enteredPin = scanner.nextInt();

        if (enteredUserId.equals(user.userId) && enteredPin == user.pin) {
            ATM atm = new ATM(user);

            int choice;
            do {
                atm.displayMenu();
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.showTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        atm.transfer(transferAmount);
                        break;
                    case 5:
                        System.out.println("Quitting ATM Interface. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } while (choice != 5);
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }
    }
}