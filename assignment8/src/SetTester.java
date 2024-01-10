/*
 * Student information for assignment:
 *
 *  Number of slip days used: 0
 *  Student 1: Brian Nguyen
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *  Grader name: Sai
 *
 *  Student 2: Siddharth Sundaram
 *  UTEID: svs833
 *  email address: siddharthsundaram@utexas.edu
 *
 * Abstract Implementation of Union, Intersection, or Difference Answer:
 * It is unwise to implement all 3 of intersection, difference, and union in the AbstractSet class
 * because in order to implement one of them in the AbstractSet class, you must call either one or
 * both of the other two to complete the implementation (this is because the return type is ISet
 * and only these methods return an ISet). If all 3 were implemented in the AbstractSet class,
 * the program would likely time out because it would be an infinite loop of each method making
 * calls to the other two.
 *
 * ------------------------EXPERIMENT RESULTS: -----------------------
 *
 * SORTEDSET:
 * --------------------------------------------------------------------------------------------------------------------------------------
 * File         Size (kb)     Total Words     Inc. Prev. Row     Unique Words     Inc. Prev. Row     Actual Time (sec)     Inc. Prev. Row
 * --------------------------------------------------------------------------------------------------------------------------------------
 * small_
 * sample          1               11                -                8                  -                0.001                  -
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Struwwelp
 * eter            34              5414             492x              1895              237x              0.132                 132x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Alice's
 * Adv. in
 * Wonderland      174             30359            5.6x              6304              3.3x              0.438                 3.3x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Pride and
 * Prejudice       755             130408           4.3x              14704             8.3x              3.640                 8.3x
 * --------------------------------------------------------------------------------------------------------------------------------------
 *
 * UNSORTEDSET:
 * --------------------------------------------------------------------------------------------------------------------------------------
 * File         Size (kb)     Total Words     Inc. Prev. Row     Unique Words     Inc. Prev. Row     Actual Time (sec)     Inc. Prev. Row
 * --------------------------------------------------------------------------------------------------------------------------------------
 * small_
 * sample          1               11                -                8                  -                0.005                  -
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Struwwelp
 * eter            34              5414             492x              1895              237x              0.054                 10.8x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Alice's
 * Adv. in
 * Wonderland      174             30359            5.6x              6304              3.3x              0.218                 4x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Pride and
 * Prejudice       755             130408           4.3x              14704             8.3x              1.105                 5.1x
 * --------------------------------------------------------------------------------------------------------------------------------------
 *
 * HASHSET:
 * --------------------------------------------------------------------------------------------------------------------------------------
 * File         Size (kb)     Total Words     Inc. Prev. Row     Unique Words     Inc. Prev. Row     Actual Time (sec)     Inc. Prev. Row
 * --------------------------------------------------------------------------------------------------------------------------------------
 * small_
 * sample          1               11                -                8                  -                2.894E-4               -
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Struwwelp
 * eter            34              5414             492x              1895              237x              0.012                 41.5x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Alice's
 * Adv. in
 * Wonderland      174             30359            5.6x              6304              3.3x              0.030                 2.5x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Pride and
 * Prejudice       755             130408           4.3x              14704             8.3x              0.111                 3.7x
 * --------------------------------------------------------------------------------------------------------------------------------------
 *
 * TREESET:
 * --------------------------------------------------------------------------------------------------------------------------------------
 * File         Size (kb)     Total Words     Inc. Prev. Row     Unique Words     Inc. Prev. Row     Actual Time (sec)     Inc. Prev. Row
 * --------------------------------------------------------------------------------------------------------------------------------------
 * small_
 * sample          1               11                -                8                  -               7.096E-4                -
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Struwwelp
 * eter            34              5414             492x              1895              237x              0.008                 11.3x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Alice's
 * Adv. in
 * Wonderland      174             30359            5.6x              6304              3.3x              0.051                 6.4x
 * --------------------------------------------------------------------------------------------------------------------------------------
 * Pride and
 * Prejudice       755             130408           4.3x              14704             8.3x              0.137                 2.7x
 * --------------------------------------------------------------------------------------------------------------------------------------
 *
 * For the following questions, N = number of total words, M = number of distinct words / words in the set
 * Big O for processText method:
 *  - SortedSet: O(N*M)
 *  - UnsortedSet: O(N*M)
 *  - HashSet: O(N)
 *  - TreeSet:O(NlogM)
 *
 * Big O for add method:
 *  - SortedSet: O(M)
 *  - UnsortedSet: O(M)
 *  - HashSet: O(1)
 *  - TreeSet:O(logM)
 *
 * Difference between HashSet and TreeSet when printing: The difference is that the printed version
 * of TreeSet is sorted in ascending order whereas the printed version of HashSet is unordered and
 * is unknown until printed.
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.UIManager;
import javax.swing.JFileChooser;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 */

