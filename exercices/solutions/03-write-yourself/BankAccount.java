/*
 * Exercice 15 - Corrigé (version formateur)
 *
 * La classe garde le nom du titulaire et le solde privés, et expose deposit,
 * withdraw et printBalance. Les deux opérations refusent les montants
 * invalides et laissent alors le solde inchangé.
 *
 * Sortie :
 *   Deposited: 50.00
 *   Withdrew: 30.00
 *   Withdrawal of 500.00 rejected: insufficient funds.
 *   Alice's balance: 120.00
 */
public class BankAccount {
    private String accountHolder;
    private double balance;

    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            System.out.printf("Deposit of %.2f rejected: invalid amount.%n", amount);
            return;
        }
        balance = balance + amount;
        System.out.printf("Deposited: %.2f%n", amount);
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.printf("Withdrawal of %.2f rejected: invalid amount.%n", amount);
            return;
        }
        if (amount > balance) {
            System.out.printf("Withdrawal of %.2f rejected: insufficient funds.%n", amount);
            return;
        }
        balance = balance - amount;
        System.out.printf("Withdrew: %.2f%n", amount);
    }

    void printBalance() {
        System.out.printf("%s's balance: %.2f%n", accountHolder, balance);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Alice", 100.00);

        account.deposit(50.00);
        account.withdraw(30.00);
        account.withdraw(500.00);

        account.printBalance();
    }
}
