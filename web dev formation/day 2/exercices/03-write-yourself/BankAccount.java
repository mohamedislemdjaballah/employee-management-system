/*
 * Exercice 15 - Écrivez le programme vous-même
 *
 * CONSIGNE
 * --------
 * Écrivez un programme de compte bancaire simple dans ce fichier.
 *
 * La classe BankAccount doit contenir :
 *   - un champ  String accountHolder
 *   - un champ  double balance
 *   - un constructeur qui reçoit le nom du titulaire et le solde initial
 *   - une méthode deposit(double amount)
 *       * ajoute le montant au solde
 *       * ignore les montants nuls ou négatifs
 *   - une méthode withdraw(double amount)
 *       * retire le montant du solde
 *       * refuse l'opération si le montant est nul ou négatif,
 *         ou s'il est supérieur au solde
 *       * affiche un message lorsque le retrait est refusé
 *   - une méthode printBalance() qui affiche le titulaire et le solde
 *     avec deux décimales
 *
 * Ensuite, dans main() :
 *   1. Créez un compte pour "Alice" avec un solde initial de 100,00.
 *   2. Déposez 50,00, puis retirez 30,00.
 *   3. Essayez de retirer 500,00 (l'opération doit être refusée).
 *   4. Affichez le solde final.
 *
 * Indice : utilisez System.out.printf("%.2f%n", balance);
 *
 * Compilez avec : javac BankAccount.java
 * Exécutez avec : java BankAccount
 *
 * SORTIE ATTENDUE (le message de refus peut être formulé autrement)
 * ----------------------------------------------------------------
 * Deposited: 50.00
 * Withdrew: 30.00
 * Withdrawal of 500.00 rejected: insufficient funds.
 * Alice's balance: 120.00
 */
public class BankAccount {
    // TODO 1 : déclarez les champs accountHolder et balance

    // TODO 2 : écrivez le constructeur

    // TODO 3 : écrivez deposit(double amount)

    // TODO 4 : écrivez withdraw(double amount)

    // TODO 5 : écrivez printBalance()

    public static void main(String[] args) {
        // TODO 6 : créez le compte et effectuez les opérations décrites ci-dessus
    }
}
