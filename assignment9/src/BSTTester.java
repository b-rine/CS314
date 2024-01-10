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
 *  Number of slip days I am using: 0
 */

/* EXPERIMENT RESULTS:
 *
 * Creating an empty binary search tree with 1000 random Integers took 0.005172 seconds.
 * Its height is 19 and the size is 1000.
 *
 * After running the experiment 10 times, the average time to do insertions was 0.000424 seconds,
 * the average height was 20.2 and the average size was 1000.0.
 *
 * Running experiment 10 times for N value of: 2000
 * Average time for insertion: 4.9686E-4
 * Average height: 23.9
 * Average size: 2000.0
 *
 * Running experiment 10 times for N value of: 4000
 * Average time for insertion: 7.9051E-4
 * Average height: 26.3
 * Average size: 4000.0
 *
 * Running experiment 10 times for N value of: 8000
 * Average time for insertion: 0.00125614
 * Average height: 29.3
 * Average size: 8000.0
 *
 * Running experiment 10 times for N value of: 16000
 * Average time for insertion: 0.00250184
 * Average height: 33.6
 * Average size: 16000.0
 *
 * Running experiment 10 times for N value of: 32000
 * Average time for insertion: 0.0060508
 * Average height: 34.4
 * Average size: 31999.7
 *
 * Running experiment 10 times for N value of: 64000
 * Average time for insertion: 0.01318125
 * Average height: 37.5
 * Average size: 63999.8
 *
 * Running experiment 10 times for N value of: 128000
 * Average time for insertion: 0.03505713
 * Average height: 40.6
 * Average size: 127998.1
 *
 * Running experiment 10 times for N value of: 256000
 * Average time for insertion: 0.08033672
 * Average height: 43.3
 * Average size: 255991.0
 *
 * Running experiment 10 times for N value of: 512000
 * Average time for insertion: 0.23483318999999997
 * Average height: 45.6
 * Average size: 511969.1
 *
 * Running experiment 10 times for N value of: 1024000
 * Average time for insertion: 0.66546361
 * Average height: 48.7
 * Average size: 1023873.0
 *
 * Using the formula log2(N + 1) - 1, we can find the minimum height for binary search trees.
 * For 1000, height is 10.
 * For 2000, height is 11.
 * For 4000, height is 12.
 * For 8000, height is 13.
 * For 16000, height is 14.
 * For 32000, height is 15.
 * For 64000, height is 16.
 * For 128000, height is 17.
 * For 256000, height is 18.
 * For 512000, height is 19.
 * For 1024000, height is 20.
 *
 * Experiments with TreeSet:
 *
 * Running experiment 10 times for N value of: 1000
 * Average time for insertion: 6.0991E-4
 *
 * Running experiment 10 times for N value of: 2000
 * Average time for insertion: 5.2794E-4
 *
 * Running experiment 10 times for N value of: 4000
 * Average time for insertion: 7.5827E-4
 *
 * Running experiment 10 times for N value of: 8000
 * Average time for insertion: 0.001111
 *
 * Running experiment 10 times for N value of: 16000
 * Average time for insertion: 0.002436
 *
 * Running experiment 10 times for N value of: 32000
 * Average time for insertion: 0.005874
 *
 * Running experiment 10 times for N value of: 64000
 * Average time for insertion: 0.013128
 *
 * Running experiment 10 times for N value of: 128000
 * Average time for insertion: 0.039047
 *
 * Running experiment 10 times for N value of: 256000
 * Average time for insertion: 0.106678
 *
 * Running experiment 10 times for N value of: 512000
 * Average time for insertion: 0.253992
 *
 * Running experiment 10 times for N value of: 1024000
 * Average time for insertion: 0.667548
 *
 * When comparing these TreeSet times to BinarySearchTree, they are roughly equal.
 *
 * For a binary search tree with 1000 sorted integers:
 * Average time: 0.005417
 * Average height: 999
 * Average size: 1000
 *
 * For a binary search tree with 2000 sorted integers:
 * Average time: 0.00604396
 * Average height: 1999
 * Average size: 2000
 *
 * For a binary search tree with 4000 sorted integers:
 * Average time: 0.01734401
 * Average height: 3999
 * Average size: 4000
 *
 * For a binary search tree with 8000 sorted integers:
 * Average time: 0.07491955999999998
 * Average height: 7999
 * Average size: 8000
 *
 * For a binary search tree with 16000 sorted integers:
 * Average time: 0.3169979
 * Average height: 15999
 * Average size: 16000
 *
 * For a binary search tree with 32000 sorted integers:
 * Average time: 1.26667688
 * Average height: 31999
 * Average size: 32000
 *
 * For a binary search tree with 64000 sorted integers:
 * Average time: 5.23338949
 * Average height: 63999
 * Average size: 64000
 *
 * Based on the results, it looks like the time quadruples each time N is doubled, meaning the
 * insertion method is O(N^2). To add 128,000 sorted integers, I would predict about 20 seconds
 * for its runtime. For 256,000 integers, around 80 seconds. And for 512,000 integers, around
 * 320 seconds.
 *
 * For a TreeSet with 1000 sorted integers:
 * Average time: 9.7341E-4
 *
 * For a TreeSet with 2000 sorted integers:
 * Average time: 3.1615E-4
 *
 * For a TreeSet with 4000 sorted integers:
 * Average time: 6.0164E-4
 *
 * For a TreeSet with 8000 sorted integers:
 * Average time: 7.1031E-4
 *
 * For a TreeSet with 16000 sorted integers:
 * Average time: 0.00150134
 *
 * For a TreeSet with 32000 sorted integers:
 * Average time: 0.00324913
 *
 * For a TreeSet with 64000 sorted integers:
 * Average time: 0.00765198
 *
 * These times are significantly faster than the times for a Binary Search Tree when adding
 * integers in sorted order. This is likely due to the fact that TreeSets use red-black trees
 * as their internal storage, so values are automatically "balanced" with each insertion. So
 * instead of having to go through thousands of values, like in the BST, having a balanced tree
 * makes the search much faster since it reduces the nodes it has to travel.
 *
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * @param args Not used
     */
    public static void main(String[] args) {
        studentTests();
    }

    public static void studentTests() {
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>();
        boolean actual = false;

        // test 1 - add()
        actual = t1.add(1);
        showTestResults(actual == true, 1);

        // test 2 - add()
        t1.add(2);
        showTestResults(t1.size() == 2, 2);

        // test 3 - remove()
        actual = t1.remove(3);
        showTestResults(actual == false, 3);

        // test 4 - remove()
        t1.remove(2);
        showTestResults(t1.size() == 1, 4);

        // test 5 - isPresent()
        actual = t1.isPresent(1);
        showTestResults(actual == true, 5);

        // test 6 - isPresent()
        actual = t1.isPresent(2);
        showTestResults(actual == false, 6);

        // test 7 - size()
        t1.add(2);
        t1.add(3);
        showTestResults(t1.size() == 3, 7);

        // test 8 - size()
        t1.add(4);
        t1.remove(2);
        showTestResults(t1.size() == 3, 8);

        // test 9 - height()
        showTestResults(t1.height() == 2, 9);

        // test 10 - height()
        t1.add(0);
        showTestResults(t1.height() == 2, 10);

        // test 11 - getAll()
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(3);
        expected.add(4);
        showTestResults(t1.getAll().equals(expected) == true, 11);

        // test 12 - getAll()
        expected.remove(3);
        t1.remove(4);
        showTestResults(t1.getAll().equals(expected) == true, 12);

        // test 13 - max()
        showTestResults(t1.max() == 3, 13);

        // test 14 - max()
        t1.add(5);
        showTestResults(t1.max() == 5, 14);

        // test 15 - min()
        showTestResults(t1.min() == 0, 15);

        // test 16 - min()
        t1.add(-1);
        showTestResults(t1.min() == -1, 16);

        // test 17 - iterativeAdd()
        t1 = new BinarySearchTree<>();
        t1.iterativeAdd(1);
        t1.iterativeAdd(2);
        t1.iterativeAdd(2);
        t1.iterativeAdd(1);
        showTestResults(t1.size() == 2, 17);

        // test 18 - iterativeAdd()
        t1.iterativeAdd(3);
        t1.iterativeAdd(4);
        showTestResults(t1.size() == 4, 18);

        // test 19 - get()
        showTestResults(t1.get(3) == 4, 19);

        // test 20 - get()
        showTestResults(t1.get(2) == 3, 20);

        // test 21 - getAllLessThan()
        expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        showTestResults(t1.getAllLessThan(3).equals(expected), 21);

        // test 22 - getAllLessThan()
        expected.add(3);
        expected.add(4);
        showTestResults(t1.getAllLessThan(5).equals(expected), 22);

        // test 23 - getAllGreaterThan()
        expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);
        showTestResults(t1.getAllGreaterThan(2).equals(expected), 23);

        // test 24 - getAllGreaterThan()
        expected.add(1);
        expected.add(2);
        Collections.sort(expected);
        showTestResults(t1.getAllGreaterThan(0).equals(expected), 24);

        // test 25 - numNodesAtDepth()
        t1 = new BinarySearchTree<>();
        t1.add(25);
        t1.add(20);
        t1.add(15);
        t1.add(22);
        t1.add(40);
        t1.add(35);
        t1.add(45);
        showTestResults(t1.numNodesAtDepth(2) == 4, 25);

        // test 26 - numNodesAtDepth()
        showTestResults(t1.numNodesAtDepth(1) == 2, 26);
    }

    private static void showTestResults(boolean passed, int testNum) {
        if (passed) {
            System.out.println("Test " + testNum + " passed.");
        } else {
            System.out.println("TEST " + testNum + " FAILED.");
        }
    }
}
