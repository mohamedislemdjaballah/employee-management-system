/*
 * Exercice 14 - Corrigé (version formateur)
 *
 * split(" ") donne les mots. Le mot le plus long est suivi pendant la boucle
 * qui parcourt ces mots. Le comptage des lettres se fait sur toute la phrase
 * convertie en minuscules, donc 'A' et 'a' sont comptés tous les deux.
 *
 * Sortie :
 *   Words: 8
 *   Longest word: programming
 *   Letter 'a' count: 7
 */
public class WordStats {
    public static void main(String[] args) {
        String sentence = "Java is a powerful and flexible programming language";

        String[] words = sentence.split(" ");

        String longest = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }

        String lowerCaseSentence = sentence.toLowerCase();
        int letterACount = 0;
        for (int i = 0; i < lowerCaseSentence.length(); i++) {
            if (lowerCaseSentence.charAt(i) == 'a') {
                letterACount++;
            }
        }

        System.out.println("Words: " + words.length);
        System.out.println("Longest word: " + longest);
        System.out.println("Letter 'a' count: " + letterACount);
    }
}
