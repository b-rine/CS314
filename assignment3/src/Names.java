/* Student information for assignment:
*
*  On my honor, Brian Nguyen, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: bn6652
*  email address: briannguyen@utexas.edu
*  Number of slip days I am using: 0
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
* A collection of NameRecords.
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names {

    private int baseDecade;
    private int numDecades;
    private ArrayList<NameRecord> records = new ArrayList<>();

    /**
     * Construct a new Names object based on the data source the Scanner
     * sc is connected to. Assume the first two lines in the
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded
     * and are not part of the resulting Names object.
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names
     * and positioned at the start of the data source.
     */
    public Names(Scanner sc) {
        int lineNum = 1;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // first two lines always have base decade and number of decades
            if (lineNum == 1) {
                baseDecade = Integer.parseInt(line);
            } else if (lineNum == 2) {
                numDecades = Integer.parseInt(line);
            } else {
                String[] parsedLine = line.split("\\s+");
                // minus the name, each line have the same number of ranks as numDecades
                if (parsedLine.length - 1 == numDecades) {
                    int notRanked = 0;
                    for (int i = 1; i < parsedLine.length; i++) {
                        if (Integer.parseInt(parsedLine[i]) == 0) {
                            notRanked++;
                        }
                    }
                    // if the name is not unranked for every decade, then we can add it
                    if (notRanked != numDecades) {
                        NameRecord name = new NameRecord(line, baseDecade);
                        records.add(name);
                    }
                }
            }
            lineNum++;
        }
    }

    /**
     * Returns an ArrayList of NameRecord objects that contain a
     * given substring, ignoring case.  The names must be in sorted order based
     * on the names of the NameRecords.
     * @param partialName != null, partialName.length() > 0
     * @return an ArrayList of NameRecords whose names contains
     * partialName. If there are no NameRecords that meet this
     * criteria returns an empty list.
     */
    public ArrayList<NameRecord> getMatches(String partialName) {
        ArrayList<NameRecord> names = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            // any name that contains the partial name, we'll add
            if (records.get(i).getName().toLowerCase().contains(partialName.toLowerCase())) {
                names.add(records.get(i));
            }
        }
        Collections.sort(names);
        return names;
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better for every decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * @return A list of the names that have been ranked in the top
     * 1000 or better in every decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list.
     */
    public ArrayList<String> rankedEveryDecade() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            // names that are ranked every decade are added
            if (records.get(i).alwaysRanked()) {
                names.add(records.get(i).getName());
            }
        }
        Collections.sort(names);
        return names;
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better in exactly one decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     * @return A list of the names that have been ranked in the top
     * 1000 or better in exactly one decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list.
     */
    public ArrayList<String> rankedOnlyOneDecade() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            // names only ranked once get added
            if (records.get(i).rankedOnce()) {
                names.add(records.get(i).getName());
            }
        }
        Collections.sort(names);
        return names;
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting more
     * popular every decade. The Strings  must be in sorted
     * order based on the name of the NameRecords.
     * @return A list of the names that have been getting more popular in
     * every decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list.
     */
    public ArrayList<String> alwaysMorePopular() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            // names must be increasing in popularity to be added
            if (records.get(i).morePopular()) {
                names.add(records.get(i).getName());
            }
        }
        Collections.sort(names);
        return names;
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting less
     * popular every decade. The Strings  must be in sorted order based
     * on the name of the NameRecords.
     * @return A list of the names that have been getting less popular in
     * every decade. The list is in sorted ascending
     * order. If there are no NameRecords that meet this
     * criteria returns an empty list.
     */
    public ArrayList<String> alwaysLessPopular() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            // names must be decreasing in popularity to be added
            if (records.get(i).lessPopular()) {
                names.add(records.get(i).getName());
            }
        }
        Collections.sort(names);
        return names;
    }

    /**
     * Return the NameRecord in this Names object that matches the given String ignoring case.
     * <br>
     * <tt>pre: name != null</tt>
     * @param name The name to search for.
     * @return The name record with the given name or null if no NameRecord in this Names
     * object contains the given name.
     */
    public NameRecord getName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The parameter name cannot be null");
	    }
        for (int i = 0; i < records.size(); i++) {
            // we are looking for an exact match of the name, otherwise return nothing
            if (records.get(i).getName().equalsIgnoreCase(name)) {
                return records.get(i);
            }
        }
        return null;
    }

    public ArrayList<String> consecutiveLetters() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            // parse through each letter of the name
            for (int j = 1; j < records.get(i).getName().length(); j++) {
                // grab the current and next letter
                char prev = records.get(i).getName().charAt(j - 1);
                char next = records.get(i).getName().charAt(j);
                // if they're the same, then the name has consecutive letters and we add it
                if (Character.toLowerCase(prev) == Character.toLowerCase(next)) {
                    names.add(records.get(i).getName());
                }
            }

        }
        Collections.sort(names);
        return names;
    }

}
