import java.util.List;

// CodeCamp.java - CS314 Assignment 1 - Tester class

/*
 * Student information for assignment: 
 * Name: Brian Nguyen
 * email address: briannguyen@utexas.edu
 * UTEID: bn6652
 * Section 5 digit ID: 52620
 * Grader name: Sai Tanuj
 * Number of slip days used on this assignment: 0
 */

/*
 * CS314 Students: place results of shared birthdays experiments in this
 * comment.
 *
 * When running 1,000,000 experiments with 365 days a year and 182 people, I found that
 * there was an average of 45 pairs per experiment.
 *
 * I think that you'll need at least 50 people to have a 50% of two people sharing a birthday
 * out of 365 days.
 *
 * After running 50,000 experiments for 2 to 100 people, I found that only 23 people are needed
 * to exceed 50%. This did surprise me because 23 people covers less than a tenth of days in a
 * year. My guess, 50 people, had a 97% chance for at least one pair which is significantly
 * higher despite only doubling the number of people.
 */

/* Birthday experiment output
Num people: 2, number of experiments with one or more shared birthday: 50000, percentage: 0.30
Num people: 3, number of experiments with one or more shared birthday: 50000, percentage: 0.88
Num people: 4, number of experiments with one or more shared birthday: 50000, percentage: 1.64
Num people: 5, number of experiments with one or more shared birthday: 50000, percentage: 2.84
Num people: 6, number of experiments with one or more shared birthday: 50000, percentage: 4.04
Num people: 7, number of experiments with one or more shared birthday: 50000, percentage: 5.53
Num people: 8, number of experiments with one or more shared birthday: 50000, percentage: 7.56
Num people: 9, number of experiments with one or more shared birthday: 50000, percentage: 9.55
Num people: 10, number of experiments with one or more shared birthday: 50000, percentage: 11.77
Num people: 11, number of experiments with one or more shared birthday: 50000, percentage: 14.05
Num people: 12, number of experiments with one or more shared birthday: 50000, percentage: 16.88
Num people: 13, number of experiments with one or more shared birthday: 50000, percentage: 19.71
Num people: 14, number of experiments with one or more shared birthday: 50000, percentage: 22.18
Num people: 15, number of experiments with one or more shared birthday: 50000, percentage: 25.25
Num people: 16, number of experiments with one or more shared birthday: 50000, percentage: 28.44
Num people: 17, number of experiments with one or more shared birthday: 50000, percentage: 31.52
Num people: 18, number of experiments with one or more shared birthday: 50000, percentage: 34.78
Num people: 19, number of experiments with one or more shared birthday: 50000, percentage: 37.85
Num people: 20, number of experiments with one or more shared birthday: 50000, percentage: 41.20
Num people: 21, number of experiments with one or more shared birthday: 50000, percentage: 44.26
Num people: 22, number of experiments with one or more shared birthday: 50000, percentage: 47.34
Num people: 23, number of experiments with one or more shared birthday: 50000, percentage: 51.03
Num people: 24, number of experiments with one or more shared birthday: 50000, percentage: 53.96
Num people: 25, number of experiments with one or more shared birthday: 50000, percentage: 56.84
Num people: 26, number of experiments with one or more shared birthday: 50000, percentage: 60.14
Num people: 27, number of experiments with one or more shared birthday: 50000, percentage: 62.61
Num people: 28, number of experiments with one or more shared birthday: 50000, percentage: 65.54
Num people: 29, number of experiments with one or more shared birthday: 50000, percentage: 68.27
Num people: 30, number of experiments with one or more shared birthday: 50000, percentage: 70.69
Num people: 31, number of experiments with one or more shared birthday: 50000, percentage: 73.08
Num people: 32, number of experiments with one or more shared birthday: 50000, percentage: 75.30
Num people: 33, number of experiments with one or more shared birthday: 50000, percentage: 77.57
Num people: 34, number of experiments with one or more shared birthday: 50000, percentage: 79.66
Num people: 35, number of experiments with one or more shared birthday: 50000, percentage: 81.07
Num people: 36, number of experiments with one or more shared birthday: 50000, percentage: 83.00
Num people: 37, number of experiments with one or more shared birthday: 50000, percentage: 84.81
Num people: 38, number of experiments with one or more shared birthday: 50000, percentage: 86.17
Num people: 39, number of experiments with one or more shared birthday: 50000, percentage: 87.87
Num people: 40, number of experiments with one or more shared birthday: 50000, percentage: 89.01
Num people: 41, number of experiments with one or more shared birthday: 50000, percentage: 90.21
Num people: 42, number of experiments with one or more shared birthday: 50000, percentage: 91.64
Num people: 43, number of experiments with one or more shared birthday: 50000, percentage: 92.23
Num people: 44, number of experiments with one or more shared birthday: 50000, percentage: 93.41
Num people: 45, number of experiments with one or more shared birthday: 50000, percentage: 94.11
Num people: 46, number of experiments with one or more shared birthday: 50000, percentage: 94.96
Num people: 47, number of experiments with one or more shared birthday: 50000, percentage: 95.56
Num people: 48, number of experiments with one or more shared birthday: 50000, percentage: 95.88
Num people: 49, number of experiments with one or more shared birthday: 50000, percentage: 96.66
Num people: 50, number of experiments with one or more shared birthday: 50000, percentage: 97.02
Num people: 51, number of experiments with one or more shared birthday: 50000, percentage: 97.51
Num people: 52, number of experiments with one or more shared birthday: 50000, percentage: 97.79
Num people: 53, number of experiments with one or more shared birthday: 50000, percentage: 98.12
Num people: 54, number of experiments with one or more shared birthday: 50000, percentage: 98.43
Num people: 55, number of experiments with one or more shared birthday: 50000, percentage: 98.55
Num people: 56, number of experiments with one or more shared birthday: 50000, percentage: 98.80
Num people: 57, number of experiments with one or more shared birthday: 50000, percentage: 98.99
Num people: 58, number of experiments with one or more shared birthday: 50000, percentage: 99.21
Num people: 59, number of experiments with one or more shared birthday: 50000, percentage: 99.28
Num people: 60, number of experiments with one or more shared birthday: 50000, percentage: 99.40
Num people: 61, number of experiments with one or more shared birthday: 50000, percentage: 99.51
Num people: 62, number of experiments with one or more shared birthday: 50000, percentage: 99.62
Num people: 63, number of experiments with one or more shared birthday: 50000, percentage: 99.61
Num people: 64, number of experiments with one or more shared birthday: 50000, percentage: 99.75
Num people: 65, number of experiments with one or more shared birthday: 50000, percentage: 99.78
Num people: 66, number of experiments with one or more shared birthday: 50000, percentage: 99.83
Num people: 67, number of experiments with one or more shared birthday: 50000, percentage: 99.84
Num people: 68, number of experiments with one or more shared birthday: 50000, percentage: 99.85
Num people: 69, number of experiments with one or more shared birthday: 50000, percentage: 99.91
Num people: 70, number of experiments with one or more shared birthday: 50000, percentage: 99.89
Num people: 71, number of experiments with one or more shared birthday: 50000, percentage: 99.93
Num people: 72, number of experiments with one or more shared birthday: 50000, percentage: 99.93
Num people: 73, number of experiments with one or more shared birthday: 50000, percentage: 99.94
Num people: 74, number of experiments with one or more shared birthday: 50000, percentage: 99.97
Num people: 75, number of experiments with one or more shared birthday: 50000, percentage: 99.97
Num people: 76, number of experiments with one or more shared birthday: 50000, percentage: 99.97
Num people: 77, number of experiments with one or more shared birthday: 50000, percentage: 99.99
Num people: 78, number of experiments with one or more shared birthday: 50000, percentage: 99.99
Num people: 79, number of experiments with one or more shared birthday: 50000, percentage: 99.99
Num people: 80, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 81, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 82, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 83, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 84, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 85, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 86, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 87, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 88, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 89, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 90, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 91, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 92, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 93, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 94, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 95, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 96, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 97, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 98, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 99, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.00
*/

