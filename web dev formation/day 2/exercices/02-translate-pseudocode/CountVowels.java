/*
 * Exercice 7 - Traduisez le pseudo-code en Java
 *
 * PSEUDO-CODE
 * -----------
 * fonction countVowels(text)
 *     count <- 0
 *     pour chaque caractère c de text
 *         lower <- minuscule(c)
 *         si lower vaut 'a' ou 'e' ou 'i' ou 'o' ou 'u'
 *             count <- count + 1
 *         fin si
 *     fin pour
 *     retourner count
 * fin fonction
 *
 * VOTRE TÂCHE
 * -----------
 * Traduisez le pseudo-code dans le corps de countVowels(String text).
 * Utilisez text.length(), text.charAt(i) et Character.toLowerCase(c).
 * Comptez les voyelles majuscules et minuscules (A et a comptent tous
 * les deux).
 *
 * Compilez avec : javac CountVowels.java
 * Exécutez avec : java CountVowels
 */
public class CountVowels {
    public static void main(String[] args) {
        System.out.println("countVowels(\"Hello World\")      = " + countVowels("Hello World") + " (expected 3)");
        System.out.println("countVowels(\"Java Programming\") = " + countVowels("Java Programming") + " (expected 5)");
        System.out.println("countVowels(\"xyz\")              = " + countVowels("xyz") + " (expected 0)");
    }

    static int countVowels(String text) {
        // TODO : traduisez le pseudo-code ici
        return 0;
    }
}
