import java.util.Arrays;

// MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:
*
*  On my honor, Brian Nguyen, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: bn6652
*  email address: briannguyen@utexas.edu
*  Unique section number: 52620
*  Number of slip days I am using: 0
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {

    // instance variable (deep copy) of original matrix OR newly constructed from parameters
    private int[][] data;

    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        // same number of rows and columns
        data = new int[mat.length][mat[0].length];
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                data[row][col] = mat[row][col];
            }
        }
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the initialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns.
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Rows or columns cannot be less than 0.");
        }
        // same number of rows and columns
        data = new int[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                data[row][col] = initialVal;
            }
        }
    }


    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return data.length;
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns() {
        return data[0].length;
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumColumns()) {
            throw new IllegalArgumentException("Desired row or col cannot be negative or " +
                    "equal to/greater than the number of rows or cols in the matrix.");
        }
        return data[row][col];
    }


    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns
     * in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide) {
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
                rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("rightHandSide matrix must not be null and have " +
                    "the same number of rows and cols as the matrix we are adding it to.");
        }
        // rows and cols won't change so we construct an empty matrix of same size
        MathMatrix resultMat = new MathMatrix(getNumRows(), getNumColumns(), 0);
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                // want to avoid changing deep copy and rightHand matrix, so add into
                // the new one
                resultMat.data[r][c] += data[r][c] + rightHandSide.data[r][c];
            }
        }
        return resultMat;
    }


    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide
     * from this MathMatrix. The number of rows in the returned MathMatrix is equal to the number
     * of rows in this MathMatrix.The number of columns in the returned MathMatrix is equal to
     * the number of columns in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide) {
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
                rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("rightHandSide matrix must not be null and have " +
                    "the same number of rows and cols as the matrix we are subtracting it from.");
        }
        MathMatrix resultMat = new MathMatrix(getNumRows(), getNumColumns(), 0);
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                // same process as addition but subtract this time
                resultMat.data[r][c] += data[r][c] - rightHandSide.data[r][c];
            }
        }
        return resultMat;
    }


    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide != null, rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying 
     * this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows
     * in this MathMatrix. The number of columns in the returned MathMatrix is equal to the number
     * of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide) {
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("rightHandSide matrix must not be null and " +
                    "its rows must equal to columns in left matrix.");
        }
        MathMatrix resultMat = new MathMatrix(getNumRows(), rightHandSide.getNumColumns(), 0);
        // outermost loop is responsible for changing the row when looking at the left matrix
        for (int rowA = 0; rowA < getNumRows(); rowA++) {
            // inner loop changes the column when looking at right matrix
            for (int colB = 0; colB < rightHandSide.getNumColumns(); colB++) {
                // innermost loop moves the cell left-right in left matrix
                // and up-down in right matrix
                for (int rowOrCol = 0; rowOrCol < getNumColumns(); rowOrCol++) {
                    resultMat.data[rowA][colB] +=
                        data[rowA][rowOrCol] * rightHandSide.data[rowOrCol][colB];
                }
            }
        }
        return resultMat;
    }


    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix
     * multiplied by factor.
     * In other words after this method has been called
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {
        // construct new matrix to hold new values
        MathMatrix tempMat = new MathMatrix(getNumRows(), getNumColumns(), 0);
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                tempMat.data[r][c] += data[r][c] * factor;
            }
        }
        return tempMat;
    }


    /**
     * accessor: get a transpose of this MathMatrix.
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        // construct new matrix to transpose columns and rows
        MathMatrix tempMat = new MathMatrix(getNumColumns(), getNumRows(), 0);
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                // switching index of rows and columns instead of only copying
                tempMat.data[c][r] = data[r][c];
            }
        }
        return tempMat;
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide) {
        /* CS314 Students. The following is standard equals
         * method code. We will learn about in the coming weeks.
         *
         * We use getClass instead of instanceof because we only want a MathMatrix to equal
         * another MathMatrix as opposed to any possible sub classes. We would
         * use instance of if we were implementing am interface and wanted to equal
         * other objects that are instances of that interface but not necessarily
         * MathMatrix objects.
         */
        if (rightHandSide == null || this.getClass() != rightHandSide.getClass()) {
            return false;
        }
        // We know rightHandSide refers to a non-null MathMatrix object, safe to cast.
        MathMatrix otherMathMatrix = (MathMatrix) rightHandSide;
        // Now we can access the private instance variables of otherMathMatrix
        // and / or call MathMatrix methods on otherMathMatrix.
        if (otherMathMatrix.getNumRows() != getNumRows() ||
                otherMathMatrix.getNumColumns() != getNumColumns()) {
            return false;
        }
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                if (data[r][c] != otherMathMatrix.data[r][c]) {
                    return false;
                }
            }
        }
        return true; // CS314 students, alter as necessary
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix.
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
    public String toString() {
        // initialize max variable to determine spaces needed later
        int longestString = 0;
        StringBuilder matrix = new StringBuilder();
        for (int r = 0; r < data.length; r++) {
            for (int c = 0; c < data[0].length; c++) {
                int intLength = ("" + data[r][c]).length();
                if (intLength > longestString) {
                    // now we can properly format string output
                    longestString = intLength;
                }
            }
        }
        // add 1 because spacing should have at least one space between each other
        longestString += 1;
        for (int r = 0; r < data.length; r++) {
            // similar to fencepost problem, need to add stuff to front and end of string
            StringBuilder row = new StringBuilder("|");
            for (int c = 0; c < data[0].length; c++) {
                // formats the integers with proper width and spacing
                row.append(String.format("%" + longestString + "d", data[r][c]));
            }
            row.append("|\n");
            matrix.append(row);
        }
        return matrix.toString();
    }


    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise.
     */
    public boolean isUpperTriangular() {
        if (getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Matrix must be a square.");
        }
        if (getNumRows() == 1) {
            // 1 by 1 matrices are automatically upper triangular
            return true;
        } else {
            int sum = 0;
            // start at row 1 because we are not including any values on the diagonal
            for (int r = 1; r < data.length; r++) {
                // row number determines how many values we look at on that row
                // (first row is 1 value, second row is 2 values)
                for (int c = 0; c < r; c++) {
                    sum += data[r][c];
                }
            }
            return sum == 0;
        }
    }


    // method to ensure mat is rectangular. It is public so that
    // tester classes can use it.
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}
