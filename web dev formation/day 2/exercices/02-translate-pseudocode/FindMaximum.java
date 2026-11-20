/*
 * Exercice 8 - Traduisez le pseudo-code en Java
 *
 * PSEUDO-CODE
 * -----------
 * fonction findMaximum(numbers)
 *     si la longueur de numbers vaut 0
 *         retourner 0
 *     fin si
 *     max <- numbers[0]
 *     pour i de 1 à (longueur de numbers - 1)
 *         si numbers[i] > max
 *             max <- numbers[i]
 *         fin si
 *     fin pour
 *     retourner max
 * fin fonction
 *
 * VOTRE TÂCHE
 * -----------
 * Traduisez le pseudo-code dans le corps de findMaximum(int[] numbers).
 * Utilisez numbers.length et une boucle for. N'utilisez pas Arrays.sort().
 *
 * Compilez avec : javac FindMaximum.java
 * Exécutez avec : java FindMaximum
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
        // TODO : traduisez le pseudo-code ici
        return 0;
    }
}
