/*
 * Exercice 10 - Traduisez le pseudo-code en Java
 *
 * PSEUDO-CODE
 * -----------
 * fonction isPrime(n)
 *     si n < 2
 *         retourner false
 *     fin si
 *     divisor <- 2
 *     tant que divisor * divisor <= n
 *         si n est divisible par divisor
 *             retourner false
 *         fin si
 *         divisor <- divisor + 1
 *     fin tant que
 *     retourner true
 * fin fonction
 *
 * VOTRE TÂCHE
 * -----------
 * Traduisez le pseudo-code dans le corps de isPrime(int n).
 * Utilisez une boucle while et l'opérateur modulo (%).
 *
 * Compilez avec : javac IsPrime.java
 * Exécutez avec : java IsPrime
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
        // TODO : traduisez le pseudo-code ici
        return false;
    }
}
