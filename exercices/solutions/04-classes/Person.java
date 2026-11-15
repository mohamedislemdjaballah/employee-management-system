/*
 * Exercice 16 - Corrigé (version formateur)
 *
 * La classe Person garde ses données privées. Le constructeur utilise this
 * pour distinguer les champs des paramètres, birthday() modifie le champ age,
 * et getFullName() joint les deux champs du nom avec un espace.
 *
 * Sortie :
 *   Full name: Ada Lovelace
 *   Age: 36
 *   After birthday, age: 37
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    String getFullName() {
        return firstName + " " + lastName;
    }

    void birthday() {
        age = age + 1;
    }

    int getAge() {
        return age;
    }

    public static void main(String[] args) {
        Person person = new Person("Ada", "Lovelace", 36);

        System.out.println("Full name: " + person.getFullName());
        System.out.println("Age: " + person.getAge());

        person.birthday();
        System.out.println("After birthday, age: " + person.getAge());
    }
}
