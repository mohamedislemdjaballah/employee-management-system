/*
 * Exercice 2 - Corrigez les erreurs de syntaxe
 *
 * Ce programme effectue quelques calculs simples avec des méthodes.
 * Il ne compile PAS. Trouvez et corrigez les 4 erreurs pour qu'il fonctionne.
 *
 * Règles :
 *   - Ne changez pas le comportement attendu du programme.
 *   - Compilez avec : javac Calculator.java
 *   - Exécutez avec : java Calculator
 *
 * Sortie attendue après correction :
 *   Sum: 9
 *   Average: 4.5
 *   Largest: 5
 *   Printed sum: 9
 */
public class Calculator {
    public static void main(String[] args) {
        int sum = add(4, 5);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average(4, 5));
        System.out.println("Largest: " + largest(4, 5));
        printSum(4, 5);
    }

    static add(int a, int b) {
        return a + b;
    }

    static double average(int a, int b) {
        double result = (a + b) / 2.0;
    }

    static int largest(int a, int b) {
        int max = a;
        if (a = b) {
            max = b;
        }
        return max;
    }

    static void printSum(int a, int b) {
        System.out.println("Printed sum: " + (a + b))
    }
}