public class SetTester {

    public static void main(String[] args) {

        studentTests();

        // CS314 Students. Uncomment this section when ready to
        // run your experiments
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }
//        catch(Exception e) {
//            System.out.println("Unable to change look and feel");
//        }
//        Scanner sc = new Scanner(System.in);
//        String response = "";
//        do {
//            largeTest();
//            System.out.print("Another file? Enter y to do another file: ");
//            response = sc.next();
//        } while( response != null && response.length() > 0
//                && response.substring(0,1).equalsIgnoreCase("y") );

    }

    public static void studentTests() {

        ISet<String> s1 = new UnsortedSet<>();
        s1.add("A");

        ISet<String> s2 = new SortedSet<>();
        s2.add("B");
        s2.add("C");

        // test 1 - size() from UnsortedSet
        boolean actual = s1.size() == 1;
        showTestResults(actual, true, 1, s1, null, "size() UnsortedSet");

        // test 2 - size() from SortedSet
        actual = s2.size() == 2;
        showTestResults(actual, true, 2, s2, null, "size() SortedSet");

        // test 3 - iterator() from UnsortedSet
        s1.add("B");
        s1.add("C");
        s2.add("A");
        Iterator<String> it1 = s1.iterator();
        Iterator<String> it2 = s2.iterator();
        boolean good = true;
        while (good && it1.hasNext()) {
            good = it1.next().equals(it2.next());
        }
        showTestResults(good, true, 3, s1, s2, "iterator() UnsortedSet");

        // test 4 - iterator() from SortedSet
        ISet<String> s3 = new SortedSet<>();
        s3.add("A");
        s3.add("B");

        ISet<String> s4 = new SortedSet<>();
        s4.add("A");
        s4.add("B");
        Iterator<String> it3 = s3.iterator();
        Iterator<String> it4 = s4.iterator();
        good = true;
        while (good && it3.hasNext()) {
            good = it3.next().equals(it4.next());
        }
        showTestResults(good, true, 4, s3, s4, "iterator() SortedSet");

        // test 5 - clear() from UnsortedSet
        s1.clear();
        actual = s1.size() == 0;
        showTestResults(actual, true, 5, s1, null, "clear() UnsortedSet");

        // test 6 - clear() from SortedSet
        s2.clear();
        actual = s2.size() == 0;
        showTestResults(actual, true, 6, s2, null, "clear() SortedSet");

        // test 7 - add() from UnsortedSet
        s1.add("A");
        actual = s1.contains("A");
        showTestResults(actual, true, 7, s1, null, "add() UnsortedSet");

        // test 8 - add() from SortedSet
        s2.add("B");
        actual = s2.contains("B");
        showTestResults(actual, true, 8, s2, null, "add() SortedSet");

        // test 9 - difference() from UnsortedSet
        s1 = new UnsortedSet<>();
        s1.add("A");
        s1.add("B");
        s2 = new UnsortedSet<>();
        s2.add("B");
        ISet<String> expected = new UnsortedSet<>();
        expected.add("A");
        s3 = s1.difference(s2);
        actual = s3.equals(expected);
        showTestResults(actual, true, 9, s1, s2, "difference() UnsortedSet");

        // test 10 - difference() from SortedSet
        s1 = new SortedSet<>();
        s1.add("E");
        s1.add("F");
        s2 = new SortedSet<>();
        s2.add("E");
        expected = new SortedSet<>();
        expected.add("F");
        s3 = s1.difference(s2);
        actual = s3.equals(expected);
        showTestResults(actual, true, 10, s1, s2, "difference() SortedSet");

        // test 11 - union() from UnsortedSet
        s1 = new UnsortedSet<>();
        s1.add("A");
        s1.add("B");
        s2 = new UnsortedSet<>();
        s2.add("C");
        s3 = s1.union(s2);
        expected = new UnsortedSet<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        actual = s3.equals(expected);
        showTestResults(actual, true, 11, s1, s2, "union() UnsortedSet");

        // test 12 - union() from SortedSet
        s1 = new SortedSet<>();
        s1.add("Z");
        s1.add("Y");
        s2 = new SortedSet<>();
        s2.add("X");
        s3 = s1.union(s2);
        expected = new SortedSet<>();
        expected.add("Z");
        expected.add("Y");
        expected.add("X");
        actual = s3.equals(expected);
        showTestResults(actual, true, 12, s1, s2, "union() SortedSet");

        // test 13 - UnsortedSet() from UnsortedSet
        s1 = new UnsortedSet<>();
        actual = s1 instanceof UnsortedSet;
        showTestResults(actual, true, 13, s1, null, "UnsortedSet() UnsortedSet");

        // test 14 - SortedSet() from SortedSet
        s2 = new SortedSet<>();
        actual = s2 instanceof SortedSet;
        showTestResults(actual, true, 14, s2, null, "SortedSet() SortedSet");

        // test 15 - SortedSet(other) from SortedSet
        s2 = new SortedSet<>(s1);
        actual = s2 instanceof SortedSet;
        showTestResults(actual, true, 15, s2, null, "SortedSet(other) SortedSet");

        // test 16 - min() from SortedSet
        s1 = new SortedSet<>();
        s1.add("B");
        s1.add("A");
        actual = ((SortedSet<String>) s1).min().equals("A");
        showTestResults(actual, true, 16, s1, null, "min() SortedSet");

        // test 17 - max() from SortedSet
        actual = ((SortedSet<String>) s1).max().equals("B");
        showTestResults(actual, true, 17, s1, null, "max() SortedSet");

        // test 18 - addAll() from AbstractSet
        s1 = new UnsortedSet<>();
        s1.add("A");
        s2 = new SortedSet<>();
        s2.add("B");
        s2.add("C");
        s1.addAll(s2);
        actual = s1.contains("A") && s1.contains("B") && s1.contains("C");
        showTestResults(actual, true, 18, s1, null, "addAll() UnsortedSet");

        // test 19 - addAll() from SortedSet
        s1.add("E");
        s1.add("F");
        s2.addAll(s1);
        actual = s2.contains("A") && s2.contains("B") && s2.contains("C") && s2.contains("E")
                && s2.contains("F");
        showTestResults(actual, true, 19, s2, null, "addAll() SortedSet");

        // test 20 - contains() from AbstractSet
        s1 = new UnsortedSet<>();
        s1.add("A");
        actual = s1.contains("A");
        showTestResults(actual, true, 20, s1, null, "contains() UnsortedSet");

        // test 21 - contains() from SortedSet
        s2 = new SortedSet<>();
        s2.add("B");
        actual = s2.contains("B");
        showTestResults(actual, true, 21, s2, null, "contains() SortedSet");

        // test 22 - containsAll() from AbstractSet
        s1.add("B");
        s2.add("A");
        actual = s1.containsAll(s2);
        showTestResults(actual, true, 22, s1, s2, "containsAll() UnsortedSet");

        // test 23 - containsAll() from SortedSet
        actual = s2.containsAll(s1);
        showTestResults(actual, true, 23, s1, s2, "containsAll() SortedSet");

        // test 24 - equals() from AbstractSet
        actual = s1.equals(s2);
        showTestResults(actual, true, 24, s1, s2, "equals() UnsortedSet");

        // test 25 - equals() from SortedSet
        s1.add("C");
        s2.add("D");
        actual = s2.equals(s1);
        showTestResults(actual, false, 25, s1, s2, "equals() SortedSet");

        // test 26 - remove() from AbstractSet
        s1.remove("C");
        actual = !s1.contains("C");
        showTestResults(actual, true, 26, s1, null, "remove() UnsortedSet");

        // test 27 - remove() from SortedSet
        s2.remove("A");
        actual = !s2.contains("A");
        showTestResults(actual, true, 27, s2, null, "remove() SortedSet");

        // test 28 - intersection() from AbstractSet
        s1 = new UnsortedSet<>();
        s1.add("A");
        s1.add("B");
        s1.add("C");
        s2 = new UnsortedSet<>();
        s2.add("B");
        s2.add("C");
        s2.add("D");
        s3 = s1.intersection(s2);
        expected = new UnsortedSet<>();
        expected.add("B");
        expected.add("C");
        actual = s3.equals(expected);
        showTestResults(actual, true, 28, s1, s2, "intersection() UnsortedSet");

        // test 29 - toString() from AbstractSet
        String output = "(A, B, C)";
        actual = s1.toString().equals(output);
        showTestResults(actual, true, 29, s1, null, "intersection() UnsortedSet");

    }

    // print out results of test
    private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
                                            int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
        if (actualResult == expectedResult) {
            System.out.println("Passed test " + testNumber);
        } else {
            System.out.print("Failed test ");
            System.out.println(testNumber + ": " + testDescription);
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result  : " + actualResult);
            System.out.println("Set 1: " + set1);
            if (set2 != null) {
                System.out.println("Set 2: " + set2);
            }
        }

    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
                                            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
                                                int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evaluating the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
    }
}
