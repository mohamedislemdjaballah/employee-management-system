/*
 * Exercice 4 - Corrigez les erreurs de syntaxe
 *
 * Ce programme crée un objet Student et affiche un petit bulletin.
 * Il ne compile PAS. Trouvez et corrigez les 4 erreurs pour qu'il fonctionne.
 *
 * Règles :
 *   - Ne changez pas le comportement attendu du programme.
 *   - Compilez avec : javac StudentReport.java
 *   - Exécutez avec : java StudentReport
 *
 * Sortie attendue après correction :
 *   === Report ===
 *   Maya: 92
 */
public class StudentReport {
    public static void main(String[] args) {
        printHeader();
        Student student = Student("Maya", 92);
        student.printReport;
    }

    static void printHeader() {
        System.out.println("=== Report ===);
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
        System.out.println(name + ": " + grade)
    }
}
