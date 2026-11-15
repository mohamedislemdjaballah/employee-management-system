/*
 * Exercice 1 - Corrigé (version formateur)
 *
 * Erreurs corrigées :
 *   1. Chaîne fermée : "Ada" au lieu de "Ada
 *   2. Remplacement de la chaîne "17" par l'entier 17
 *   3. Ajout du point-virgule manquant après le premier println
 *   4. Écriture de System avec un S majuscule (Java distingue les majuscules)
 *
 * Sortie :
 *   Hello, Ada
 *   You are 17 years old.
 */
public class Greeting {
    public static void main(String[] args) {
        String name = "Ada";
        int age = 17;
        System.out.println("Hello, " + name);
        System.out.println("You are " + age + " years old.");
    }
}
