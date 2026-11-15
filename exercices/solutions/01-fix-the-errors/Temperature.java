/*
 * Exercice 5 - Corrigé (version formateur)
 *
 * Erreurs corrigées :
 *   1. Déclaration de fahrenheit en double (un double ne peut pas être
 *      stocké dans un int sans conversion, et la partie décimale doit
 *      être conservée)
 *   2. Ajout du point-virgule manquant après describe(18)
 *   3. Ajout du point-virgule manquant après le return de toFahrenheit()
 *   4. Ajout de l'accolade fermante manquante avant else
 *
 * Sortie :
 *   18.0C = 64.4F
 *   Mild
 */
public class Temperature {
    public static void main(String[] args) {
        double celsius = 18.0;
        double fahrenheit = toFahrenheit(celsius);
        System.out.println(celsius + "C = " + fahrenheit + "F");
        describe(18);
    }

    static double toFahrenheit(double c) {
        return c * 9 / 5 + 32;
    }

    static void describe(int temp) {
        if (temp < 10) {
            System.out.println("Cold");
        } else if (temp < 25) {
            System.out.println("Mild");
        } else {
            System.out.println("Warm");
        }
    }
}
