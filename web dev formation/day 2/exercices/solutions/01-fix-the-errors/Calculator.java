/*
 * Exercice 2 - Corrigé (version formateur)
 *
 * Erreurs corrigées :
 *   1. Ajout du type de retour int manquant à add()
 *   2. Ajout de l'instruction return manquante dans average()
 *   3. Remplacement de l'affectation a = b par la comparaison a < b
 *   4. Ajout du point-virgule manquant après le println dans printSum()
 *
 * Sortie :
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

    static int add(int a, int b) {
        return a + b;
    }

    static double average(int a, int b) {
        double result = (a + b) / 2.0;
        return result;
    }

    static int largest(int a, int b) {
        int max = a;
        if (a < b) {
            max = b;
        }
        return max;
    }

    static void printSum(int a, int b) {
        System.out.println("Printed sum: " + (a + b));
    }
}
