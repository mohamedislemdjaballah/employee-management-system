/*
 * Exercice 12 - Corrigé (version formateur)
 *
 * Le sous-total est la somme de prix * quantité pour chaque article. La
 * réduction de 10 % ne s'applique que si le sous-total est supérieur à 100,00.
 * Le total vaut sous-total - réduction. Tous les montants sont affichés avec
 * deux décimales.
 *
 * Sortie :
 *   Subtotal: 124.00
 *   Discount: 12.40
 *   Total: 111.60
 */
public class ShoppingCart {
    public static void main(String[] args) {
        double[] prices = {12.50, 2.25, 45.00};
        int[] quantities = {2, 4, 2};

        double subtotal = 0.0;
        for (int i = 0; i < prices.length; i++) {
            subtotal = subtotal + prices[i] * quantities[i];
        }

        double discount = 0.0;
        if (subtotal > 100.00) {
            discount = subtotal * 0.10;
        }

        double total = subtotal - discount;

        System.out.printf("Subtotal: %.2f%n", subtotal);
        System.out.printf("Discount: %.2f%n", discount);
        System.out.printf("Total: %.2f%n", total);
    }
}
