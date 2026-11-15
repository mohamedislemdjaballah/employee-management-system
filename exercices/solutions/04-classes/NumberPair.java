/*
 * Exercice 18 - Corrigé (version formateur)
 *
 * NumberPair stocke deux entiers privés. sum() et product() calculent à
 * partir des champs, et setFirst modifie l'un d'eux par l'intermédiaire
 * d'une méthode.
 *
 * Sortie :
 *   Sum: 13
 *   Product: 42
 *   New sum: 17
 */
public class NumberPair {
    private int first;
    private int second;

    NumberPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    int sum() {
        return first + second;
    }

    int product() {
        return first * second;
    }

    void setFirst(int value) {
        first = value;
    }

    int getFirst() {
        return first;
    }

    public static void main(String[] args) {
        NumberPair pair = new NumberPair(6, 7);

        System.out.println("Sum: " + pair.sum());
        System.out.println("Product: " + pair.product());

        pair.setFirst(10);
        System.out.println("New sum: " + pair.sum());
    }
}
