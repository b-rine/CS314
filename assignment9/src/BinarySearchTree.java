/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Brian Nguyen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *  TA name: Sai
 *  Number of slip days I am using: 0
 */

import java.util.List;
import java.util.ArrayList;

/**
 * Shell for a binary search tree class.
 * @author scottm
 * @param <E> The data type of the elements of this BinarySearchTree.
 * Must implement Comparable or inherit from a class that implements
 * Comparable.
 *
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private BSTNode<E> root;
    private int size;

    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     */
    public boolean add(E value) {
        int oldSize = size;
        root = addHelp(root, value);
        return oldSize != size;
    }

    // Using naive insertion algorithm from Mike's class notes
    private BSTNode<E> addHelp(BSTNode<E> n, E value) {
        if (n == null) {
            size++;
            return new BSTNode<>(value);
        }
        // determine which side of tree value belongs in
        int direction = value.compareTo(n.data);
        if (direction < 0) {
            n.left = addHelp(n.left, value);
        } else if (direction > 0) {
            n.right = addHelp(n.right, value);
        }
        return n;
    }

    /**
     *  Remove a specified item from this Binary Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     */
    public boolean remove(E value) {
        int oldSize = size;
        root = removeHelp(root, value);
        return oldSize != size;
    }

    // Using naive remove algorithm from Mike's class notes
    private BSTNode<E> removeHelp(BSTNode<E> n, E value) {
        if (n != null) {
            int direction = value.compareTo(n.data);
            // determine which side value is in
            if (direction < 0) {
                n.left = removeHelp(n.left, value);
            } else if (direction > 0) {
                n.right = removeHelp(n.right, value);
            } else {
                size--;
                // 3 different cases with child nodes to handle after removal
                if (n.left == null && n.right == null) {
                    n = null;
                } else if (n.right == null) {
                    n = n.left;
                } else if (n.left == null) {
                    n = n.right;
                } else {
                    n.data = maxHelp(n.left);
                    n.left = removeHelp(n.left, n.data);
                    size++;
                }
            }
        }
        return n;
    }

    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @return true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        return presentHelp(root, value);
    }

    private boolean presentHelp(BSTNode<E> n, E value) {
        boolean found = false;
        if (n != null) {
            // each check will look through every subtree
            if (n.data == value || presentHelp(n.left, value) || presentHelp(n.right, value)) {
                return true;
            }
        }
        return found;
    }

    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @return the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @return the height of this tree or -1 if the tree is empty
     */
    public int height() {
        return heightHelp(root);
    }

    // Using heightHelper from Mike's class notes
    private int heightHelp(BSTNode<E> n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(heightHelp(n.left), heightHelp(n.right));
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order.
     *  If the tree is empty return an empty List
     *  @return a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        List<E> result = new ArrayList<>();
        getAllHelp(root, result);
        return result;
    }

    private void getAllHelp(BSTNode<E> n, List<E> result) {
        if (n != null) {
            // for ascending order, we do in-order traversal because everything is sorted
            getAllHelp(n.left, result);
            result.add(n.data);
            getAllHelp(n.right, result);
        }
    }

    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max() {
        return maxHelp(root);
    }

    // tree is already sorted, so we check the right-most element for max
    private E maxHelp(BSTNode<E> n) {
        while (n.right != null) {
            n = n.right;
        }
        return n.data;
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
        return minHelp(root);
    }

    // tree is already sorted, so we check the left-most element for min
    private E minHelp(BSTNode<E> n) {
        while (n.left != null) {
            n = n.left;
        }
        return n.data;
    }

    /**
     * An add method that implements the add algorithm iteratively
     * instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree,
     * otherwise do nothing.
     *
     * @param data the item to be added to this tree
     */
    public void iterativeAdd(E data) {
        if (root == null) {
            size++;
            root = new BSTNode<>(data);
        }
        BSTNode<E> parent = null;
        BSTNode<E> child = root;

        // determine where we need to add the node with trail-lead approach
        boolean duplicateFound = false;
        while (child != null && !duplicateFound) {
            parent = child;
            if (data.compareTo(child.data) < 0) {
                child = child.left;
            } else if (data.compareTo(child.data) > 0) {
                child = child.right;
            } else {
                duplicateFound = true;
            }
        }

        // once found, newNode is added based on its value compared to parent
        BSTNode<E> newNode = new BSTNode<>(data);
        if (!duplicateFound) {
            size++;
            if (data.compareTo(parent.data) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    /**
     * Return the "kth" element in this Binary Search Tree. If kth = 0 the
     * smallest value (minimum) is returned.
     * If kth = 1 the second smallest value is returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth value in this Binary Search Tree
     */
    public E get(int kth) {
        int[] kCounter = new int[1];
        List<E> res = new ArrayList<>();
        getHelp(root, kth, kCounter, res);
        return res.get(0);
    }

    private void getHelp(BSTNode<E> n, int kth, int[] kCounter, List<E> res) {
        // performing in-order traversal to find kth value and stopping when achieved
        if (n != null && kCounter[0] <= kth) {
            getHelp(n.left, kth, kCounter, res);
            if (kCounter[0] == kth) {
                res.add(n.data);
            }
            kCounter[0]++;
            getHelp(n.right, kth, kCounter, res);
        }
    }

    /**
     * Return a List with all values in this Binary Search Tree 
     * that are less than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than 
     * the parameter value. If there are no values in this tree less 
     * than value return an empty list. The elements of the list are 
     * in ascending order.
     */
    public List<E> getAllLessThan(E value) {
        List<E> res = new ArrayList<>();
        getLessHelp(root, res, value);
        return res;
    }

    private void getLessHelp(BSTNode<E> n, List<E> res, E value) {
        if (n != null) {
            // want values less than, so ignore anything equal or greater than the target value
            if (n.data.compareTo(value) >= 0) {
                getLessHelp(n.left, res, value);

            // in-order traversal because we know this subtree has lower values
            } else if (n.data.compareTo(value) < 0) {
                getLessHelp(n.left, res, value);
                res.add(n.data);
                getLessHelp(n.right, res, value);
            }
        }
    }

    /**
     * Return a List with all values in this Binary Search Tree 
     * that are greater than the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater
     *  than the parameter value. If there are no values in this tree
     * greater than value return an empty list. 
     * The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        List<E> res = new ArrayList<>();
        getGreaterHelp(root, res, value);
        return res;
    }

    private void getGreaterHelp(BSTNode<E> n, List<E> res, E value) {
        if (n != null) {
            // want values greater than, so ignore anything equal or less than the target value
            if (n.data.compareTo(value) <= 0) {
                getGreaterHelp(n.right, res, value);

            // in-order traversal because we know this subtree has greater values
            } else if (n.data.compareTo(value) > 0) {
                getGreaterHelp(n.left, res, value);
                res.add(n.data);
                getGreaterHelp(n.right, res, value);
            }
        }
    }

    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        int[] nodes = new int[1];
        int currentDepth = 0;
        depthHelp(root, d, currentDepth, nodes);
        return nodes[0];
    }

    private void depthHelp(BSTNode<E> n, int d, int currD, int[] nodes) {
        // in-order traversal and only needs to check when target depth is reached
        if (n != null) {
            depthHelp(n.left, d, currD + 1, nodes);
            if (currD == d) {
                nodes[0]++;
            }
            depthHelp(n.right, d, currD + 1, nodes);
        }
    }

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children will not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * <br>pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if(n != null){
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null);
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,
                BSTNode<E> initRight) {
            data = initValue;
            left = initLeft;
            right = initRight;
        }

        public E getData() {
            return data;
        }

        public BSTNode<E> getLeft() {
            return left;
        }

        public BSTNode<E> getRight() {
            return right;
        }

        public void setData(E theNewValue) {
            data = theNewValue;
        }

        public void setLeft(BSTNode<E> theNewLeft) {
            left = theNewLeft;
        }

        public void setRight(BSTNode<E> theNewRight) {
            right = theNewRight;
        }
    }
}
