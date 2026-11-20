/*
 * Exercice 3 - Corrigé (version formateur)
 *
 * Erreurs corrigées :
 *   1. Ajout du point-virgule manquant après l'initialisation du tableau
 *   2. Remplacement des virgules de l'en-tête de la boucle for
 *      par des points-virgules
 *   3. Appel de numbers.length (un attribut) et non de numbers.length()
 *   4. Ajout du point-virgule manquant après la boucle do-while
 *
 * Sortie :
 *   1 2 3 4 5
 *   Total: 15
 *   Countdown: 3
 *   Countdown: 2
 *   Countdown: 1
 */
public class LoopPractice {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        int total = 0;
        for (int i = 0; i < numbers.length; i++) {
            total = total + numbers[i];
        }
        System.out.println("Total: " + total);

        int count = 3;
        do {
            System.out.println("Countdown: " + count);
            count--;
        } while (count > 0);
    }
}
