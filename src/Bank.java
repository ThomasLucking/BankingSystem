import java.util.ArrayList;

interface Printable {
    void printDetails();
}

abstract class BankAccount {
    String owner;
    double balance;
    ArrayList<String> transactionHistory = new ArrayList<>();

    BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
        transactionHistory.add("Account opened with balance: " + balance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
        System.out.println(owner + " deposited " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrawn: " + amount);
        System.out.println(owner + " withdrew " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public void printHistory() {
        System.out.println("--- Transaction History for " + owner + " ---");
        for (String t : transactionHistory) {
            System.out.println("  " + t);
        }
    }

    @Override
    public String toString() {
        return "Owner: " + owner + " | Balance: " + balance;
    }
}

class SavingsAccount extends BankAccount implements Printable {
    double interestRate;

    SavingsAccount(String owner, double balance, double interestRate) {
        super(owner, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (balance - amount < 100) {
            System.out.println("Blocked! Savings account must keep minimum balance of 100.");
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrawn: " + amount);
        System.out.println(owner + " withdrew " + amount);
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        transactionHistory.add("Interest applied: +" + interest);
        System.out.println("Interest applied: +" + interest);
    }

    @Override
    public void printDetails() {
        System.out.println("=== Savings Account ===");
        System.out.println(toString());
        System.out.println("Interest rate: " + (interestRate * 100) + "%");
    }
}

class BusinessAccount extends BankAccount implements Printable {
    double creditLimit;

    BusinessAccount(String owner, double balance, double creditLimit) {
        super(owner, balance);
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (balance - amount < -creditLimit) {
            System.out.println("Blocked! Credit limit of " + creditLimit + " exceeded.");
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrawn: " + amount);
        System.out.println(owner + " withdrew " + amount);
    }

    public double getCreditUsed() {
        return balance < 0 ? -balance : 0;
    }

    @Override
    public void printDetails() {
        System.out.println("=== Business Account ===");
        System.out.println(toString());
        System.out.println("Credit limit: " + creditLimit);
        System.out.println("Credit used: " + getCreditUsed());
    }
}