public class CodeCampTester {

    public static void main(String[] args) {
        final String newline = System.getProperty("line.separator");
        // test 1, hamming distance
        int[] h1 = { 3, 7, 3, 6, 2, 5, 2, 6, 4 };
        int[] h2 = { 4, 6, 2, 5, 2, 6, 3, 7, 3 };
        int expected = 8;
        int actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println("Test 1 hamming distance: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 1, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 1, hamming distance");
        }

        // test 2, hamming distance
        h1 = new int[] { -3, -1, 3, 4, 2, -6, 10, -7, 4, 3 };
        h2 = new int[] { -3, 8, 2, 4, 0, -1, 10, -2, 1, 3 };
        expected = 6;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test 2 hamming distance: expected value: " + expected
                + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 2, hamming distance");
        } else {
            System.out.println(" ***** FAILED ***** test 2, hamming distance");
        }

        // test 3, isPermutation
        int[] a = { 4, 3, 3, -2, 6, 7, 5 };
        int[] b = { -2, 3, 6, 4, 3, 5, 7 };
        boolean expectedBool = true;
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 3 isPermutation: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 3, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 3, isPermutation");
        }

        // test 4, is Permutation
        a = new int[] { 1, 2, 3 };
        b = new int[] { 1, 2, 1, 3 };
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 4 isPermutation: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 4, isPermutation");
        } else {
            System.out.println(" ***** FAILED ***** test 4, isPermutation");
        }

        // test 5, mostVowels
        String[] arrayOfStrings = { "AlabamaTexasArkansas", "OklahomaSucks", "dolphinsaresmart" };
        int expectedResult = 0;
        int actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "Test 5 mostVowels: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println("passed test 5, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 5, mostVowels");
        }

        // test 6, mostVowels
        arrayOfStrings = new String[] { "wow", "amazing", null, "bing bong",
                "abcdefghijklmnopqrstuvxyz" } ;
        expectedResult = 4;
        actualResult = CodeCamp.mostVowels(arrayOfStrings);
        System.out.println(newline + "Test 6 mostVowels: expected result: " + expectedResult
                + ", actual result: " + actualResult);
        if (actualResult == expectedResult) {
            System.out.println("passed test 6, mostVowels");
        } else {
            System.out.println("***** FAILED ***** test 6, mostVowels");
        }

        // test 7, sharedBirthdays
        int pairs = CodeCamp.sharedBirthdays(1, 10);
        System.out.println(newline + "Test 7 shared birthdays: expected: 0, actual: " + pairs);
        int expectedShared = 0;
        if (pairs == expectedShared) {
            System.out.println("passed test 7, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 7, shared birthdays");
        }

        // test 8, sharedBirthdays
        pairs = CodeCamp.sharedBirthdays(150, 365);
        System.out.println(newline + "Test 8 shared birthdays: expected: "
                + "a value of 1 or more, actual: " + pairs);
        if (pairs > 0) {
            System.out.println("passed test 8, shared birthdays");
        } else {
            System.out.println("***** FAILED ***** test 8, shared birthdays");
        }

        // test 9, queensAreASafe
        char[][] board = { { '.', '.', '.', 'q' },
                           { 'q', '.', '.', '.' },
                           { '.', '.', 'q', '.' },
                           { '.', '.', '.', '.' } };
        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 9 queensAreSafe: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 9, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 9, queensAreSafe");
        }

        board = new char[][] { { '.', '.', '.', 'q', '.' },
                               { '.', 'q', '.', '.', '.' },
                               { '.', '.', '.', '.', '.' },
                               { 'q', '.', '.', '.', '.' },
                               { '.', '.', 'q', '.', '.' } };
        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 10 queensAreSafe: expected value: " + expectedBool
                + ", actual value: " + actualBool);
        if (expectedBool == actualBool) {
            System.out.println(" passed test 10, queensAreSafe");
        } else {
            System.out.println(" ***** FAILED ***** test 10, queensAreSafe");
        }

        // test 11, vetValueOfMostValuablePlot
        int[][] city = { { 9, -4, -3, -4, -1 },
                         { -4, 9, -1, -1, -5 },
                         { -1, -2, -1, -4, -2 },
                         { -4, -2, -2, -3, -1 },
                         { -1, -1, -1, -1, -5 },
                         { -4, -2, -1, 0, -1 } };
        expected = 10;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 11 getValueOfMostValuablePlot: expected value: "
                + expected + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 11, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 11, getValueOfMostValuablePlot");
        }

        // test 12, getValueOfMostValuablePlot
        city = new int[][] { { 2, 5, 3, -1, 0, 4 },
                             { -3, 4, -6, 7, -2, 1 } };
        expected = 15;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 12 getValueOfMostValuablePlot: expected value: "
                + expected + ", actual value: " + actual);
        if (expected == actual) {
            System.out.println(" passed test 12, getValueOfMostValuablePlot");
        } else {
            System.out.println(" ***** FAILED ***** test 12, getValueOfMostValuablePlot");
        }

    } // end of main method

    // pre: list != null
    private static int[] intListToArray(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("list parameter may not be null.");
        }
        int[] result = new int[list.size()];
        int arrayIndex = 0;
        for (int x : list) {
            result[arrayIndex++] = x;
        }
        return result;
    }
}
