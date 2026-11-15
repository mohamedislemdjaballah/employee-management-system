/*
 * Exercice 10 - Corrigé (version formateur)
 *
 * Les valeurs inférieures à 2 ne sont pas premières. Sinon, tous les diviseurs
 * possibles jusqu'à la racine carrée de n sont testés ; si l'un d'eux divise n,
 * alors n n'est pas premier.
 *
 * Sortie :
 *   isPrime(2)  = true   (expected true)
 *   isPrime(17) = true  (expected true)
 *   isPrime(21) = false  (expected false)
 *   isPrime(1)  = false   (expected false)
 *   isPrime(97) = true  (expected true)
 */
public class IsPrime {
    public static void main(String[] args) {
        System.out.println("isPrime(2)  = " + isPrime(2) + "   (expected true)");
        System.out.println("isPrime(17) = " + isPrime(17) + "  (expected true)");
        System.out.println("isPrime(21) = " + isPrime(21) + "  (expected false)");
        System.out.println("isPrime(1)  = " + isPrime(1) + "   (expected false)");
        System.out.println("isPrime(97) = " + isPrime(97) + "  (expected true)");
    }

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        int divisor = 2;
        while (divisor * divisor <= n) {
            if (n % divisor == 0) {
                return false;
            }
            divisor++;
        }
        return true;
    }
}
