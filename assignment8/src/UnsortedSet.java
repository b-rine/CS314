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
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    // Constructor to initialize myCon
    // Big O: O(1)
    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    // Return the size of this set
    // Big O: O(1)
    public int size() {
        return myCon.size();
    }

    // Return an iterator over this set
    // Big O: O(1)
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    // Make this set empty
    // Big O: O(N)
    public void clear() {
        myCon.clear();
    }

    // Add the parameter element to this set. Return true if this set has changed,
    // false otherwise
    // Big O: O(N)
    public boolean add(E item) {
        for (E elem : myCon) {
            if (elem.equals(item)) {
                return false;
            }
        }

        myCon.add(item);
        return true;
    }

    // Returns the difference of this set and the parameter set
    // Big O: O(N^2)
    public ISet<E> difference(ISet<E> otherSet) {
        UnsortedSet<E> res = new UnsortedSet<>();
        for (E elem : myCon) {
            if (!otherSet.contains(elem)) {
                res.myCon.add(elem);
            }
        }

        return res;
    }

    // Returns the union of this set and the parameter set
    // Big O: O(N^2)
    public ISet<E> union(ISet<E> otherSet) {
        UnsortedSet<E> res = new UnsortedSet<>();
        res.addAll(this);
        res.addAll(otherSet);
        return res;
    }
}
