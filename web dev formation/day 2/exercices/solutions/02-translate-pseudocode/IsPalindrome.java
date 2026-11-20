/*
 * Exercice 9 - Corrigé (version formateur)
 *
 * Deux index partent des deux extrémités du texte et se rapprochent vers le
 * centre. Dès que les caractères diffèrent, le texte n'est pas un palindrome.
 *
 * Sortie :
 *   isPalindrome("level") = true (expected true)
 *   isPalindrome("java")  = false  (expected false)
 *   isPalindrome("a")     = true     (expected true)
 *   isPalindrome("")      = true      (expected true)
 */
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println("isPalindrome(\"level\") = " + isPalindrome("level") + " (expected true)");
        System.out.println("isPalindrome(\"java\")  = " + isPalindrome("java") + "  (expected false)");
        System.out.println("isPalindrome(\"a\")     = " + isPalindrome("a") + "     (expected true)");
        System.out.println("isPalindrome(\"\")      = " + isPalindrome("") + "      (expected true)");
    }

    static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
