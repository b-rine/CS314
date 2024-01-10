/*  Student information for assignment:
 *
 *  On our honor, Siddharth Sundaram and Brian Nguyen,
 *  this programming assignment is our own work
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


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.
     * <br>
     * pre: n != Integer.MIN_VALUE<br>
     * <br>
     * post: Returns a String that represents N in binary.
     * All chars in returned String are '1's or '0's.
     * Most significant digit is at position 0
     * 
     * @param n the base 10 int to convert to base 2
     * @return a String that is a binary representation of the parameter n
     */
    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "getBinary. n cannot equal "
                    + "Integer.MIN_VALUE. n: " + n);
        }

        String res = "";
        boolean neg = false;
        
        // Change negative integers to positive value during recursion
        // then add negative sign at the end
        if (n < 0) {
            n *= -1;
            neg = true;
        }

        // Special case
        if (n == 0) {
            return "0";
        }
        // Base case
        if (n == 1) {
            return "1";
        }
        res += getBinary(n / 2);
        res += n % 2;
        res = neg ? "-" + res : res;
        return res;
    }

    /**
     * Problem 2: reverse a String recursively.<br>
     * pre: stringToRev != null<br>
     * post: returns a String that is the reverse of stringToRev
     * 
     * @param stringToRev the String to reverse.
     * @return a String with the characters in stringToRev
     *         in reverse order.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        // Base Case & Special Case
        if (stringToRev.length() == 1 || stringToRev.length() == 0) {
            return stringToRev;
        }

        String res = "";
        
        // Grab last letter, then update string to remove letter
        res += stringToRev.charAt(stringToRev.length() - 1);
        res += revString(stringToRev.substring(0, stringToRev.length() - 1));

        return res;
    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data
     * that are followed immediately by double the value
     * 
     * @param data The array to search.
     * @return The number of elements in data that
     *         are followed immediately by a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        // Base Case - no more numbers to work with
        if (data.length == 0 || data.length == 1) {
            return 0;
        }
        int[] newData = removeFirstElement(data);

        // Subsequent number is double current, then call on the updated array
        if (data[1] == data[0] * 2) {
            return 1 + nextIsDouble(newData);
        } else {
            return nextIsDouble(newData);
        }
    }

    // Returns an array that is a copy of the input array with the first element
    // removed
    private static int[] removeFirstElement(int[] data) {
        int[] res = new int[data.length - 1];
        for (int i = 1; i < data.length; i++) {
            res[i - 1] = data[i];
        }
        return res;
    }

    /**
     * Problem 4: Find all combinations of mnemonics
     * for the given number.<br>
     * pre: number != null, number.length() > 0,
     * all characters in number are digits<br>
     * post: see tips section of assignment handout
     * 
     * @param number The number to find mnemonics for
     * @return An ArrayList<String> with all possible mnemonics
     *         for the given number
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null || number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }
        ArrayList<String> results = new ArrayList<>(); // to store the mnemonics
        recursiveMnemonics(results, "", number);
        return results;
    }

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used
     * from the original number.
     */
    private static void recursiveMnemonics(ArrayList<String> mnemonics,
            String mnemonicSoFar, String digitsLeft) {

        // Base Case - empty digitsLeft means no combination left to be made so add
        if (digitsLeft.length() == 0) {
            mnemonics.add(mnemonicSoFar);
        } else {
            // Convert digits into their associated set of letters and remove the digit used
            String letters = digitLetters(digitsLeft.charAt(0));
            if (digitsLeft.length() == 1) {
                digitsLeft = "";
            } else {
                digitsLeft = digitsLeft.substring(1);
            }
            // Finding all possible combinations with updated parameters
            for (int i = 0; i < letters.length(); i++) {
                recursiveMnemonics(mnemonics, mnemonicSoFar + letters.charAt(i), digitsLeft);
            }
        }
    }

    /*
     * Static code blocks are run once when this class is loaded.
     * Here we create an unmoddifiable list to use with the phone
     * mnemonics method.
     */
    private static final List<String> LETTERS_FOR_NUMBER;
    static {
        String[] letters = { "0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);

    }
    // used by method digitLetters

    /*
     * helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with
     * this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter "
                    + "ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return LETTERS_FOR_NUMBER.get(index);
    }

    /*
     * helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is
     * a digit ('0' through '9')
     */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * 
     * @param size  the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);

        // Uncomment this line for comparison purposes
        // p.save(String.format("carpet-%d-%d.png", size, limit));
    }

    /*
     * Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * 
     * @param g The Graphics object to use to fill rectangles
     * 
     * @param size the size of the current square
     * 
     * @param limit the smallest allowable size of squares
     * 
     * @param x the x coordinate of the upper left corner of the current square
     * 
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit,
            double x, double y) {

        // Size has to be larger than limit to do anything
        if (size > limit) {
            int newSize = size / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Middle square is cut out, everything else treated as a new square
                    if (i == 1 && j == 1) {
                        g.fillRect((int) x + newSize, (int) y + newSize, newSize, newSize);
                    } else {
                        drawSquares(g, newSize, limit, x + newSize * i, y + newSize * j);
                    }
                }
            }
        }
    }

    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>
     * pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length,
     * 0 <= col < map[0].length
     * <br>
     * post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map,
     * false otherwise.
     * 
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     *         specified by row, column can reach the edge of the map, false
     *         otherwise.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
        if (map == null || map.length == 0 || !isRectangular(map)
                || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "canFlowOffMap");
        }

        // Base Case - we've made it to the edge of the map
        if (row == 0 || row == map.length - 1 || col == 0 || col == map[0].length - 1) {
            return true;
        }

        // Make trackers to see which direction ultimately flows off
        boolean down = false;
        boolean up = false;
        boolean right = false;
        boolean left = false;
        
        // Only move in the direction where elevation is lower
        if (map[row][col] > map[row + 1][col]) {
            down = canFlowOffMap(map, row + 1, col);
        }
        if (map[row][col] > map[row - 1][col]) {
            up = canFlowOffMap(map, row - 1, col);
        }
        if (map[row][col] > map[row][col + 1]) {
            right = canFlowOffMap(map, row, col + 1);
        }
        if (map[row][col] > map[row][col - 1]) {
            left = canFlowOffMap(map, row, col - 1);
        }

        return down || up || right || left;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null
                && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br>
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br>
     * post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * 
     * @param numTeams  the number of teams to form.
     *                  Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     *         with the maximum total ability and the team with the minimum total
     *         ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
            throw new IllegalArgumentException("Failed precondition: minDifference");
        }
        // Variables to track team abilities and members in each team
        int[][] teams = new int[2][numTeams];
        return minDifferenceHelper(teams, abilities);
    }

    private static int minDifferenceHelper(int[][] teams, int[] abilities) {

        // Base Case - no more abilities to distribute
        if (abilities.length == 0) {
            if (isValid(teams)) {
                return getMinDiff(teams);
            } else {
                return Integer.MAX_VALUE;
            }
        }

        int min = Integer.MAX_VALUE;

        // grabs [1] from [1, 2, 3, 4, 5, 6, 7]
        int abilityToAdd = abilities[0];

        // removes 1 from abilities: [2, 3, 4, 5, 6, 7]
        abilities = removeFirstElement(abilities);
        for (int i = 0; i < teams[0].length; i++) {
            // adding ability to a team
            teams[0][i] += abilityToAdd;
            teams[1][i]++;
            // find diff of that team
            int diff = minDifferenceHelper(teams, abilities);
            // revert so we can look at different combination of team
            teams[0][i] -= abilityToAdd;
            teams[1][i]--;

            if (diff < min) {
                min = diff;
            }
        }
        return min;
    }

    private static int getMinDiff(int[][] teams) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < teams[0].length; i++) {
            int score = teams[0][i];
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }
        return max - min;
    }

    // Check if the teams are valid (size >= 1)
    private static boolean isValid(int[][] teams) {
        for (int i = 0; i < teams[0].length; i++) {
            if (teams[1][i] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Problem 8: Maze solver.
     * <br>
     * pre: board != null
     * <br>
     * pre: board is a rectangular matrix
     * <br>
     * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>
     * pre: there is a single 'S' character present
     * <br>
     * post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins.
     * Return 1 if it is possible to escape the maze
     * but without collecting all the coins.
     * Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * 
     * @param rawMaze represents the maze we want to escape.
     *                rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
    public static int canEscapeMaze(char[][] rawMaze) {
        if (rawMaze == null) {
            throw new IllegalArgumentException("Failed precondition: canEscapeMaze");
        }
        int row = 0;
        int col = 0;
        // Find row and col of starting point
        for (int i = 0; i < rawMaze.length; i++) {
            for (int j = 0; j < rawMaze[0].length; j++) {
                if (rawMaze[i][j] == 'S') {
                    row = i;
                    col = j;
                }
            }
        }
        return canEscapeMazeHelper(rawMaze, row, col);
    }

    private static int canEscapeMazeHelper(char[][] maze, int row, int col) {
        int res = 0;

        // Base Case - escape is possible (with or without coin) OR no escape possible
        if (maze[row][col] == 'E') {
            res = noCoinsLeft(maze) ? 2 : 1;
            return res;
        }
        if (maze[row][col] == '*') {
            return 0;
        }
        ArrayList<Integer[]> moves = possibleMoves(maze, row, col);
        ArrayList<Integer> routes = new ArrayList<>();

        // Update cell before recursive call, revert cell after recursive call
        for (int i = 0; i < moves.size(); i++) {
            boolean coin = updateCell(maze, row, col);
            int newRow = moves.get(i)[0];
            int newCol = moves.get(i)[1];
            res = canEscapeMazeHelper(maze, newRow, newCol);
            routes.add(res);
            revertCell(maze, row, col, coin);
        }
        return res;
    }

    // Return all possible moves from a given index in the maze
    private static ArrayList<Integer[]> possibleMoves(char[][] maze, int row, int col) {
        ArrayList<Integer[]> moves = new ArrayList<>();

        // Ensure moving up, down, left or right is possible (not OOB)
        if (row - 1 != -1) {
            moves.add(new Integer[] { row - 1, col });
        }
        if (row + 1 != maze.length) {
            moves.add(new Integer[] { row + 1, col });
        }
        if (col - 1 != -1) {
            moves.add(new Integer[] { row, col - 1 });
        }
        if (col + 1 != maze[0].length) {
            moves.add(new Integer[] { row, col + 1 });
        }
        return moves;
    }

    // need to change cell after moving into it
    private static boolean updateCell(char[][] maze, int row, int col) {
        if (maze[row][col] == 'S') {
            maze[row][col] = 'G';
        } else if (maze[row][col] == 'G') {
            maze[row][col] = 'Y';
        } else if (maze[row][col] == '$') {
            maze[row][col] = 'Y';
            return true;
        } else {
            maze[row][col] = '*';
        }
        return false;
    }

    // revert cell update to avoid affecting branch routes
    private static void revertCell(char[][] maze, int row, int col, boolean coin) {
        if (maze[row][col] == 'G') {
            maze[row][col] = 'S';
        } else if (maze[row][col] == 'Y' && coin) {
            maze[row][col] = '$';
        } else if (maze[row][col] == 'Y') {
            maze[row][col] = 'G';
        } else {
            maze[row][col] = 'Y';
        }
    }

    // does maze still have a coin
    private static boolean noCoinsLeft(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == '$') {
                    return false;
                }
            }
        }
        return true;
    }

}