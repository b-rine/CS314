/*  Student information for assignment:
 *
 *  On our honor, Siddharth Sundaram and Brian Nguyen, this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1: Siddharth Sundaram
 *  UTEID: svs833
 *  email address: siddharthsundaram@utexas.edu
 *  Grader name: Sai
 *  Section number: 52620
 *
 *  Student 2: Brian Nguyen
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */
public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
        doBinaryTests();
        doReverseTests();
        doNextIsDoubleTests();
        doListMnemonicsTests();
        // doCarpetTest();
        doMazeTests();
        doFlowOffMapTests();
        doFairTeamsTests();
        studentTests();
    }

    // post: run student test
    private static void studentTests() {
        // CS314 students put your tests here

    }

    private static void doMazeTests() {
        int mazeTestNum = 1;

        // Test 1, case without an exit
        String m = "$S$$GYGY";
        runMazeTest(m, 2, 0, mazeTestNum++);

        // Test 2, case where cant collect all the coins
        m = "$Y$SE";
        runMazeTest(m, 1, 1, mazeTestNum++);
    }

    private static void runMazeTest(String rawMaze, int rows, int expected, int num) {
        char[][] maze = makeMaze(rawMaze, rows);
        System.out.println("Can escape maze test number " + num);
        printMaze(maze);
        int actual = Recursive.canEscapeMaze(maze);
        if (expected == actual) {
            System.out.println("passed test " + num);
        } else {
            System.out.println("FAILED TEST " + num);
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + actual);
        }
        System.out.println();
    }

    // print the given maze
    // pre: none
    private static void printMaze(char[][] maze) {
        if (maze == null) {
            System.out.println("NO MAZE GIVEN");
        } else {
            for (char[] row : maze) {
                for (char c : row) {
                    System.out.print(c);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    // generate a char[][] given the raw string
    // pre: rawMaze != null, rawMaze.length() % rows == 0
    private static char[][] makeMaze(String rawMaze, int rows) {
        if (rawMaze == null || rawMaze.length() % rows != 0) {
            throw new IllegalArgumentException("Violation of precondition in makeMaze."
                    + "Either raw data is null or left over values: ");
        }
        int cols = rawMaze.length() / rows;
        char[][] result = new char[rows][cols];
        int rawIndex = 0;
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = rawMaze.charAt(rawIndex);
                rawIndex++;
            }
        }
        return result;
    }

    // Test the Sierpinski carpet method.
    private static void doCarpetTest() {
        Recursive.drawCarpet(729, 4);
        Recursive.drawCarpet(729, 1);
    }

    private static void doFairTeamsTests() {
        int[] abilities = new int[] { 5, -5, 7, -6 };
        showFairTeamsResults(Recursive.minDifference(2, abilities), 1, 1);

        abilities = new int[] { 1, 2, 3, 4, 5 };
        showFairTeamsResults(Recursive.minDifference(3, abilities), 0, 2);
    }

    // Show the results of a fair teams test by comparing actual and expected
    // result.
    private static void showFairTeamsResults(int actual, int expected, int testNum) {
        if (actual == expected) {
            System.out.println("Test " + testNum + " passed. min difference.");
        } else {
            System.out.println("Test " + testNum + " failed. min difference.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + actual);
        }
    }

    private static void doFlowOffMapTests() {
        int testNum = 1;
        int[][] world = { { 10, 10, 10, 10 },
                { 10, 10, 10, 10 },
                { 10, 9, 10, 10 },
                { 10, 8, 10, 10 } };

        // Test case 1
        doOneFlowTest(world, 1, 1, true, testNum++);

        // Test case 2
        doOneFlowTest(world, 1, 2, false, testNum++);
    }

    private static void doOneFlowTest(int[][] world, int r, int c,
            boolean expected, int testNum) {

        System.out.println("Can Flow Off Map Test Number " + testNum);
        boolean actual = Recursive.canFlowOffMap(world, r, c);
        System.out.println("Start location = " + r + ", " + c);
        System.out.println("Expected result = " + expected + " actual result = " + actual);
        if (expected == actual) {
            System.out.println("passed test " + testNum + " can flow off map.");
        } else {
            System.out.println("FAILED TEST " + testNum + " can flow off map.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + actual);
        }
        System.out.println();
    }

    private static void doListMnemonicsTests() {
        ArrayList<String> mnemonics = Recursive.listMnemonics("1");
        ArrayList<String> expected = new ArrayList<>();

        // Test case 1
        mnemonics = Recursive.listMnemonics("33");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("DD");
        expected.add("DE");
        expected.add("DF");
        expected.add("ED");
        expected.add("EE");
        expected.add("EF");
        expected.add("FD");
        expected.add("FE");
        expected.add("FF");
        Collections.sort(expected);
        if (mnemonics.equals(expected)) {
            System.out.println("Test 1 passed. Phone mnemonics.");
        } else {
            System.out.println("Test 1 failed. Phone mnemonics.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + mnemonics);
        }

        // Test case 2
        mnemonics = Recursive.listMnemonics("89");
        expected.clear();
        expected.add("TW");
        expected.add("TX");
        expected.add("TY");
        expected.add("TZ");
        expected.add("UW");
        expected.add("UX");
        expected.add("UY");
        expected.add("UZ");
        expected.add("VW");
        expected.add("VX");
        expected.add("VY");
        expected.add("VZ");
        Collections.sort(expected);
        if (mnemonics.equals(expected)) {
            System.out.println("Test 2 passed. Phone mnemonics.");
        } else {
            System.out.println("Test 2 failed. Phone mnemonics.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + mnemonics);
        }
        System.out.println();

    }

    private static void doNextIsDoubleTests() {

        // Test case 1
        int[] numsForDouble = { 3, 6, 12, 24, 48, 96 };
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 5;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 1 passed. next is double.");
        } else {
            System.out.println("Test 1 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        // Test case 2
        numsForDouble = new int[] { 3, 6, 12, 24, 48, 69 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 4;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 2 passed. next is double.");
        } else {
            System.out.println("Test 2 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        System.out.println();
    }

    private static void doReverseTests() {

        // Test case 1
        String actualRev = Recursive.revString("");
        String expectedRev = "";
        if (actualRev.equals(expectedRev)) {
            System.out.println("Test 1 passed. reverse string.");
        } else {
            System.out.println("Test 1 failed. reverse string. expected: " +
                    expectedRev + ", actual: " + actualRev);
        }

        // Test case 2
        actualRev = Recursive.revString("Truvia 4");
        expectedRev = "4 aivurT";
        if (actualRev.equals(expectedRev)) {
            System.out.println("Test 2 passed. reverse string.");
        } else {
            System.out.println("Test 2 failed. reverse string. expected: "
                    + expectedRev + ", actual: " + actualRev);
        }

        System.out.println();
    }

    private static void doBinaryTests() {

        // Test case 1
        String actualBinary = Recursive.getBinary(69);
        String expectedBinary = "1000101";
        if (actualBinary.equals(expectedBinary)) {
            System.out.println("Test 1 passed. get binary.");
        } else {
            System.out.println("Test 1 failed. get binary. expected: "
                    + expectedBinary + ", actual: " + actualBinary);
        }

        // Test case 2
        actualBinary = Recursive.getBinary(-8);
        expectedBinary = "-1000";
        if (actualBinary.equals(expectedBinary)) {
            System.out.println("Test 2 passed. get binary.");
        } else {
            System.out.println("Test 2 failed. get binary. expected: "
                    + expectedBinary + ", actual: " + actualBinary);
        }

        System.out.println();
    }

}