/*
 * Exercice 17 - Corrigé (version formateur)
 *
 * Le champ value est privé et démarre à 0. Toutes les modifications passent
 * par les méthodes increment, add et reset, et getValue expose la valeur
 * actuelle.
 *
 * Sortie :
 *   Value: 2
 *   Value after adding 5: 7
 *   Value after reset: 0
 */
public class Counter {
    private int value = 0;

    void increment() {
        value = value + 1;
    }

    void add(int amount) {
        value = value + amount;
    }

    void reset() {
        value = 0;
    }

    int getValue() {
        return value;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        counter.increment();
        counter.increment();
        System.out.println("Value: " + counter.getValue());

        counter.add(5);
        System.out.println("Value after adding 5: " + counter.getValue());

        counter.reset();
        System.out.println("Value after reset: " + counter.getValue());
    }
}
