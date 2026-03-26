


public void main() {
    SavingsAccount savings = new SavingsAccount("Alice", 500, 0.05);
    BusinessAccount business = new BusinessAccount("Bob", 1000, 500);

    savings.deposit(200);
    business.deposit(300);

    savings.withdraw(100);
    business.withdraw(800);

    savings.withdraw(550);

    business.withdraw(900);

    savings.applyInterest();

    System.out.println("\n--- All Accounts ---");
    BankAccount[] accounts = {savings, business};
    for (BankAccount acc : accounts) {
        System.out.println(acc);
    }

    System.out.println("\n--- Details ---");
    Printable[] printables = {savings, business};
    for (Printable p : printables) {
        p.printDetails();
    }

    System.out.println();
    savings.printHistory();
    System.out.println();
    business.printHistory();
}
