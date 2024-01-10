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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    // Returns an iterator over this set
    // Big O: O(1)
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    /**
     * create an empty SortedSet
     */
    // Big O: O(1)
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * Create a copy of other that is sorted.<br>
     * @param other != null
     */
    // Big O: O(NlogN)
    public SortedSet(ISet<E> other) {
        this();
        for (E elem : other) {
            myCon.add(elem);
        }
        mergeSort(myCon);
    }

    // Mergesort method from class slides
    // Big O: O(NlogN)
    private void mergeSort(ArrayList<E> con) {
        ArrayList<E> temp = new ArrayList<>(con.size());
        for (int i = 0; i < con.size(); i++) {
            temp.add(null);
        }
        mergeHelper(con, temp, 0, con.size() - 1);
    }

    // Recursive helper method for mergesort from class slides
    // Big O: O(NlogN)
    private void mergeHelper(ArrayList<E> con, ArrayList<E> temp, int low, int high) {
        if (low < high) {
            int center = (low + high) / 2;
            mergeHelper(con, temp, low, center);
            mergeHelper(con, temp, center + 1, high);
            merge(con, temp, low, center + 1, high);
        }
    }

    // Mergesort helper used to merge collections from class slides
    // Big O: O(N)
    private void merge(ArrayList<E> con, ArrayList<E> temp, int leftPos, int rightPos,
                       int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (con.get(leftPos).compareTo(con.get(rightPos)) <= 0) {
                temp.set(tempPos, con.get(leftPos));
                leftPos++;
            } else {
                temp.set(tempPos, con.get(rightPos));
                rightPos++;
            }
            tempPos++;
        }
        while (leftPos <= leftEnd) {
            temp.set(tempPos, con.get(leftPos));
            tempPos++;
            leftPos++;
        }
        while (rightPos <= rightEnd) {
            temp.set(tempPos, con.get(rightPos));
            tempPos++;
            rightPos++;
        }
        for (int i = 0; i < numElements; i++, rightEnd--) {
            con.set(rightEnd, temp.get(rightEnd));
        }
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    // Big O: O(1)
    public E min() {
        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    // Big O: O(1)
    public E max() {
        return myCon.get(size() - 1);
    }

    // Adds the parameter element to this set. Returns true if this set was changed,
    // false otherwise
    // Big O: O(N)
    public boolean add(E item) {
        int size = size();
        for (int i = 0; i < size; i++) {
            E elem = myCon.get(i);
            if (elem.equals(item)) {
                return false;
            }
            if (item.compareTo(elem) < 0) {
                myCon.add(i, item);
                return true;
            }
        }
        myCon.add(item);
        return true;
    }

    // Add all elements from parameter set to this set. Returns true if this set was changed,
    // false otherwise
    // Big O: O(N)
    public boolean addAll(ISet<E> otherSet) {
        ArrayList<E> temp = myCon;
        SortedSet<E> otherSortedSet = convert(otherSet);

        // merging part of the sets
        addMerge(otherSortedSet);
        return !myCon.equals(temp);
    }

    // Merges 2 sorted sets in proper order, used for addAll method
    // Big O: O(N)
    private void addMerge(SortedSet<E> other) {
        ArrayList<E> res = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;

        // initializing biggest and smallest sets

        while (index1 < size() && index2 < other.size()) {
            E elem1 = myCon.get(index1);
            E elem2 = other.myCon.get(index2);
            if (elem1.compareTo(elem2) < 0) {
                res.add(elem1);
                index1++;
            } else if (elem1.compareTo(elem2) > 0) {
                res.add(elem2);
                index2++;
            } else {
                res.add(elem1);
                index1++;
                index2++;
            }
        }

        if (index1 < size()) {
            addToEnd(res, myCon, index1);
        } else if (index2 < other.size()) {
            addToEnd(res, other.myCon, index2);
        }
        myCon = res;
    }

    // Adds all elements from addList to the end of res
    // Big O: O(N)
    private void addToEnd(ArrayList<E> res, ArrayList<E> addList, int index) {
        for (int i = index; i < addList.size(); i++) {
            res.add(addList.get(i));
        }
    }

    // Makes this set empty
    // Big O: O(N)
    public void clear() {
        myCon.clear();
    }

    // Returns true if this set contains the parameter item, false otherwise
    // Big O: O(logN)
    public boolean contains(E item) {
        return -1 != bsearch(item);
    }

    // Binary search method
    // Big O: O(logN)
    private int bsearch(E tgt) {
        int result = -1;
        int low = 0;
        int high = size() - 1;
        while (result == -1 && low <= high) {
            int mid = low + ((high - low) / 2);
            int compareResult = tgt.compareTo(myCon.get(mid));
            if (compareResult == 0) {
                result = mid;
            } else if (compareResult > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // Returns true if this set contains all elements from the parameter set, false otherwise
    // Big O: O(N)
    public boolean containsAll(ISet<E> otherSet) {
        SortedSet<E> otherSortedSet = convert(otherSet);
        int elemIndex = bsearch(otherSortedSet.myCon.get(0));
        if (elemIndex != -1) {
            int index = 1;
            while (elemIndex < size() && index < otherSortedSet.size()) {
                int comparison = myCon.get(elemIndex).compareTo(otherSortedSet.myCon.get(index));
                if (comparison < 0) {
                    elemIndex++;
                } else if (comparison == 0) {
                    elemIndex++;
                    index++;
                } else {
                    return false;
                }
            }
            if (index < otherSortedSet.size()) {
                return false;
            }
            return true;
        }
        return false;
    }

    // Return true if this set is equal to parameter set, false otherwise
    // Big O: Worst - O(NlogN), Best - O(N)
    public boolean equals(Object other) {
        if (!(other instanceof ISet)) {
            return false;
        }
        ISet<?> otherSet = (ISet<?>) other;
        if (!(other instanceof SortedSet)) {
            return super.equals(otherSet);
        } else {
            Iterator<E> it1 = iterator();
            Iterator<?> it2 = otherSet.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                if (!(it1.next().equals(it2.next()))) {
                    return false;
                }
            }
            if (it1.hasNext() || it2.hasNext()) {
                return false;
            }
            return true;
        }
    }

    // Removes the parameter element from this set
    // Big O: O(N)
    public boolean remove(E item) {
        int index = bsearch(item);
        if (index != -1) {
            myCon.remove(index);
            return true;
        }
        return false;
    }

    // Returns the size of this set
    // Big O: O(1)
    public int size() {
        return myCon.size();
    }

    // Returns the difference of this set and parameter set
    // Big O: O(N)
    public ISet<E> difference(ISet<E> other) {
        SortedSet<E> otherSortedSet = convert(other);
        SortedSet<E> res = new SortedSet<>();
        int thisIndex = 0;
        int otherIndex = 0;

        while (thisIndex < size() && otherIndex < otherSortedSet.size()) {
            E thisElem = myCon.get(thisIndex);
            E otherElem = otherSortedSet.myCon.get(otherIndex);
            int comparison = thisElem.compareTo(otherElem);
            if (comparison < 0) {
                res.add(thisElem);
                thisIndex++;
            } else if (comparison == 0) {
                thisIndex++;
                otherIndex++;
            } else {
                otherIndex++;
            }
        }
        if (thisIndex < size()) {
            addToEnd(res.myCon, myCon, thisIndex);
        }
        return res;
    }

    // Returns the union of this set and the parameter set
    // Big O: O(N)
    public ISet<E> union(ISet<E> other) {
        SortedSet<E> res = new SortedSet<>();
        res.addAll(this);
        res.addAll(other);
        return res;
    }

    // Converts parameter set to a sorted set if not one already
    // Big O: O(NlogN)
    private SortedSet<E> convert(ISet<E> set) {
        SortedSet<E> otherSortedSet;
        if (!(set instanceof SortedSet)) {
            otherSortedSet = new SortedSet<>(set);
        } else {
            otherSortedSet = (SortedSet<E>) set;
        }

        return otherSortedSet;
    }

}
