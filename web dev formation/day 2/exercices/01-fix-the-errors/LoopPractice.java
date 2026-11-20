/*
 * Exercice 3 - Corrigez les erreurs de syntaxe
 *
 * Ce programme affiche un tableau, additionne ses valeurs et fait un
 * compte à rebours. Il ne compile PAS.
 * Trouvez et corrigez les 5 erreurs pour qu'il fonctionne.
 *
 * Règles :
 *   - Ne changez pas le comportement attendu du programme.
 *   - Compilez avec : javac LoopPractice.java
 *   - Exécutez avec : java LoopPractice
 *
 * Sortie attendue après correction :
 *   1 2 3 4 5
 *   Total: 15
 *   Countdown: 3
 *   Countdown: 2
 *   Countdown: 1
 */
public class LoopPractice {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5}

        for (int i = 0, i < numbers.length(), i++) {
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
        } while (count > 0)
    }
}
