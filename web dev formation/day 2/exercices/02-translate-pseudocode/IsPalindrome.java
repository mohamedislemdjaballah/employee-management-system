/*
 * Exercice 9 - Traduisez le pseudo-code en Java
 *
 * PSEUDO-CODE
 * -----------
 * fonction isPalindrome(text)
 *     left <- 0
 *     right <- longueur de text - 1
 *     tant que left < right
 *         si text[left] != text[right]
 *             retourner false
 *         fin si
 *         left <- left + 1
 *         right <- right - 1
 *     fin tant que
 *     retourner true
 * fin fonction
 *
 * VOTRE TÂCHE
 * -----------
 * Traduisez le pseudo-code dans le corps de isPalindrome(String text).
 * Utilisez text.charAt(index) pour lire un caractère.
 * Supposez que le texte ne contient que des lettres minuscules et
 * aucun espace.
 *
 * Compilez avec : javac IsPalindrome.java
 * Exécutez avec : java IsPalindrome
 */
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println("isPalindrome(\"level\") = " + isPalindrome("level") + " (expected true)");
        System.out.println("isPalindrome(\"java\")  = " + isPalindrome("java") + "  (expected false)");
        System.out.println("isPalindrome(\"a\")     = " + isPalindrome("a") + "     (expected true)");
        System.out.println("isPalindrome(\"\")      = " + isPalindrome("") + "      (expected true)");
    }

    static boolean isPalindrome(String text) {
        // TODO : traduisez le pseudo-code ici
        return false;
    }
}
