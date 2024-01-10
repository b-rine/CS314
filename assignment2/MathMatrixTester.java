import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *  Grader name: Sai Tanuj
 *  Number of slip days I am using: 0
 */


/* CS314 Students. Put your experiment results and
 * answers to questions here.
 *
 * Experiment 1:
 * I created two matrices both with 1000 rows by 1000 columns. The total time it took to do
 * 1000 add operations was 1.7785 seconds. At 2000 rows by 2000 columns, the total time elapsed
 * was 4.6704 seconds. At 4000 rows by 4000 columns, the total time elapsed was 16.6296 seconds.
 *
 * Experiment 2:
 * Both matrices were 250 rows by 250 columns for the first repetition and the total time elapsed
 * was 1.1470 seconds. At 500 rows by 500 columns, the total time elapsed was 18.4525 seconds.
 * At 1000 rows by 1000 columns, the total time elapsed was 210.0690 seconds.
 *
 * Question 1:
 * I would expect the total time to be roughly 256 seconds long because the time was roughly
 * squared after the first two times it was doubled.
 *
 * Question 2:
 * The Big O of the add operation is N^2. The timing data does support it because each time the
 * rows and columns are doubled increases the total time by a power of 2.
 *
 * Question 3:
 * I would expect the total time to be about 1000 seconds long because it increased by about a
 * factor of 10 between the second and third doubling.
 *
 * Question 4:
 * The Big O of the multiply operation is N^3. The timing data supports this because solving for
 * x in each experiment (x^3, (2x)^3, (4x)^3) gives us a value around 1.3 for all three.
 *
 * Question 5:
 * The largest matrix I could make before running out of heap memory was 8000 rows by 8000 columns.
 * This means that the matrix had 64,000,000 cells and when multiplied by 4 bytes, gives us
 * 256,000,000 bytes, which is 256 megabytes. My program used about 68% of my computer's RAM before
 * crashing. However, that means that the computer allocates about 2000 megabytes for the program
 * before it crashes.
 *
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[][] data1 = { {1, 2, 3}, {4, 5, 6} };
        int[][] data2 = { {3, 2, 1}, {1, 2, 3} };
        int[][] data3 = { {3, 2, 1, 0}, {0, 1, 2, 3}, {3, 2, 1, 0} };
        int[][] data4 = { {1, 2, 1, 3}, {4, 1, 2, 3}, {3, 2, 4, 2} };
        int[][] data5 = { {3, -3}, {-2, 2}, {4, 1} };
        int[][] data6 = { {1, 2, 3}, {4, 5, 6} };
        int[][] e1;

        // test 1 - 2, constructor with parameters
        MathMatrix mat1 = new MathMatrix(3, 1, -1);
        e1 = new int[][] { {-1}, {-1}, {-1} };
        printTestResult(get2DArray(mat1), e1, 1,
                "constructor with size and initial val specified");

        mat1 = new MathMatrix(4, 2, 4);
        e1 = new int[][] { {4, 4}, {4, 4}, {4, 4}, {4, 4} };
        printTestResult(get2DArray(mat1), e1, 2,
                "constructor with size and initial val specified");

        // test 3 - 4, int[][] constructor (deep copy)
        mat1 = new MathMatrix(data1);
        data1[0][0] = 10;
        e1 = new int[][] { {10, 2, 3}, {4, 5, 6} };
        printTestResult(data1, e1, 3,
                "constructor with one parameter of type int[][]");

        mat1 = new MathMatrix(data2);
        data2[1][2] = 6;
        e1 = new int[][] { {3, 2, 1}, {1, 2, 6} };
        printTestResult(data2, e1, 4,
                "constructor with one parameter of type int[][]");

        // test 5 - 6, getNumRows()
        data1[0][0] = 1;
        data2[1][2] = 3;
        mat1 = new MathMatrix(data3);
        int expected = 3;
        if (mat1.getNumRows() == expected) {
            System.out.println(("Test number 5 tests the getNumRows(). The test passed."));
        } else {
            System.out.println("Test number 5 tests the getNumRows(). The test failed.");
        }

        mat1 = new MathMatrix(data2);
        expected = 2;
        if (mat1.getNumRows() == expected) {
            System.out.println(("Test number 6 tests the getNumRows(). The test passed."));
        } else {
            System.out.println("Test number 6 tests the getNumRows(). The test failed.");
        }

        // test 7 - 8, getNumColumns()
        mat1 = new MathMatrix(data3);
        expected = 4;
        if (mat1.getNumColumns() == expected) {
            System.out.println(("Test number 7 tests the getNumColumns(). The test passed."));
        } else {
            System.out.println("Test number 7 tests the getNumColumns(). The test failed.");
        }

        mat1 = new MathMatrix(data2);
        expected = 3;
        if (mat1.getNumColumns() == expected) {
            System.out.println(("Test number 8 tests the getNumColumns(). The test passed."));
        } else {
            System.out.println("Test number 8 tests the getNumColumns(). The test failed.");
        }

        // test 9 - 10, add()
        mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);
        MathMatrix mat3 = mat1.add(mat2);
        e1 = new int[][] { {4, 4, 4}, {5, 7, 9} };
        printTestResult(get2DArray(mat3), e1, 9, "add method. Testing mat3 correct result");

        mat1 = new MathMatrix(data3);
        mat2 = new MathMatrix(data4);
        mat3 = mat1.add(mat2);
        e1 = new int[][] { {4, 4, 2, 3}, {4, 2, 4, 6}, {6, 4, 5, 2} };
        printTestResult(get2DArray(mat3), e1, 10, "add method. Testing mat3 correct result");

        // test 11 - 12, subtract()
        mat1 = new MathMatrix(data2);
        mat2 = new MathMatrix(data1);
        mat3 = mat1.subtract(mat2);
        e1 = new int[][] { {2, 0, -2}, {-3, -3, -3} };
        printTestResult(get2DArray(mat3), e1, 11, "subtract method. Testing mat3 correct result");

        mat1 = new MathMatrix(data4);
        mat2 = new MathMatrix(data3);
        mat3 = mat1.subtract(mat2);
        e1 = new int[][] { {-2, 0, 0, 3}, {4, 0, 0, 0}, {0, 0, 3, 2} };
        printTestResult(get2DArray(mat3), e1, 12, "subtract method. Testing mat3 correct result");

        // test 13 - 14, multiply()
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data5);
        mat3 = mat1.multiply(mat2);
        e1 = new int[][] { {11, 4}, {26, 4} };
        printTestResult(get2DArray(mat3), e1, 13, "multiply method. Testing mat3 correct result");

        mat1 = new MathMatrix(data5);
        mat2 = new MathMatrix(data2);
        mat3 = mat1.multiply(mat2);
        e1 = new int[][] { {6, 0, -6}, {-4, 0, 4}, {13, 10, 7} };
        printTestResult(get2DArray(mat3), e1, 14, "multiply method. Testing mat3 correct result");

        // test 15 - 16, getScaledMatrix()
        mat1 = new MathMatrix(data1);
        mat2 = mat1.getScaledMatrix(3);
        e1 = new int[][] { {3, 6, 9}, {12, 15, 18} };
        printTestResult(get2DArray(mat2), e1, 15, "scale method. Testing mat2 correct result");

        mat1 = new MathMatrix(data4);
        mat2 = mat1.getScaledMatrix(2);
        e1 = new int[][] { {2, 4, 2, 6}, {8, 2, 4, 6}, {6, 4, 8, 4} };
        printTestResult(get2DArray(mat2), e1, 16, "scale method. Testing mat2 correct result");

        // test 17 - 18, getTranspose()
        mat1 = new MathMatrix(data1);
        mat2 = mat1.getTranspose();
        e1 = new int[][] { {1, 4}, {2, 5}, {3, 6} };
        printTestResult(get2DArray(mat2), e1, 17, "transpose method. Testing mat2 correct result");

        mat1 = new MathMatrix(data5);
        mat2 = mat1.getTranspose();
        e1 = new int[][] { {3, -2, 4}, {-3, 2, 1} };
        printTestResult(get2DArray(mat2), e1, 18, "transpose method. Testing mat2 correct result");

        // test 19 - 20, toString()
        mat1 = new MathMatrix(data1);
        String stringMat = "| 1 2 3|\n| 4 5 6|\n";
        if (mat1.toString().equals(stringMat)){
            System.out.println("Test number 19 tests the toString method. The test passed.");
        } else {
            System.out.println("Test number 19 tests the toString method. The test failed.");
        }

        e1 = new int[][] { {-100, 3}, {10, 4} };
        mat1 = new MathMatrix(e1);
        stringMat = "| -100    3|\n|   10    4|\n";
        if (mat1.toString().equals(stringMat)){
            System.out.println("Test number 20 tests the toString method. The test passed.");
        } else {
            System.out.println("Test number 20 tests the toString method. The test failed.");
        }

        // test 21 - 22, isUpperTriangular()
        e1 = new int[][] { {1, 2, 3}, {0, 6, 4}, {0, 0, 9} };
        mat1 = new MathMatrix(e1);
        if (mat1.isUpperTriangular()) {
            System.out.println("Test number 21 tests the upperTriangular method. The test passed.");
        } else {
            System.out.println("Test number 21 tests the upperTriangular method. Tes test failed.");
        }

        e1 = new int[][] { {5, 2, 0}, {3, 2, 4}, {0, 2, 0} };
        mat1 = new MathMatrix(e1);
        if (!mat1.isUpperTriangular()) {
            System.out.println("Test number 22 tests the upperTriangular method. The test passed.");
        } else {
            System.out.println("Test number 22 tests the upperTriangular method. Tes test failed.");
        }

        // test 23 - 24, getVal()
        mat1 = new MathMatrix(data1);
        int expectInt = 1;
        if (mat1.getVal(0, 0) == expectInt) {
            System.out.println("Test number 23 tests the getVal method. The test passed.");
        } else {
            System.out.println("Test number 23 tests the getVal method. The test failed.");
        }

        expectInt = 6;
        if (mat1.getVal(1, 2) == expectInt) {
            System.out.println("Test number 24 tests the getVal method. The test passed.");
        }  else {
            System.out.println("Test number 24 tests the getVal method. The test failed.");
        }

        // test 25 - 26, equals()
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data6);
        if (mat1.equals(mat2)) {
            System.out.println("Test number 25 tests the equals method. The test passed.");
        }  else {
            System.out.println("Test number 25 tests the equals method. The test failed.");
        }

        mat2 = new MathMatrix(data2);
        if (!mat1.equals(mat2)) {
            System.out.println("Test number 26 tests the equals method. The test passed.");
        }  else {
            System.out.println("Test number 26 tests the equals method. The test failed.");
        }

        // experiment code
//        Random randNumGen = new Random(1);
//        int rows = 500;
//        int cols = 500;
//        int limit = 1000;
//        double totalSec = 0;
//        mat1 = createMat(randNumGen, rows, cols, limit);
//        mat2 = createMat(randNumGen, rows, cols, limit);
//        Stopwatch sw = new Stopwatch();
//        for (int repetition = 0; repetition < 100; repetition++) {
//            sw.start();
//            mat1.multiply(mat2);
//            sw.stop();
//            totalSec += sw.time();
//        }
//        System.out.println("Total seconds: " + totalSec);
        // this section is for creating big matrices
//        while (true) {
//            System.out.println("Rows: " + rows + ", cols: " + cols);
//            // doubling size
//            rows *= 2;
//            cols *= 2;
//            mat1 = createMat(randNumGen, rows, cols, limit);
//        }
    }

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if (mat == null) {
            throw new IllegalArgumentException("mat may not be null");
        }
        int result = 0;
        final int ROWS =  mat.getNumRows();
        final int COLS = mat.getNumColumns();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
            }
        }
        return result;
    }

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    public static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {

        if (randNumGen == null) {
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        } else if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
                    "rows: " + rows + ", cols: " + cols);
        }

        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
            }
        }

        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum,
            String testingWhat) {
        System.out.print("Test number " + testNum + " tests the " + testingWhat +". The test ");
        String result = equals(data1, data2) ? "passed." : "failed.";
        System.out.println(result);
    }

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with
    // the same elements
    private static int[][] get2DArray(MathMatrix m) {
        //check precondition
        if  ((m == null) || (m.getNumRows() == 0)
                || (m.getNumColumns() == 0)) {
            throw new IllegalArgumentException("Violation of precondition: get2DArray");
        }

        int[][] result = new int[m.getNumRows()][m.getNumColumns()];
        for(int r = 0; r < result.length; r++) {
            for(int c = 0; c < result[0].length; c++) {
                result[r][c] = m.getVal(r,c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
        //check precondition
        if((data1 == null) || (data1.length == 0)
                || (data1[0].length == 0) || !rectangularMatrix(data1)
                ||  (data2 == null) || (data2.length == 0)
                || (data2[0].length == 0) || !rectangularMatrix(data2)) {
            throw new IllegalArgumentException( "Violation of precondition: " +
                    "equals check on 2d arrays of ints");
        }
        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while (result && row < data1.length) {
            int col = 0;
            while (result && col < data1[0].length) {
                result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix( int[][] mat ) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null"
                    + " and must be at least 1 by 1");
        }
        return MathMatrix.rectangularMatrix(mat);
    }
}

