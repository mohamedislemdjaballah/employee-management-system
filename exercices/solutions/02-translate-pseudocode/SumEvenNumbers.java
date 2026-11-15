/*
 * Exercice 6 - Corrigé (version formateur)
 *
 * Le pseudo-code utilise un accumulateur et une boucle for de 1 à n.
 * Chaque valeur paire est ajoutée au total.
 *
 * Sortie :
 *   sumEvenNumbers(6)  = 12  (expected 12)
 *   sumEvenNumbers(10) = 30 (expected 30)
 *   sumEvenNumbers(1)  = 0   (expected 0)
 */
public class SumEvenNumbers {
    public static void main(String[] args) {
        System.out.println("sumEvenNumbers(6)  = " + sumEvenNumbers(6) + "  (expected 12)");
        System.out.println("sumEvenNumbers(10) = " + sumEvenNumbers(10) + " (expected 30)");
        System.out.println("sumEvenNumbers(1)  = " + sumEvenNumbers(1) + "   (expected 0)");
    }

    static int sumEvenNumbers(int n) {
        int total = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                total = total + i;
            }
        }
        return total;
    }
}
