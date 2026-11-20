/*
 * Exercice 6 - Traduisez le pseudo-code en Java
 *
 * PSEUDO-CODE
 * -----------
 * fonction sumEvenNumbers(n)
 *     total <- 0
 *     pour i de 1 à n (inclus)
 *         si i est divisible par 2
 *             total <- total + i
 *         fin si
 *     fin pour
 *     retourner total
 * fin fonction
 *
 * VOTRE TÂCHE
 * -----------
 * Traduisez le pseudo-code dans le corps de sumEvenNumbers(int n).
 * Utilisez une boucle for et l'opérateur modulo (%).
 *
 * Exemple : sumEvenNumbers(6) = 2 + 4 + 6 = 12
 *
 * Compilez avec : javac SumEvenNumbers.java
 * Exécutez avec : java SumEvenNumbers
 */
public class SumEvenNumbers {
    public static void main(String[] args) {
        System.out.println("sumEvenNumbers(6)  = " + sumEvenNumbers(6) + "  (expected 12)");
        System.out.println("sumEvenNumbers(10) = " + sumEvenNumbers(10) + " (expected 30)");
        System.out.println("sumEvenNumbers(1)  = " + sumEvenNumbers(1) + "   (expected 0)");
    }

    static int sumEvenNumbers(int n) {
        // TODO : traduisez le pseudo-code ici
        return 0;
    }
}
