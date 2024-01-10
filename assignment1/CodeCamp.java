//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 *
 *  replace <NAME> with your name.
 *
 *  On my honor, Brian Nguyen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Brian Nguyen
 *  email address: briannguyen@utexas.edu
 *  UTEID: bn6652
 *  Section 5 digit ID: 52620
 *  Grader name: Sai Tanuj
 *  Number of slip days used on this assignment: 0
 */

public class CodeCamp {

    /**
     * Determine the Hamming distance between two arrays of ints.
     * Neither the parameter <tt>aData</tt> or
     * <tt>bData</tt> are altered as a result of this method.<br>
     * @param aData != null, aData.length == aData.length
     * @param bData != null
     * @return the Hamming Distance between the two arrays of ints.
     */
    public static int hammingDistance(int[] aData, int[] bData) {
        // check preconditions
        if (aData == null || bData == null || aData.length != bData.length) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "hammingDistance. neither parameter may equal null, arrays" +
                    " must be equal length.");
        }
        // initialize a variable to track number of differences
        int hamDist = 0;
        for (int i = 0; i < aData.length; i++) {
            // at the same index, increment if different
            if (aData[i] != bData[i]) {
                hamDist++;
            }
        }
        return hamDist;
    }


    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>aData</tt> or
     * the parameter <tt>bData</tt> are altered as a result of this method.<br>
     * @param aData != null
     * @param bData != null
     * @return <tt>true</tt> if aData is a permutation of bData,
     * <tt>false</tt> otherwise
     *
     */
    public static boolean isPermutation(int[] aData, int[] bData) {
        // check preconditions
        if (aData == null || bData == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isPermutation. neither parameter may equal null.");
        }
        // arrays must be same length to be permutation
        if (aData.length != bData.length) {
            return false;
        }
        // only need to look at values from one array to compare
        for (int number : aData) {
            int numFreqA = countValues(number, aData);
            int numFreqB = countValues(number, bData);
            // any difference in frequency immediately means false
            if (numFreqA != numFreqB) {
                return false;
            }
        }
        return true;
    }

    private static int countValues(int value, int[] data) {
        int numFreq = 0;
        // look through array to track frequency
        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                numFreq += 1;
            }
        }
        return numFreq;
    }


    /**
     * Determine the index of the String that
     * has the largest number of vowels.
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o',
     * 'U', and 'u'</tt>.
     * The parameter <tt>arrayOfStrings</tt> is not altered as a result of this method.
     * <p>pre: <tt>arrayOfStrings != null</tt>, <tt>arrayOfStrings.length > 0</tt>,
     * there is an least 1 non null element in arrayOfStrings.
     * <p>post: return the index of the non-null element in arrayOfStrings that has the
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero.
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param arrayOfStrings the array to check
     * @return the index of the non-null element in arrayOfStrings that has
     * the largest number of vowels.
     */
    public static int mostVowels(String[] arrayOfStrings) {
        // check preconditions
        if (arrayOfStrings == null || arrayOfStrings.length == 0
        		|| !atLeastOneNonNull(arrayOfStrings)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "mostVowels. parameter may not equal null and must contain " +
                    "at least one none null value.");
        }
        // array to track vowels in each string
        int[] numVowels = new int[arrayOfStrings.length];
        int mostVowels = -1;
        int mostIndex = -1;
        vowelCount(arrayOfStrings, numVowels);
        // look at every string and check if it has the most vowels
        for (int i = 0; i < numVowels.length; i++) {
            if (numVowels[i] > mostVowels) {
                mostVowels = numVowels[i];
                mostIndex = i;
            }
        }
        return mostIndex;
    }

    public static void vowelCount(String[] strings, int[] numVowels) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        // looking through the array of strings
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                numVowels[i] = -1;
            } else if (strings[i].isEmpty()) {
                numVowels[i] = 0;
            } else {
                // convert to lowercase for easy comparison
                String currString = strings[i].toLowerCase();
                for (int j = 0; j < currString.length(); j++) {
                    for (char vowel : vowels) {
                        if (currString.charAt(j) == vowel) {
                            numVowels[i]++;
                        }
                    }
                }
            }
        }
    }

    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people.
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiment.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday
     * after running the simulation.
     */
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople +
                    ", numDaysInYear: " + numDaysInYear);
        }
        // start birthday index at 1 to be more reflective of an actual year
        int[] birthdays = new int[numPeople];
        int[] numOfBirthdays = new int[numDaysInYear + 1];
        int pairs = 0;
        for (int i = 0; i < birthdays.length; i++) {
            birthdays[i] = (int) (Math.random() * numDaysInYear) + 1;
        }
        // one person automatically means no pairs
        if (numPeople < 2) {
            return 0;
        } else {
            // tallying up birthdays
            for (int birthday : birthdays) {
                numOfBirthdays[birthday]++;
            }
            // can use the formula for combination: n! / ((n - r)! * r!),
            for (int number : numOfBirthdays) {
                if (number > 1) {
                    // since we only care about pairs: r = 2 which simplifies to this equation
                    pairs += number * (number - 1) / 2;
                }
            }
        }
        return pairs;
    }


    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board)
                || !onlyContains(board, validChars)) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "queensAreSafe. The board may not be null, must be square, " +
                    "and may only contain 'q's and '.'s");
        }
        // look through the entire board, only start checking upon seeing a queen
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] == 'q') {
                    if (!checkStraights(board, row, col)) {
                        return false;
                    }
                    if (!checkDiagonals(board, row, col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkStraights(char[][] board, int row, int col) {
        // checks up and down
        for (int rowSpace = 0; rowSpace < board.length; rowSpace++) {
            if (rowSpace != row && board[rowSpace][col] == 'q') {
                return false;
            }
        }
        // checks left and right
        for (int colSpace = 0; colSpace < board.length; colSpace++) {
            if (colSpace != col && board[row][colSpace] == 'q') {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonals(char[][] board, int row, int col) {
        int backRow = row;
        int backCol = col;
        // checking backward slash diagonal first so position is set at top left
        while (backRow > 0 && backCol > 0) {
            backRow--;
            backCol--;
        }
        // going down the entire diagonal
        while (backRow < board.length - 1 && backCol < board.length - 1) {
            if (backRow != row && backCol != col && board[backRow][backCol] == 'q') {
                return false;
            }
            backRow++;
            backCol++;
        }
        // checking forward slash next so position is set at bottom left
        int frontRow = row;
        int frontCol = col;
        while (frontRow < board.length - 1 && frontCol > 0) {
            frontRow++;
            frontCol--;
        }
        // going up right of the diagonal
        while (frontRow > 0 && frontCol < board.length - 1) {
            if (frontRow != row && frontCol != col && board[frontRow][frontCol] == 'q') {
                return false;
            }
            frontRow--;
            frontCol++;
        }
        return true;
    }


    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1.
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contiguous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contiguous sub rectangle
     * in <tt>city</tt>.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0
                || !isRectangular(city) ) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "getValueOfMostValuablePlot. The parameter may not be null," +
                    " must value at least one row and at least" +
                    " one column, and must be rectangular.");
        }
        int maxValue = -1000;
        // finding all possible upper left corners
        for (int upCorRow = 0; upCorRow < city.length; upCorRow++) {
            for (int upCorCol = 0; upCorCol < city[upCorRow].length; upCorCol++) {
                // for each upper left corner, find all possible bottom right corners
                for (int botCorRow = upCorRow; botCorRow < city.length; botCorRow++) {
                    for (int botCorCol = upCorCol;
                        botCorCol < city[botCorRow].length; botCorCol++) {
                        // generate possible subplots, separate by rows and columns
                        int sum = 0;
                        for (int subRow = upCorRow; subRow <= botCorRow; subRow++) {
                            for (int subCol = upCorCol; subCol <= botCorCol; subCol++) {
                                // adding values in subplot
                                sum += city[subRow][subCol];
                            }
                        }
                        if (sum > maxValue) {
                            maxValue = sum;
                        }
                    }
                }
            }
        }
        return maxValue;
    }


    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    // CS314 STUDENTS: Put your birthday problem experiment code here:
/*  Experiment 1: 1 Million experiments, 182 people
    public static void sharedBirthdays() {
        int experiment = 1;
        int numPeople = 182;
        int numDaysInYear = 365;
        int totalPairs = 0;
        // running experiment 1 million times
        while (experiment <= 1000000) {
            int[] birthdays = new int[numPeople];
            int[] numOfBirthdays = new int[numDaysInYear + 1];
            int pairs = 0;
            for (int i = 0; i < birthdays.length; i++) {
                birthdays[i] = (int) (Math.random() * numDaysInYear) + 1;
            }
            for (int birthday : birthdays) {
                numOfBirthdays[birthday]++;
            }
            for (int number : numOfBirthdays) {
                if (number > 1) {
                    pairs += number * (number - 1) / 2;
                }
            }
            System.out.println("Experiment " + experiment + ": " + pairs + " pairs");
            // tracking total pairs to find average
            totalPairs += pairs;
            experiment += 1;
        }
        System.out.println();
        // experiment variable will equal 1,000,001 after the loop, so subtract 1
        double avgPairs = (double) totalPairs / (experiment - 1);
        System.out.println("Average pairs: " + avgPairs);
    }
*/

/*  Experiment 2: 2 - 100 people, 50000 each
    public static void sharedBirthdays() {
        double[] onePairPercent = new double[99];
        int totalExperiment = 1;
        // running experiment for 2 to 100 people
        for (int numPeople = 2; numPeople < 101; numPeople++) {
            int experiment = 1;
            int numDaysInYear = 365;
            int totalPairs = 0;
            while (experiment <= 50000) {
                int[] birthdays = new int[numPeople];
                int[] numOfBirthdays = new int[numDaysInYear + 1];
                int pairs = 0;
                for (int i = 0; i < birthdays.length; i++) {
                    birthdays[i] = (int) (Math.random() * numDaysInYear) + 1;
                }
                for (int birthday : birthdays) {
                    numOfBirthdays[birthday]++;
                }
                for (int number : numOfBirthdays) {
                    if (number > 1) {
                        pairs += number * (number - 1) / 2;
                    }
                }
                // used this line for debugging and finding errors
                // System.out.println("Experiment " + totalExperiment + ": " + pairs + " pairs");
                experiment += 1;
                if (pairs > 0) {
                    totalPairs += 1;
                }
                totalExperiment += 1;
            }
            // percentage conversion for proper formatting
            onePairPercent[numPeople - 2] = ((double) totalPairs / 50000 * 100);
        }
        for (int i = 2; i < 101; i++) {
            System.out.print("Num people: " + i + ", number of experiments with one or more " +
                    "shared birthday: 50000," + " percentage: ");
            System.out.printf("%.2f\n", onePairPercent[i - 2]);
        }
    }
*/

    /*
     * pre: arrayOfStrings != null
     * post: return true if at least one element in arrayOfStrings is
     * not null, otherwise return false.
     */
    private static boolean atLeastOneNonNull(String[] arrayOfStrings) {
        // check precondition
        if (arrayOfStrings == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "atLeastOneNonNull. parameter may not equal null.");
        }
        boolean foundNonNull = false;
        int i = 0;
        while( !foundNonNull && i < arrayOfStrings.length ) {
            foundNonNull = arrayOfStrings[i] != null;
            i++;
        }
        return foundNonNull;
    }


    /*
     * pre: mat != null
     * post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isSquare. Parameter may not be null.");
        }
        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while (isSquare && row < numRows) {
            isSquare = ( mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }


    /*
     * pre: mat != null, valid != null
     * post: return true if all elements in mat are one of the
     * characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if (mat == null || valid == null) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "onlyContains. Parameters may not be null.");
        }
        String validChars = new String(valid);
        int row = 0;
        boolean onlyContainsValidChars = true;
        while (onlyContainsValidChars && row < mat.length) {
            int col = 0;
            while(onlyContainsValidChars && col < mat[row].length) {
                int indexOfChar = validChars.indexOf(mat[row][col]);
                onlyContainsValidChars = indexOfChar != -1;
                col++;
            }
            row++;
        }
        return onlyContainsValidChars;
    }


    /*
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isRectangular. Parameter may not be null and must contain" +
                    " at least one row.");
        }
        boolean correct = mat[0] != null;
        int row = 0;
        while(correct && row < mat.length) {
            correct = (mat[row] != null)
                    && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }

    // make constructor private so no instances of CodeCamp can not be created
    private CodeCamp() {

    }
}
