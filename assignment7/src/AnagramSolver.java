/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Brian Nguyen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *  TA name: Sai
 *  Number of slip days I am using: 1
 */

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

public class AnagramSolver {

    private HashMap<String, LetterInventory> inventories;

    private static class AnagramComparator implements Comparator<List<String>> {
        public int compare(List<String> anagram1, List<String> anagram2) {
            // first sort by list size
            if (anagram1.size() < anagram2.size()) {
                return -1;
            } else if (anagram1.size() > anagram2.size()) {
                return 1;
            } else {
                // same list size, so now sort by its words
                for (int i = 0; i < anagram1.size(); i++) {
                    String s1 = anagram1.get(i);
                    String s2 = anagram2.get(i);
                    if (s1.compareTo(s2) != 0) {
                        return s1.compareTo(s2);
                    }
                }
                return 0;
            }
        }
    }

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Failed precondition: AnagramSolver method");
        }
        // make map with inventory for every word
        inventories = new HashMap<>();
        for (String word : dictionary) {
            LetterInventory inv = new LetterInventory(word);
            inventories.put(word, inv);
        }
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        if (maxWords < 0 || s == null || !containsEnglishLetter(s)) {
            throw new IllegalArgumentException("Failed precondition: getAnagrams method");
        }
        ArrayList<List<String>> results = new ArrayList<>();
        // do initial shrinking of words we can use
        ArrayList<String> words = possibleWords(s);
        LetterInventory inv = new LetterInventory(s);
        // prepare variables to set up recursion method
        int startIndex = 0;
        List<String> anagramsTracker = new ArrayList<>();
        anagramsHelper(results, anagramsTracker, inv, maxWords, words, startIndex);
        AnagramComparator comp = new AnagramComparator();
        Collections.sort(results, comp);
        return results;
    }

    private void anagramsHelper(ArrayList<List<String>> anagrams, List<String> anagramsSoFar,
                                LetterInventory inv, int maxWords, ArrayList<String> wordsList,
                                int index) {
        // base case - inventory runs out of letters to use
        if (inv.isEmpty()) {
            Collections.sort(anagramsSoFar);
            anagrams.add(copy(anagramsSoFar));
        // recursive case - current anagrams list isn't max yet or continue if no limit
        } else if (anagramsSoFar.size() < maxWords || maxWords == 0) {
            // look at smaller list and start at index provided so words aren't overly repeated
            for (int i = index; i < wordsList.size(); i++) {
                LetterInventory invOther = inventories.get(wordsList.get(i));
                LetterInventory invNew = inv.subtract(invOther);
                // make sure there is enough letters to make the word
                if (invNew != null) {
                    anagramsSoFar.add(wordsList.get(i));
                    anagramsHelper(anagrams, anagramsSoFar, invNew, maxWords, wordsList, i);
                    anagramsSoFar.remove(wordsList.get(i));
                }
            }
        }
    }

    // method to do initial shrinking of initial dictionary
    private ArrayList<String> possibleWords(String s) {
        ArrayList<String> result = new ArrayList<>();
        LetterInventory inv = new LetterInventory(s);
        for (String word : inventories.keySet()) {
            LetterInventory invNew = inv.subtract(inventories.get(word));
            // make sure there is enough letters to make the word
            if (invNew != null) {
                result.add(word);
            }
        }
        return result;
    }

    // make a deep copy of arraylist
    private ArrayList<String> copy(List<String> old) {
        ArrayList<String> result = new ArrayList<>();
        for (String word : old) {
            result.add(word);
        }
        return result;
    }

    // method to make sure the word has at least one English letter
    private boolean containsEnglishLetter(String word) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if ((letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z')) {
                return true;
            }
        }
        return false;
    }

}
