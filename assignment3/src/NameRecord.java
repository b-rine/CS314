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

import java.util.ArrayList;

public class NameRecord implements Comparable<NameRecord>{

    private String name;
    private int base;
    private ArrayList<Integer> ranks = new ArrayList<>();

    public NameRecord(String data, int paramBase) {
        // split up the line to get names and ranks
        String[] dataArray = data.split("\\s+");
        name = dataArray[0];
        base = paramBase;
        // storing ranks as integers
        for (int i = 1; i < dataArray.length; i++) {
            ranks.add(Integer.parseInt(dataArray[i]));
        }
    }

    // get the name of this record
    public String getName() {
        return name;
    }

    // get base decade of this record
    public int getBase() {
        return base;
    }

    // get number of decades this name is in (ranked and unranked)
    public int decades() {
        return ranks.size();
    }

    // get rank for a specific decade
    public int decadeRank(int decade) {
        return ranks.get(decade);
    }

    // get the name's highest ranked decade
    public int bestDecade() {
        int best = 1001;
        int decadeNum = 0;
        for (int i = 0; i < decades(); i++) {
            // excluding 0, lower rank means better rank
            if (decadeRank(i) < best && decadeRank(i) != 0) {
                best = decadeRank(i);
                decadeNum = i;
            }
        }
        // calculate the decade we found it in before returning
        return getBase() + decadeNum * 10;
    }

    // get number of times name was ranked
    public int timesRanked() {
        int topThousand = 0;
        for (Integer rank : ranks) {
            // anything better than 0 and below 1001 is ranked
            if (rank > 0 && rank <= 1000) {
                topThousand++;
            }
        }
        return topThousand;
    }

    // check name is ranked every decade
    public boolean alwaysRanked() {
        for (Integer rank : ranks) {
            // not ranked in any decade means false
            if (rank == 0) {
                return false;
            }
        }
        return true;
    }

    // check name is ranked only one decade
    public boolean rankedOnce() {
        return timesRanked() == 1;
    }

    // check name is increasing in popularity
    public boolean morePopular() {
        boolean more = true;
        // get first (previous) element so we can compare it to
        // next elements
        int prev = ranks.get(0);
        for (int i = 1; i < ranks.size(); i++) {
            // any rank that is 0 or worse after the first decade
            // means the name is not getting popular
            if (ranks.get(i) >= prev || ranks.get(i) == 0) {
                return false;
            }
            prev = ranks.get(i);
        }
        return more;
    }

    // check name is decreasing in popularity
    public boolean lessPopular() {
        boolean less = true;
        int prev = ranks.get(0);
        for (int i = 1; i < ranks.size(); i++) {
            if (i < ranks.size() - 1) {
                // getting a rank of 0 any decade BEFORE the last one
                // means it doesn't always get worse
                if (ranks.get(i) <= prev || ranks.get(i) == 0 || ranks.get(0) == 0) {
                    return false;
                }
            } else {
                // check last rank if it's lower, but exclude 0 because
                // we still want to allow the last rank to equal 0
                if (ranks.get(i) <= prev && ranks.get(i) != 0) {
                    return false;
                }
            }
            prev = ranks.get(i);
        }
        return less;
    }

    // override default toString for better format
    public String toString() {
        StringBuilder stats = new StringBuilder(name + "\n");
        for (int i = 0; i < ranks.size(); i++) {
            stats.append(base + (i * 10) + ": " + ranks.get(i) + "\n");
        }
        return stats.toString();
    }

    // implement compareTo to sort name lists
    public int compareTo(NameRecord other) {
        return name.compareTo(other.name);
    }

}
