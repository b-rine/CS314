/*  Student information for assignment:
 *
 *  On my honor, Brian Nguyen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Brian Nguyen
 *  email address: briannguyen@utexas.edu
 *  UTEID: bn6652
 *  Section 5 digit ID: bn6652
 *  Grader name: Sai Tanuj
 *  Number of slip days used on this assignment: 2
 */

import java.util.*;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    private Set<String> wordList;
    private HangmanDifficulty difficulty;
    private int wordLength;
    private int guesses;
    private int wrongGuesses;
    private ArrayList<Character> charGuessed;
    private String pattern;
    private int turn;
    private boolean debug;


    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        wordList = new HashSet<>(words);
        debug = debugOn;
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        new HangmanManager(words, false);
    }

    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary
     * with the given length
     */
    public int numWords(int length) {
        int total = 0;
        for (String word : wordList) {
            if (word.length() == length) {
                total++;
            }
        }
        return total;
    }

    /**
     * Get for a new round of Hangman. Think of a round as a
     * complete game of Hangman.
     * @param wordLen the length of the word to pick this time.
     * numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the
     * player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
        // resetting instance variables each time a new round starts
        wordLength = wordLen;
        guesses = numGuesses;
        difficulty = diff;
        wrongGuesses = 0;
        StringBuilder pat = new StringBuilder();
        for (int i = 0; i < wordLen; i++) {
            pat.append("-");
        }
        pattern = pat.toString();
        charGuessed = new ArrayList<>();
        turn = 1;
    }

    /**
     * The number of words still possible (live) based on the guesses so far.
     *  Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the
     * original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return wordList.size();
    }

    /**
     * Get the number of wrong guesses the user has left in
     * this round (game) of Hangman.
     * @return the number of wrong guesses the user has left
     * in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        // guesses only go down each time user is wrong
        return guesses - wrongGuesses;
    }

    /**
     * Return a String that contains the letters the user has guessed
     * so far during this round.
     * The characters in the String are in alphabetical order.
     * The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user
     * has guessed so far during this round.
     */
    public String getGuessesMade() {
        StringBuilder charList = new StringBuilder("[");
        if (!charGuessed.isEmpty()) {
            charList.append(charGuessed.get(0));
        }
        for (int i = 1; i < charGuessed.size(); i++) {
            charList.append(", ").append(charGuessed.get(i));
        }
        return charList.append("]").toString();
    }

    /**
     * Check the status of a character.
     * @param guess The character to check.
     * @return true if guess has been used or guessed this round of Hangman,
     * false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        return charGuessed.contains(guess);
    }

    /**
     * Get the current pattern. The pattern contains '-'s for
     * unrevealed (or guessed) characters and the actual character 
     * for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Update the game status (pattern, wrong guesses, word list),
     * based on the give guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        if (alreadyGuessed(guess)) {
            throw new IllegalStateException("This character has already been guessed.");
        }
        addChars(guess);
        boolean hardest = false;
        // deciding when to use the hardest or second hardest pattern based on game difficulty
        // and what turn the player is on
        if ((difficulty == HangmanDifficulty.EASY && turn % 2 == 0) ||
                (difficulty == HangmanDifficulty.MEDIUM && turn % 4 == 0)) {
            hardest = false;
        } else {
            hardest = true;
        }
        HashMap<String, ArrayList<String>> wordFams = wordFamilies(guess);
        TreeMap<String, Integer> numInFams = numInFamilies(wordFams);
        HashMap<Pattern, Integer> mostWords = biggestFam(numInFams, hardest);
        ArrayList<Pattern> leastChars = smallestPatterns(mostWords, hardest);
        Pattern pat = choosePattern(leastChars, hardest);
        // guess is wrong if the pattern stays the same
        if (pattern.compareTo(pat.pattern) == 0) {
            wrongGuesses++;
        }
        pattern = pat.getPattern();
        if (debug) {
            System.out.println("DEBUGGING: New pattern is: " + pattern + ". New family has " +
                    numInFams.get(pattern) + " words.");
            System.out.println();
        }
        // make new active wordlist to add the words from the family we're going into now
        Set<String> newList = new HashSet<>();
        for (String word : wordFams.get(pattern)) {
            newList.add(word);
        }
        wordList = newList;
        turn++;
        return numInFams;
    }

    // making word families based on pattern
    private HashMap<String, ArrayList<String>> wordFamilies(char guess) {
        HashMap<String, ArrayList<String>> wordFams = new HashMap<>();
        for (String word : wordList) {
            ArrayList<String> fam = new ArrayList<>();
            // create new string pattern each time we look at a new pattern
            StringBuilder thisPattern = new StringBuilder(pattern);
            for (int i = 0; i < wordLength; i++) {
                if (word.charAt(i) == guess) {
                    thisPattern.replace(i, i + 1, guess + "");
                }
            }
            // if pattern doesn't already exist in the map, then add the pattern and arraylist
            if (!wordFams.containsKey(thisPattern.toString())) {
                fam.add(word);
                wordFams.put(thisPattern.toString(), fam);
            // otherwise just get the value and add to that arraylist with new word
            } else {
                ArrayList<String> value = wordFams.get(thisPattern.toString());
                value.add(word);
                wordFams.put(thisPattern.toString(), value);
            }
        }
        return wordFams;
    }

    // making another map but with patterns and NUMBER of words (instead of the actual words)
    private TreeMap<String, Integer> numInFamilies(HashMap<String, ArrayList<String>> wordFams) {
        TreeMap<String, Integer> numInFams = new TreeMap<>();
        for (String pattern : wordFams.keySet()) {
            int numWords = wordFams.get(pattern).size();
            numInFams.put(pattern, numWords);
        }
        return numInFams;
    }

    // if hardest difficulty, only add families with max value; otherwise add next largest families
    private HashMap<Pattern, Integer> biggestFam(TreeMap<String, Integer> numInFams,
                                                 boolean hardest) {
        HashMap<Pattern, Integer> mostWords = new HashMap<>();
        ArrayList<Integer> nums = new ArrayList<>();
        for (String pattern : numInFams.keySet()) {
            int elems = numInFams.get(pattern);
            nums.add(elems);
        }
        // sizes are now in biggest to smallest order
        nums.sort(Collections.reverseOrder());
        // we know the biggest is the first element so use that as a starting point
        int max = nums.get(0);
        if (hardest) {
            for (String pattern : numInFams.keySet()) {
                if (numInFams.get(pattern) == max) {
                    Pattern pat = new Pattern(pattern);
                    mostWords.put(pat, numInFams.get(pattern));
                }
            }
        } else {
            // look for the second-largest max number of elements
            boolean nextMaxFound = false;
            int numsIndex = 0;
            while (!nextMaxFound && numsIndex < nums.size()) {
                if (nums.get(numsIndex) < max) {
                    max = nums.get(numsIndex);
                    nextMaxFound = true;
                }
                numsIndex++;
            }
            for (String pattern : numInFams.keySet()) {
                if (numInFams.get(pattern) == max) {
                    Pattern pat = new Pattern(pattern);
                    mostWords.put(pat, numInFams.get(pattern));
                }
            }
        }
        return mostWords;
    }

    private ArrayList<Pattern> smallestPatterns(HashMap<Pattern, Integer> mostWords,
                                                boolean hardest) {
        ArrayList<Pattern> leastChars = new ArrayList<>();
        ArrayList<Integer> numsRevealed = new ArrayList<>();
        for (Pattern p : mostWords.keySet()) {
            int chars = p.getChars();
            numsRevealed.add(chars);
        }
        Collections.sort(numsRevealed);
        int charsRevealed = numsRevealed.get(0);
        if (hardest) {
            for (Pattern p : mostWords.keySet()) {
                if (p.getChars() == charsRevealed) {
                    leastChars.add(p);
                }
            }
        } else {
            boolean nextLeastFound = false;
            int numsIndex = 0;
            while (!nextLeastFound && numsIndex < numsRevealed.size()) {
                if (numsRevealed.get(numsIndex) > charsRevealed) {
                    charsRevealed = numsRevealed.get(numsIndex);
                    nextLeastFound = true;
                }
                numsIndex++;
            }
            for (Pattern p : mostWords.keySet()) {
                if (p.getChars() == charsRevealed) {
                    leastChars.add(p);
                }
            }
        }
        return leastChars;
    }

    private Pattern choosePattern(ArrayList<Pattern> leastChars, boolean hardest) {
        Collections.sort(leastChars);
        if (hardest || leastChars.size() == 1) {
            if (!hardest && debug) {
                System.out.println();
                System.out.println("DEBUGGING: Should pick second hardest pattern this turn," +
                        " but only one pattern available.");
            }
            if (debug) {
                System.out.println();
                System.out.println("DEBUGGING: Picking hardest list.");
            }
            return leastChars.get(0);
        } else {
            // since it's already sorted, the second hardest pattern lexicographically should
            // always be the second element (unless there's only one element)
            if (debug) {
                System.out.println();
                System.out.println("DEBUGGING: Difficulty second hardest pattern and list.");
            }
            return leastChars.get(1);
        }
    }

    /**
     * Return the secret word this HangmanManager finally ended up
     * picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        Random rand = new Random();
        String[] words = wordList.toArray(new String[wordList.size()]);
        if (numWordsCurrent() > 1) {
            return words[rand.nextInt(words.length)];
        } else {
            return words[0];
        }
    }

    // adding chars to the list of guessed already in alphabetical order
    private void addChars(char guess) {
        int index = 0;
        while (index < charGuessed.size() && charGuessed.get(index).compareTo(guess) < 0) {
            index++;
        }
        charGuessed.add(index, guess);
    }

    class Pattern implements Comparable<Pattern>{
        // need number of characters revealed for a tiebreaker
        private int charsRevealed;
        private String pattern;

        public Pattern(String pat) {
            pattern = pat;
            for (int i = 0; i < pat.length(); i++) {
                if (pat.charAt(i) != '-') {
                    charsRevealed++;
                }
            }
        }

        public int getChars() {
            return charsRevealed;
        }

        public String getPattern() {
            return pattern;
        }

        public int compareTo(Pattern other) {
            return pattern.compareTo(other.pattern);
        }

    }

}
