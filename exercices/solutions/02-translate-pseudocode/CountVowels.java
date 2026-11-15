/*
 * Exercice 7 - Corrigé (version formateur)
 *
 * Le pseudo-code parcourt chaque caractère, le convertit en minuscule et
 * incrémente le compteur lorsque le caractère est une voyelle.
 *
 * Sortie :
 *   countVowels("Hello World")      = 3 (expected 3)
 *   countVowels("Java Programming") = 5 (expected 5)
 *   countVowels("xyz")              = 0 (expected 0)
 */
public class CountVowels {
    public static void main(String[] args) {
        System.out.println("countVowels(\"Hello World\")      = " + countVowels("Hello World") + " (expected 3)");
        System.out.println("countVowels(\"Java Programming\") = " + countVowels("Java Programming") + " (expected 5)");
        System.out.println("countVowels(\"xyz\")              = " + countVowels("xyz") + " (expected 0)");
    }

    static int countVowels(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char lower = Character.toLowerCase(text.charAt(i));
            if (lower == 'a' || lower == 'e' || lower == 'i'
                    || lower == 'o' || lower == 'u') {
                count++;
            }
        }
        return count;
    }
}
