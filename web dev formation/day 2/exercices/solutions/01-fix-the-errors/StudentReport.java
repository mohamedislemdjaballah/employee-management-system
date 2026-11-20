/*
 * Exercice 4 - Corrigé (version formateur)
 *
 * Erreurs corrigées :
 *   1. Création de l'objet avec new Student("Maya", 92)
 *   2. Ajout des parenthèses à l'appel student.printReport()
 *   3. Fermeture de la chaîne "=== Report ==="
 *   4. Ajout du point-virgule manquant dans printReport()
 *
 * Sortie :
 *   === Report ===
 *   Maya: 92
 */
public class StudentReport {
    public static void main(String[] args) {
        printHeader();
        Student student = new Student("Maya", 92);
        student.printReport();
    }

    static void printHeader() {
        System.out.println("=== Report ===");
    }
}

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    void printReport() {
        System.out.println(name + ": " + grade);
    }
}
