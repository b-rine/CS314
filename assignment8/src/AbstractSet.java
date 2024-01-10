/*  Student information for assignment:
 *
 *  On OUR honor, Brian and Sid,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *  Student 1: Brian Nguyen
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *  Grader name: Sai
 *
 *  Student 2: Siddharth Sundaram
 *  UTEID: svs833
 *  email address: siddharthsundaram@utexas.edu
 */

import java.util.Iterator;

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    // Add all elements from the parameter set to this set. Return true if this set has changed,
    // false otherwise
    // Big O: O(N^2)
    public boolean addAll(ISet<E> otherSet) {
        boolean res = false;
        for (E elem : otherSet) {
            if (add(elem)) {
                res = true;
            }
        }

        return res;
    }

    // Determine if the parameter item is in this set
    // Big O: O(N)
    public boolean contains(E item) {
        for (E elem : this) {
            if (elem.equals(item)) {
                return true;
            }
        }

        return false;
    }

    // Determine if all the items in the parameter set is in this set
    // Big O: O(N^2)
    public boolean containsAll(ISet<E> otherSet) {
        for (E elem : otherSet) {
            if (!contains(elem)) {
                return false;
            }
        }

        return true;
    }

    // Determine if this set is equal to other
    // Big O: O(N^2)
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof ISet)) {
            return false;
        }

        ISet<?> otherSet = (ISet<?>) other;
        Iterator<E> it1 = iterator();
        while (it1.hasNext()) {
            boolean flag = false;
            Iterator<?> it2 = otherSet.iterator();
            E elem = it1.next();
            while (it2.hasNext() && !flag) {
                if (elem.equals(it2.next())) {
                    flag = true;
                }
            }

            if (!flag) {
                return false;
            }
        }

        return true;
    }

    // Return the intersection of this set with the parameter set
    // Big O: Unsorted - O(N^2), Sorted - O(N)
    public ISet<E> intersection(ISet<E> otherSet) {
        ISet<E> diff = difference(otherSet);
        return difference(diff);
    }

    // Returns the number of elements in this set
    // Big O: O(N)
    public int size() {
        int count = 0;
        for (E elem : this) {
            count++;
        }

        return count;
    }

    // Remove the parameter item from this set if it is present. Return true if this set has
    // changed, false otherwise
    // Big O: O(N)
    public boolean remove(E item) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(item)) {
                it.remove();
                return true;
            }
        }

        return false;
    }

    // Make this set empty
    // Big O: O(N^2)
    public void clear() {
        Iterator<E> it = iterator();

        while(it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * Return a String version of this set.
     * Format is (e1, e2, ... en)
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}
