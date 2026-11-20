/*
 * Exercice 12 - Écrivez le programme vous-même
 *
 * CONSIGNE
 * --------
 * Un client achète les articles suivants :
 *
 *     double[] prices    = {12.50, 2.25, 45.00};
 *     int[]    quantities = {2, 4, 2};
 *
 * Écrivez un programme de panier d'achat qui :
 *   1. Calcule le sous-total : la somme de prix * quantité pour chaque article.
 *   2. Applique une réduction de 10 % si le sous-total est supérieur à 100,00,
 *      sinon la réduction vaut 0.
 *   3. Calcule le total : sous-total - réduction.
 *   4. Affiche le sous-total, la réduction et le total,
 *      chacun avec exactement deux décimales.
 *
 * Indice : utilisez System.out.printf("Subtotal: %.2f%n", subtotal);
 *
 * Compilez avec : javac ShoppingCart.java
 * Exécutez avec : java ShoppingCart
 *
 * SORTIE ATTENDUE
 * ---------------
 * Subtotal: 124.00
 * Discount: 12.40
 * Total: 111.60
 */
public class ShoppingCart {
    public static void main(String[] args) {
        double[] prices = {12.50, 2.25, 45.00};
        int[] quantities = {2, 4, 2};

        // TODO 1 : calculez le sous-total avec une boucle

        // TODO 2 : calculez la réduction (10 % seulement si le sous-total > 100)

        // TODO 3 : calculez le total

        // TODO 4 : affichez le sous-total, la réduction et le total avec %.2f
    }
}
