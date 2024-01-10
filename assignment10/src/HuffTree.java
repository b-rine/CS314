/*  Student information for assignment:
 *
 *  On OUR honor, Siddharth Sundaram and Brian Nguyen, this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1: Siddharth Sundaram
 *  UTEID: svs833
 *  email address: siddharthsundaram@utexas.edu
 *  Grader name: Sai
 *
 *  Student 2: Brian Nguyen
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *
 */

import java.util.HashMap;
import java.util.ArrayList;

public class HuffTree {

    private TreeNode root;
    private int size;
    public static final int BITS_PER_LEAF_NODE = 9;

    // Constructor for HuffTree
    public HuffTree(TreeNode r) {
        root = r;
    }

    // Returns the root of this tree
    public TreeNode getRoot() {
        return root;
    }

    // Returns a map from chunk value to code
    public HashMap<Integer, String> getCodes() {
        HashMap<Integer, String> res = new HashMap<>();
        getCodesHelper(res, root, "");
        return res;
    }

    // Recursive helper to create the map
    private void getCodesHelper(HashMap<Integer, String> map, TreeNode node, String code) {
        if (node.getLeft() == null && node.getRight() == null) {
            map.put(node.getValue(), code);
        } else {
            if (node.getLeft() != null) {
                getCodesHelper(map, node.getLeft(), code + "0");
            }
            if (node.getRight() != null) {
                getCodesHelper(map, node.getRight(), code + "1");
            }
        }
    }

    // Get a preorder traversal bit representation of this tree with size at beginning
    public ArrayList<Integer> preOrderBits() {

        // Index 0 stores number of leaf nodes, index 1 stores total size
        int[] sizes = new int[2];
        ArrayList<Integer> res = new ArrayList<>();
        preOrderHelper(res, root, sizes);
        res.add(0, sizes[0] * BITS_PER_LEAF_NODE + sizes[1]);
        return res;
    }

    // Recursive helper method for preOrderBits
    private void preOrderHelper(ArrayList<Integer> traversal, TreeNode node, int[] sizes) {
        if (node.getLeft() == null && node.getRight() == null) {
            traversal.add(1);
            traversal.add(node.getValue());
            sizes[0]++;
        } else {
            traversal.add(0);
            if (node.getLeft() != null) {
                preOrderHelper(traversal, node.getLeft(), sizes);;
            }
            if (node.getRight() != null) {
                preOrderHelper(traversal, node.getRight(), sizes);;
            }
        }

        sizes[1]++;
    }
}