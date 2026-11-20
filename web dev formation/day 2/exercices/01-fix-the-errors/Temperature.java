/*
 * Exercice 5 - Corrigez les erreurs de syntaxe
 *
 * Ce programme convertit des degrés Celsius en Fahrenheit et décrit la
 * température. Il ne compile PAS.
 * Trouvez et corrigez les 4 erreurs pour qu'il fonctionne.
 *
 * Règles :
 *   - Ne changez pas le comportement attendu du programme.
 *     La valeur en Fahrenheit doit conserver sa partie décimale.
 *   - Compilez avec : javac Temperature.java
 *   - Exécutez avec : java Temperature
 *
 * Sortie attendue après correction :
 *   18.0C = 64.4F
 *   Mild
 */
public class Temperature {
    public static void main(String[] args) {
        double celsius = 18.0;
        int fahrenheit = toFahrenheit(celsius);
        System.out.println(celsius + "C = " + fahrenheit + "F");
        describe(18)
    }

    static double toFahrenheit(double c) {
        return c * 9 / 5 + 32
    }

    static void describe(int temp) {
        if (temp < 10) {
            System.out.println("Cold");
        } else if (temp < 25) {
            System.out.println("Mild");
        else {
            System.out.println("Warm");
        }
    }
}
