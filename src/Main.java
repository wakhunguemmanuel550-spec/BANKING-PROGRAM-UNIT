import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Map<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== BANK PROGRAM ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewAccounts();
                    break;
                case 0:
                    System.out.println("Thank you for using the bank program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = input.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String holderName = input.nextLine();

        BankAccount account = new BankAccount(accountNumber, holderName);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully.");
    }

    private static void depositMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = input.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        account.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + account.getBalance());
    }

    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = input.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = input.nextDouble();
        input.nextLine();

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Remaining balance: $" + account.getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = input.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Account Holder: " + account.getHolderName());
        System.out.println("Balance: $" + account.getBalance());
    }

    private static void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        System.out.println("\nList of Accounts:");
        for (BankAccount account : accounts.values()) {
            System.out.println(account);
        }
    }

    static class BankAccount {
        private final String accountNumber;
        private final String holderName;
        private double balance;

        public BankAccount(String accountNumber, String holderName) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = 0.0;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount > balance) {
                return false;
            }
            balance -= amount;
            return true;
        }

        public double getBalance() {
            return balance;
        }

        public String getHolderName() {
            return holderName;
        }

        @Override
        public String toString() {
            return "Account Number: " + accountNumber + ", Holder: " + holderName + ", Balance: $" + balance;
        }
    }
}
