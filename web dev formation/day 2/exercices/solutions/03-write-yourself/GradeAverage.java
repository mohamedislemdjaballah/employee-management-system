/*
 * Exercice 11 - Corrigé (version formateur)
 *
 * Une seule boucle calcule le total, la note la plus haute, la note la plus
 * basse et le nombre de notes >= 16. La moyenne est convertie en double avant
 * la division pour ne pas perdre la partie décimale. La lettre est choisie
 * avec une suite de if / else if.
 *
 * Sortie :
 *   Average: 16.00
 *   Highest: 19
 *   Lowest: 13
 *   Scores >= 16: 3
 *   Grade: B
 */
public class GradeAverage {
    public static void main(String[] args) {
        int[] scores = {15, 18, 13, 17, 19, 14};

        int total = 0;
        int highest = scores[0];
        int lowest = scores[0];
        int atLeast16 = 0;

        for (int i = 0; i < scores.length; i++) {
            total = total + scores[i];

            if (scores[i] > highest) {
                highest = scores[i];
            }
            if (scores[i] < lowest) {
                lowest = scores[i];
            }
            if (scores[i] >= 16) {
                atLeast16++;
            }
        }

        double average = (double) total / scores.length;

        String grade;
        if (average >= 18) {
            grade = "A";
        } else if (average >= 16) {
            grade = "B";
        } else if (average >= 14) {
            grade = "C";
        } else if (average >= 12) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.printf("Average: %.2f%n", average);
        System.out.println("Highest: " + highest);
        System.out.println("Lowest: " + lowest);
        System.out.println("Scores >= 16: " + atLeast16);
        System.out.println("Grade: " + grade);
    }
}
