/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Brian Nguyen, this programming assignment is my own work
 * and I have not provided this code
 * to any other student. 
 * 
 * UTEID: bn6652
 * email address: briannguyen@utexas.edu
 * Number of slip days I am using: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NameSurfer {

    // CS314 students, explain your menu option 7 here:
    // The search option that I implemented looks for names that
    // have consecutive letters in a row. So names like "Hannah"
    // or "Aaron" will be added to the list of names. After doing the
    // option for each file, I found that 'names.txt' had 984 names
    // with consecutive letters, 'names2.txt' had 783 names, and
    // 'names4.txt' had 1306 names.

    // CS314 students, Explain your interesting search / trend here:
    // For my interesting search, I looked up my own name and the other
    // variation: "Bryan". What stuck out to me is that "Bryan" was always
    // ranked every decade, but "Brian" only appeared in 1930 and was already
    // rank 324, whereas "Bryan" started at 482 in 1900 and lowered to rank 537
    // in 1930. Both names share a similar trend where they become popular
    // enough to achieve double-digit ranks. BUT "Brian" got to rank 8 in 1970
    // and remained as the more popular name of the two throughout the rest
    // of the decades, therefore making it the superior variation of the two names.

    // CS314 students, add test code for NameRecord class here:
/*    // test 1 - 2, getName
    public static void testCode() {
        String rawData = "John 664 613 626 575 542 491 497 422 381 385 354";
        int baseDecade = 1900;
        NameRecord record = new NameRecord(rawData, baseDecade);
        String expected = "John";
            if (record.getName().equals(expected)) {
            System.out.println("passed getName - test 1.");
        } else {
            System.out.println("FAILED getName - test 1.");
        }

        rawData = "Kira 602 676 639 935 869 0";
        baseDecade = 1950;
        record = new NameRecord(rawData, baseDecade);
        expected = "Kira";
        String actual = record.toString();
            if (record.getName().equals(expected)) {
            System.out.println("passed getName - test 2.");
        } else {
            System.out.println("FAILED getName - test 2.");
        }

        // test 3 - 4, getBase
        int expect = 1950;
            if (record.getBase() == expect) {
            System.out.println("passed getBase - test 3.");
        } else {
            System.out.println("FAILED getBase - test 3.");
        }

        rawData = "Karol 0 24 800";
        baseDecade = 1930;
        record = new NameRecord(rawData, baseDecade);
        expect = 1930;
            if (record.getBase() == expect) {
            System.out.println("passed getBase - test 4.");
        } else {
            System.out.println("FAILED getBase - test 4.");
        }

        // test 5 - 6, decades
        rawData = "Abby 0 0 0 0 0 906 782 548 233 211 209";
        baseDecade = 1900;
        record = new NameRecord(rawData, baseDecade);
        expect = 11;
            if (record.decades() == expect) {
            System.out.println("passed decades - test 5.");
        } else {
            System.out.println("FAILED decades - test 5.");
        }

        rawData = "Elza 691 808 934 0 0 0";
        baseDecade = 1950;
        record = new NameRecord(rawData, baseDecade);
        expect = 6;
            if (record.decades() == expect) {
            System.out.println("passed decades - test 6.");
        } else {
            System.out.println("FAILED decades - test 6.");
        }

        // test 7 - 8, decadeRank
        expect = 934;
            if (record.decadeRank(2) == expect) {
            System.out.println("passed decadeRank - test 7.");
        } else {
            System.out.println("FAILED decadeRank - test 7.");
        }

        rawData = "Leora 395 390 568 647 822 0 0 0 0 0 0";
        baseDecade = 1900;
        record = new NameRecord(rawData, baseDecade);
        expect = 822;
            if (record.decadeRank(4) == expect) {
            System.out.println("passed decadeRank - test 8.");
        } else {
            System.out.println("FAILED decadeRank - test 8.");
        }

        // test 9 - 10, bestDecade
        expect = 1910;
            if (record.bestDecade() == expect) {
            System.out.println("passed bestDecade - test 9.");
        } else {
            System.out.println("FAILED bestDecade - test 9.");
        }

        rawData = "Leroy 82 65 52 56 90 137 183 263 345 553 734";
        record = new NameRecord(rawData, baseDecade);
        expect = 1920;
            if (record.bestDecade() == expect) {
            System.out.println("passed bestDecade - test 10.");
        } else {
            System.out.println("FAILED bestDecade - test 10.");
        }

        // test 11 - 12, timesRanked
        expect = 11;
            if (record.timesRanked() == expect) {
            System.out.println("passed timesRanked - test 11.");
        } else {
            System.out.println("FAILED timesRanked - test 11.");
        }

        rawData = "Reilly 0 0 0 0 0 0 798";
        record = new NameRecord(rawData, baseDecade);
        expect = 1;
            if (record.timesRanked() == expect) {
            System.out.println("passed timesRanked - test 12.");
        } else {
            System.out.println("FAILED timesRanked - test 12.");
        }

        // test 13 - 14, alwaysRanked
            if (!record.alwaysRanked()) {
            System.out.println("passed alwaysRanked - test 13.");
        } else {
            System.out.println("FAILED alwaysRanked - test 13.");
        }

        rawData = "Reid 897 1000 743 932 797 571 684 656 514 449 441";
        record = new NameRecord(rawData, baseDecade);
            if (record.alwaysRanked()) {
            System.out.println("passed alwaysRanked - test 14.");
        } else {
            System.out.println("FAILED alwaysRanked - test 14.");
        }

        // test 15 - 16, rankedOnce
            if (!record.rankedOnce()) {
            System.out.println("passed rankedOnce - test 15.");
        } else {
            System.out.println("FAILED rankedOnce - test 15.");
        }

        rawData = "Sherita 0 0 0 0 0 0 836 0 0";
        record = new NameRecord(rawData, baseDecade);
            if (record.rankedOnce()) {
            System.out.println("passed rankedOnce - test 16.");
        } else {
            System.out.println("FAILED rankedOnce - test 16.");
        }

        // test 17 - 18, morePopular
        rawData = "Reginald 365 314 311 236 151 134 172 212 275";
        record = new NameRecord(rawData, baseDecade);
            if (!record.morePopular()) {
            System.out.println("passed morePopular - test 17");
        } else {
            System.out.println("FAILED morePopular - test 17");
        }

        rawData = "Gerald 805 788 652 524 473 341 251 142 19";
        record = new NameRecord(rawData, baseDecade);
            if (record.morePopular()) {
            System.out.println("passed morePopular - test 18");
        } else {
            System.out.println("FAILED morePopular - test 18");
        }

        // test 19 - 20, lessPopular
            if (!record.lessPopular()) {
            System.out.println("passed lessPopular - test 19");
        } else {
            System.out.println("FAILED lessPopular - test 19");
        }

        rawData = "Yvonne 23 141 234 345 473 586 647 700 0";
        record = new NameRecord(rawData, baseDecade);
            if (record.lessPopular()) {
            System.out.println("passed lessPopular - test 20");
        } else {
            System.out.println("FAILED lessPopular - test 20");
        }

        // test 21 - 22, toString
        rawData = "Abel 664 613 626 575 542 491 497 422 381 385 354";
        record = new NameRecord(rawData, baseDecade);
        expected = "Abel\n1900: 664\n1910: 613\n1920: 626\n1930: 575\n1940: "
                + "542\n1950: 491\n1960: 497\n1970: 422\n1980: 381\n1990: "
                + "385\n2000: 354\n";
        actual = record.toString();
            if (expected.equals(actual)) {
            System.out.println("passed toString - test 21.");
        } else {
            System.out.println("FAILED toString - test 21.");
        }

        rawData = "Emmitt 602 676 639 935 869 0";
        baseDecade = 1950;
        record = new NameRecord(rawData, baseDecade);
        expected = "Emmitt\n1950: 602\n1960: 676\n1970: 639\n1980: 935\n1990: "
                + "869\n2000: 0\n";
        actual = record.toString();
            if (expected.equals(actual)) {
            System.out.println("passed toString - test 22.");
        } else {
            System.out.println("FAILED toString - test 22.");
        }

        // test 23 - 24, compareTo
        rawData = "Abel 664 613 626 575 542 491 497 422 381 385 354";
        NameRecord record2 = new NameRecord(rawData, baseDecade);
            if (record.compareTo(record2) > 0) {
            System.out.println("passed compareTo - test 23.");
        } else {
            System.out.println("FAILED compareTo - test 23.");
        }

        rawData = "Aaron 279 232 132 36 32 31 41";
        record = new NameRecord(rawData, baseDecade);
        rawData = "Abagail0 0 0 0 0 0 0 958";
        record2 = new NameRecord(rawData, baseDecade);
            if (record.compareTo(record2) < 0) {
            System.out.println("passed compareTo - test 24.");
        } else {
            System.out.println("FAILED compareTo - test 24.");
        }
    }
 */

    // A few simple tests for the Names and NameRecord class.
    public static void simpleTest() {
        // raw data for Jake. Alter as necessary for your NameRecord
        String jakeRawData = "Jake 262 312 323 479 484 630 751 453 225 117 97";
        int baseDecade = 1900;
        NameRecord jakeRecord = new NameRecord(jakeRawData, baseDecade);
        String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
                        + "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: "
                        + "117\n2000: 97\n";
        String actual = jakeRecord.toString();
        System.out.println("expected string:\n" + expected);
        System.out.println("actual string:\n" + actual);
        if (expected.equals(actual)) {
            System.out.println("passed Jake toString test.");
        } else {
            System.out.println("FAILED Jake toString test.");
        }

        // Some Name Tests
        Names names = new Names(getFileScannerForNames("names.txt"));
        String[] testNames = {"Isabelle", "isabelle", "sel",
                "X1178", "ZZ", "via", "kelly"};
        boolean[] expectNull = {false, false, true, true, true, true, false};
        for (int i = 0; i < testNames.length; i++) {
            performGetNameTest(names, testNames[i], expectNull[i]);
        }
    }

    // Checks if given name is present in Names.
    private static void performGetNameTest(Names names, String name,
            boolean expectNull) {
        System.out.println("Performing test for this name: " + name);
        if (expectNull) {
            System.out.println("Expected return value is null");
        } else {
            System.out.println("Expected return value is not null");
        }
        NameRecord result = names.getName(name);
        if ((expectNull && result == null) || (!expectNull && result != null)) {
            System.out.println("PASSED TEST.");
        } else {
            System.out.println("Failed test");
        }
    }

    // main method. Driver for the whole program
    public static void main(String[] args) {
        // Alter name of file to try different data sources.
        final String NAME_FILE = "names.txt";
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    /* pre: namesDatabase != null
     * Ask user for options to perform on the given Names object.
     * Creates a Scanner connected to System.in.
     */
    private static void runOptions(Names namesDatabase) {
        Scanner keyboard = new Scanner(System.in);
        MenuChoices[] menuChoices = MenuChoices.values();
        MenuChoices menuChoice;
        do {
            showMenu();
            int userChoice = getChoice(keyboard) - 1;
            menuChoice = menuChoices[userChoice];
            if(menuChoice == MenuChoices.SEARCH) {
                search(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.ONE_NAME) {
                oneName(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.APPEAR_ONCE) {
                appearOnce(namesDatabase);
            } else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
                appearAlways(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_MORE) {
                alwaysMore(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_LESS) {
                alwaysLess(namesDatabase);
            } else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
                consecutive(namesDatabase);
            }
        } while(menuChoice != MenuChoices.QUIT);
        keyboard.close();
    }

    /* Create a Scanner and return connected to a File with the given name.
     * pre: fileName != null
     * post: Return a Scanner connected to the file or null
     * if the File does not exist in the current directory.
     */
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("\n***** ERROR IN READING FILE ***** ");
            System.out.println("Can't find this file "
                    + fileName + " in the current directory.");
            System.out.println("Error: " + e);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Be sure " + fileName + " is in this directory: ");
            System.out.println(currentDir);
            System.out.println("\nReturning null from method.");
            sc = null;
        }
        return sc;
    }

    /* Display the names that have appeared in every decade.
     * pre: n != null
     * post: print out names that have appeared in every decade
     */
    private static void appearAlways(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        // rankedEveryDecade searches names and we just print its results
        System.out.println(namesDatabase.rankedEveryDecade().size() + " names appear " +
                "in every decade. The names are: ");
        for (int i = 0; i < namesDatabase.rankedEveryDecade().size(); i++) {
            System.out.println(namesDatabase.rankedEveryDecade().get(i));
        }
    }

    /* Display the names that have appeared in only one decade.
     * pre: n != null
     * post: print out names that have appeared in only one decade
     */
    private static void appearOnce(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        // only need to print names found from rankedOnlyOneDecade
        System.out.println(namesDatabase.rankedOnlyOneDecade().size() + " names appear " +
                "in exactly one decade. The names are: ");
        for (int i = 0; i < namesDatabase.rankedOnlyOneDecade().size(); i++) {
            System.out.println(namesDatabase.rankedOnlyOneDecade().get(i));
        }
    }

    /* Display the names that have gotten more popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten more popular in each decade
     */
    private static void alwaysMore(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        // alwaysMorePopular does searching so we only need to print results
        System.out.println(namesDatabase.alwaysMorePopular().size() + " names are more " +
                "popular in every decade. ");
        for (int i = 0; i < namesDatabase.alwaysMorePopular().size(); i++) {
            System.out.println(namesDatabase.alwaysMorePopular().get(i));
        }
    }

    /* Display the names that have gotten less popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten less popular in each decade
     */
    private static void alwaysLess(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        // alwaysLessPopular does the searching for us
        System.out.println(namesDatabase.alwaysLessPopular().size() + " names are " +
                "less popular in every decade. ");
        for (int i = 0; i < namesDatabase.alwaysLessPopular().size(); i++) {
            System.out.println(namesDatabase.alwaysLessPopular().get(i));
        }
    }

    /* Display the data for one name or state that name has never been ranked.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: print out the data for n or a message that n has never been in the
     * top 1000 for any decade
     */
    private static void oneName(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a name: ");
        String name = keyboard.next();
        System.out.println();
        // found a match in the database
        if (namesDatabase.getName(name) != null) {
            System.out.println(namesDatabase.getName(name));
        } else {
            System.out.println(name + " does not appear in any decade.");
        }
    }

    /* Display all names that contain a substring from the user
     * and the decade they were most popular.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: display the data for each name.
     */
    private static void search(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a partial name: ");
        String name = keyboard.next();
        System.out.println();
        // not empty means that we found matches, so get the results
        if (!namesDatabase.getMatches(name).isEmpty()) {
            System.out.println("There are " + namesDatabase.getMatches(name).size() +
                    " matches for " + name + ".");
            System.out.println();
            System.out.println("The matches with their highest ranking decade are:");
            // only want to print out the best decade with the matched names
            for (int i = 0; i < namesDatabase.getMatches(name).size(); i++) {
                System.out.println(namesDatabase.getMatches(name).get(i).getName() + " " +
                        namesDatabase.getMatches(name).get(i).bestDecade());
            }
        } else {
            System.out.println("There are 0 matches for " + name + ".");
        }
    }

    private static void consecutive(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        // consecutiveLetters will do all the name searching
        System.out.println(namesDatabase.consecutiveLetters().size() + " names have "
            + "consecutive letters. ");
        for (int i = 0; i < namesDatabase.consecutiveLetters().size(); i++) {
            System.out.println(namesDatabase.consecutiveLetters().get(i));
        }
    }

    /* Get choice from the user keyboard != null and is connected to System.in
     * return an int that is >= MenuChoices.SEARCH.ordinal()
     *  and <= MenuChoices.QUIT.ordinal().
     */
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // Add one due to zero based indexing of enums, but 1 based indexing of menu.
        final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
        while (choice < 1  || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    /* Ensure an int is entered from the keyboard.
     * pre: s != null and is connected to System.in
     * post: return the int typed in by the user.
     */
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // Show the user the menu.
    private static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("Enter 1 to search for names.");
        System.out.println("Enter 2 to display data for one name.");
        System.out.println("Enter 3 to display all names that appear in only "
                + "one decade.");
        System.out.println("Enter 4 to display all names that appear in all "
                + "decades.");
        System.out.println("Enter 5 to display all names that are more popular "
                + "in every decade.");
        System.out.println("Enter 6 to display all names that are less popular "
                + "in every decade.");
        System.out.println("Enter 7 to display all names that have consecutive "
                + "letters.");
        System.out.println("Enter 8 to quit.");
        System.out.println();
    }

    /**
     * An enumerated type to hold the menu choices
     * for the NameSurfer program.
     */
    private static enum MenuChoices {
        SEARCH, ONE_NAME, APPEAR_ONCE, APPEAR_ALWAYS, ALWAYS_MORE,
        ALWAYS_LESS, STUDENT_SEARCH, QUIT;
    }
}
