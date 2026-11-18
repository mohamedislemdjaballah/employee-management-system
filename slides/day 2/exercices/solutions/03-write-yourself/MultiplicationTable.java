/*
 * Exercice 13 - Corrigé (version formateur)
 *
 * La boucle externe contrôle les lignes, la boucle interne contrôle les
 * colonnes, et chaque produit est affiché sur 4 caractères. Le retour à la
 * ligne est affiché après la boucle interne, donc chaque ligne apparaît sur
 * sa propre ligne.
 *
 * Sortie :
 *      1   2   3   4   5
 *      2   4   6   8  10
 *      3   6   9  12  15
 *      4   8  12  16  20
 *      5  10  15  20  25
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        for (int row = 1; row <= 5; row++) {
            for (int column = 1; column <= 5; column++) {
                System.out.printf("%4d", row * column);
            }
            System.out.println();
        }
    }
}
