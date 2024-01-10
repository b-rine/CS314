/* Student information for assignment:
 *
 *  On my honor, Brian Nguyen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *  TA name: Sai
 *  Number of slip days I am using: 1
 */

public class LetterInventory {

    private static final int ALPHABET_LENGTH = 26;
    private int[] letters;
    private int size;

    // constructor - determine the frequency of letters from given string
    public LetterInventory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Failed precondition: LetterInventory()");
        }
        letters = new int[ALPHABET_LENGTH];
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            // increment letter indices in order based on ascii value (starts at 'a')
            if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                letters[word.charAt(i) - 'a']++;
                size++;
            }
        }
    }

    // get method - get frequency of that letter in this inventory
    public int get(char letter) {
        return letters[Character.toLowerCase(letter) - 'a'];
    }

    // size method - size is total number of letters
    public int size() {
        return size;
    }

    // isEmpty() - no letters left in inventory
    public boolean isEmpty() {
        return size == 0;
    }

    // toString() - print all letters in alphabetical order
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int letter = 0; letter < ALPHABET_LENGTH; letter++) {
            for (int repeat = 0; repeat < letters[letter]; repeat++) {
                // conversion integer to char using their ascii value (starts at 'a')
                string.append((char) (letter + 'a'));
            }
        }
        return string.toString();
    }

    // add method - return new letter inventory of original plus other
    public LetterInventory add(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("Failed precondition: add method");
        }
        LetterInventory result = new LetterInventory("");
        for (int letter = 0; letter < ALPHABET_LENGTH; letter++) {
            result.letters[letter] += letters[letter] + other.letters[letter];
            result.size += result.letters[letter];
        }
        return result;
    }

    // subtract method - return new letter inventory of original minus other
    public LetterInventory subtract(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("Failed precondition: subtract method");
        }
        LetterInventory result = new LetterInventory("");
        for (int letter = 0; letter < ALPHABET_LENGTH; letter++) {
            // cannot subtract letters that don't exist, so return null
            if (letters[letter] < other.letters[letter]) {
                return null;
            }
            result.letters[letter] += letters[letter] - other.letters[letter];
            result.size += result.letters[letter];
        }
        return result;
    }

    // equals method - check that each letter frequency is the same
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        LetterInventory otherInv = (LetterInventory) other;
        for (int letter = 0; letter < ALPHABET_LENGTH; letter++) {
            if (letters[letter] != otherInv.letters[letter]) {
                return false;
            }
        }
        return true;
    }
}
