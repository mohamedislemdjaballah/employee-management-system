/*
 * Exercice 8 - Corrigé (version formateur)
 *
 * La première valeur sert de maximum initial, puis le reste du tableau est
 * parcouru et max est mis à jour dès qu'une valeur plus grande est trouvée.
 * Un tableau vide renvoie 0, comme demandé par le pseudo-code.
 *
 * Sortie :
 *   findMaximum({3, 9, 2, 7}) = 9  (expected 9)
 *   findMaximum({-5, -2, -9}) = -2 (expected -2)
 *   findMaximum({})           = 0  (expected 0)
 */
public class FindMaximum {
    public static void main(String[] args) {
        int[] first = {3, 9, 2, 7};
        int[] second = {-5, -2, -9};
        int[] empty = {};

        System.out.println("findMaximum({3, 9, 2, 7}) = " + findMaximum(first) + "  (expected 9)");
        System.out.println("findMaximum({-5, -2, -9}) = " + findMaximum(second) + " (expected -2)");
        System.out.println("findMaximum({})           = " + findMaximum(empty) + "  (expected 0)");
    }

    static int findMaximum(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }

        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
}
