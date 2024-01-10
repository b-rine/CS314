import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
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

public class AnagramFinderTester {


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {
        cs314StudentTestsForLetterInventory();
    }

    private static void cs314StudentTestsForLetterInventory() {

        // test cases 1 - 2, LetterInventory(String word)
        LetterInventory letInv1 = new LetterInventory("brian");
        LetterInventory letInv2 = new LetterInventory("nguyen");
        Object expected = true;
        Object actual = letInv1 instanceof LetterInventory;
        showTestResults(expected, actual, 1, "LetterInventory constructor");

        expected = false;
        actual = letInv2.getClass().equals("hello".getClass());
        showTestResults(expected, actual, 2, "LetterInventory constructor");

        // test cases 3 - 4, get(char letter)
        expected = true;
        actual = letInv1.get('b') == 1;
        showTestResults(expected, actual, 3, "LetterInventory get");

        expected = false;
        actual = letInv2.get('n') != 2;
        showTestResults(expected, actual, 4, "LetterInventory get");


        // test cases 5 - 6, size()
        expected = 5;
        actual = letInv1.size();
        showTestResults(expected, actual, 5, "LetterInventory size");

        expected = 6;
        actual = letInv2.size();
        showTestResults(expected, actual, 6, "LetterInventory size");


        // test cases 7 - 8, isEmpty()
        expected = false;
        actual = letInv1.isEmpty();
        showTestResults(expected, actual, 7, "LetterInventory isEmpty");

        expected = true;
        LetterInventory blank = new LetterInventory("");
        actual = blank.isEmpty();
        showTestResults(expected, actual, 8, "LetterInventory isEmpty");

        // test cases 9 - 10, toString()
        expected = "abinr";
        actual = letInv1.toString();
        showTestResults(expected, actual, 9, "LetterInventory toString");

        expected = "egnnuy";
        actual = letInv2.toString();
        showTestResults(expected, actual, 10, "LetterInventory toString");


        // test cases 11 - 12, add(LetterInventory other)
        expected = "abeginnnruy";
        LetterInventory letInv3 = letInv1.add(letInv2);
        actual = letInv3.toString();
        showTestResults(expected, actual, 11, "LetterInventory add");

        expected = "abeginnnooruyz";
        LetterInventory letInv4 = new LetterInventory("zoo");
        LetterInventory letInv5 = letInv3.add(letInv4);
        actual = letInv5.toString();
        showTestResults(expected, actual, 12, "LetterInventory add");

        // test cases 13 - 14, subtract(LetterInventory other)
        expected = "abeginnnruy";
        letInv5 = letInv5.subtract(letInv4);
        actual = letInv5.toString();
        showTestResults(expected, actual, 13, "LetterInventory subtract");

        expected = "abinr";
        letInv5 = letInv3.subtract(letInv2);
        actual = letInv5.toString();
        showTestResults(expected, actual, 14, "LetterInventory subtract");

        // test cases 15 - 16, equals(LetterInventory other)
        expected = true;
        letInv1 = new LetterInventory("B3r5$i.a^n");
        letInv2 = new LetterInventory("n*ir)93ab");
        actual = letInv1.equals(letInv2);
        showTestResults(expected, actual, 15, "LetterInventory equals");

        expected = false;
        letInv1 = new LetterInventory("!wiN*ne3R");
        letInv2 = new LetterInventory("2No!twi#nNEr!");
        actual = letInv1.equals(letInv2);
        showTestResults(expected, actual, 16, "LetterInventory equals");

    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0 
                && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual, 
            int testNum, String featureTested) {
        
        System.out.println("Test Number " + testNum + " testing " 
                + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) 
                || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, 
            boolean displayAnagrams) {
        
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams 
                    = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if(displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if(checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
                System.out.println("Time to find anagrams: " + st.time());
                /* System.out.println("Number of calls to recursive helper method: "
                        + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));*/
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " 
                        + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for (List<String> singleAnagram : anagrams) {
            System.out.println(singleAnagram);
        }
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: " 
                    + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: " 
                        + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   " 
                        + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, "
                        + "but either a difference in anagrams or"
                        + " anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }  
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams 
                    : "Wrong number of angrams read or expected";
        }
    }
}
