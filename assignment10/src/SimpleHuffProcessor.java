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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Math;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private int[] chunkFreqs;
    private HuffTree codeTree;
    private HashMap<Integer, String> valToCode;
    int compressedBits;
    int saved;
    int headerType;
    ArrayList<Integer> treeRepresentation;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        int uncompressedBits = getIntsArray(in);
        PriorityQueue314<TreeNode> pq = nodeMaker();
        makeHuffTree(pq);
        valToCode = codeTree.getCodes();
        headerType = headerFormat;
        treeRepresentation = codeTree.preOrderBits();

        // Accounting for MAGIC_NUMBER and SCF/STF bits (2 * BITS_PER_INT bits)
        compressedBits = BITS_PER_INT * 2;

        // Accounting for reproducing tree bits
        compressedBits += getHeaderBits();

        // Accounting for bits from actual file data
        for (int i = 0; i < chunkFreqs.length; i++) {
            String code = valToCode.get(i);
            if (code != null) {
                compressedBits += code.length() * chunkFreqs[i];
            }
        }

        // Accounting for PEOF bits
        compressedBits += valToCode.get(PSEUDO_EOF).length();

        showResults(uncompressedBits);

        saved = uncompressedBits - compressedBits;

        return saved;
    }

    private void showResults(int uncompressedBits) {
        showString("Counting characters in selected file.");
        showString("");
        showString("Counting characters in selected file.");
        for (int i = 0; i < ALPH_SIZE; i++) {
            String code = valToCode.get(i);
            if (code != null) {
                showString("value: " + i + ", equivalent char: " + (char) i + ", " +
                "frequency:  " + chunkFreqs[i] + ", new code: " + code);
            }
        }
        showString("value: " + 256 + ", equivalent char: " + (char) 256 + ", " +
                "frequency:  " + 1 + ", new code: " + valToCode.get(256));
        showString("");
        showString("Uncompressed file size: " + (uncompressedBits));
        showString("Compressed file size: " + compressedBits);
        showString("");
    }

    // Get the number of header bits based on header type (SCF or STF)
    private int getHeaderBits() {
        int res = 0;
        if (headerType == STORE_TREE) {
            res += BITS_PER_INT;
            for (int i = 1; i < treeRepresentation.size(); i++) {
                if (treeRepresentation.get(i) == 1) {
                    res += HuffTree.BITS_PER_LEAF_NODE;
                    i++;
                }

                res += 1;
            }
        } else {
            res = chunkFreqs.length * BITS_PER_INT;
        }

        return res;
    }

    // store frequency of each char, and calculate and return 
    private int getIntsArray(InputStream in) throws IOException {
        int count = 0;
        chunkFreqs = new int[ALPH_SIZE];
        BitInputStream bits = new BitInputStream(in);
        int inbits = bits.readBits(BITS_PER_WORD);

        while (inbits != -1) {
            count += BITS_PER_WORD;
            chunkFreqs[inbits]++;
            inbits = bits.readBits(BITS_PER_WORD);
        }

        bits.close();
        return count;
    }

    // Get priority queue of chunks based on frequency
    private PriorityQueue314<TreeNode> nodeMaker() {
        PriorityQueue314<TreeNode> pq = new PriorityQueue314<>();
        for (int i = 0; i < chunkFreqs.length; i++) {
            if (chunkFreqs[i] != 0) {
                TreeNode newNode = new TreeNode(i, chunkFreqs[i]);
                pq.enqueue(newNode);
            }
        }
        
        TreeNode PEOFNode = new TreeNode(IHuffConstants.PSEUDO_EOF, 1);
        pq.enqueue(PEOFNode);
        return pq;
    }

    // Get Huffcode Tree
    private void makeHuffTree(PriorityQueue314<TreeNode> pq) {
        while (!pq.onlyOne()) {
            TreeNode left = pq.dequeue();
            TreeNode right = pq.dequeue();
            TreeNode parent = new TreeNode(-1, left.getFrequency() + right.getFrequency());
            parent.setLeft(left);
            parent.setRight(right);
            pq.enqueue(parent);
        }

        codeTree = new HuffTree(pq.dequeue());
    }

    /**
	 * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        if (saved < 0 && !force) {
            showString("saved: " + saved);
            myViewer.showError("Compressed file has " + -1 * saved + " more bits than uncompressed file. " +
            "Select \"force compression\" option to compress.");
        } else if (saved >= 0 || force) {
            BitOutputStream outStream = new BitOutputStream(out);
            outStream.writeBits(BITS_PER_INT, MAGIC_NUMBER);
            writeHeaderBits(outStream);

            BitInputStream bits = new BitInputStream(in);
            int inbits = bits.readBits(BITS_PER_WORD);

            while (inbits != -1) {
                String code = valToCode.get(inbits);
                for (int i = 0; i < code.length(); i++) {
                    char bit = code.charAt(i);
                    if (bit == '0') {
                        outStream.writeBits(1, 0);
                    } else {
                        outStream.writeBits(1, 1);
                    }
                }
                inbits = bits.readBits(BITS_PER_WORD);
            }

            // Write PseudoEOF
            String PEOFCode = valToCode.get(PSEUDO_EOF);
            for (int i = 0; i < PEOFCode.length(); i++) {
                char bit = PEOFCode.charAt(i);
                    if (bit == '0') {
                        outStream.writeBits(1, 0);
                    } else {
                        outStream.writeBits(1, 1);
                    }
            }
            bits.close();
            outStream.close();
        }
        return compressedBits;
    }

    // Write header bits to output stream based on header type 
    private void writeHeaderBits(BitOutputStream outStream) {
        if (headerType == STORE_TREE) {
            outStream.writeBits(BITS_PER_INT, STORE_TREE);

            // Write size of the tree first
            outStream.writeBits(BITS_PER_INT, treeRepresentation.get(0));

            // At leaf node, write BITS_PER_LEAF_NODE bits with next index as value
            for (int i = 1; i < treeRepresentation.size(); i++) {
                int curr = treeRepresentation.get(i);
                outStream.writeBits(1, curr);
                if (curr == 1) {
                    i++;
                    outStream.writeBits(HuffTree.BITS_PER_LEAF_NODE, treeRepresentation.get(i));
                }
            }
        } else {
            outStream.writeBits(BITS_PER_INT, STORE_COUNTS);
            for (int i = 0; i < chunkFreqs.length; i++) {
                outStream.writeBits(BITS_PER_INT, chunkFreqs[i]);
            }
        }
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
	        BitInputStream inStream = new BitInputStream(in);
            int magic = inStream.readBits(BITS_PER_INT);
            if (magic != MAGIC_NUMBER) {
                myViewer.showError("Error in reading compressed file: " + 
                                    "File did not start with magic number.");
                inStream.close();
                return -1;
            }
            headerType = inStream.readBits(BITS_PER_INT);
            processHeader(inStream);
            BitOutputStream outStream = new BitOutputStream(out);
            return decodeCompression(inStream, outStream);

    }

    // Helper method to decompress the actual data of the file, adapted from how to page
    private int decodeCompression(BitInputStream inStream, BitOutputStream outStream)
                                    throws IOException {
        int res = 0;
        boolean finished = false;
        while (!finished) {
            TreeNode curr = codeTree.getRoot();
            boolean found = false;
            while (!found) {
                int bit = inStream.readBits(1);
                if (bit == -1) {
                    throw new IOException("Error in reading compressed file: " + 
                                            "Unexpected end of input, no PEOF value.");
                } else {
                    if (bit == 0) {
                        curr = curr.getLeft();
                    } else if (bit == 1) {
                        curr = curr.getRight();
                    }

                    if (curr.getLeft() == null && curr.getRight() == null) {
                        if (curr.getValue() == PSEUDO_EOF) {
                            finished = true;
                        } else {
                            outStream.writeBits(BITS_PER_WORD, curr.getValue());
                            res += BITS_PER_WORD;
                        }
                        found = true;
                    }
                }
            }
        }

        inStream.close();
        outStream.close();
        return res;
    }

    // Helper method to decode the header and build the HuffTree for decompression
    private void processHeader(BitInputStream inStream) throws IOException {
        if (headerType != STORE_TREE && headerType != STORE_COUNTS) {
            myViewer.showError("Error in reading compressed file: Header Format is not valid.");
        }
        if (headerType == STORE_TREE) {
            buildTree(inStream);
        } else {
            chunkFreqs = new int[ALPH_SIZE];
            for (int i = 0; i < chunkFreqs.length; i++) {
                chunkFreqs[i] = inStream.readBits(BITS_PER_INT);
            }

            PriorityQueue314<TreeNode> pq = nodeMaker();
            makeHuffTree(pq);
        }

        valToCode = codeTree.getCodes();
    }

    // Reconstructs the HuffTree during decompression
    private void buildTree(BitInputStream inStream) throws IOException {
        int treeSize = inStream.readBits(BITS_PER_INT);
        TreeNode root = buildTreeHelper(inStream);
        codeTree = new HuffTree(root);
    }

    // Recursive helper method to rebuild tree, adapted from second how to page
    private TreeNode buildTreeHelper(BitInputStream inStream) throws IOException  {
        int bit = inStream.readBits(1);
        if (bit == 0) {
            TreeNode node = new TreeNode(-1, -1);
            node.setLeft(buildTreeHelper(inStream));
            node.setRight(buildTreeHelper(inStream));
            return node;
        } else if (bit == 1) {
            int value = 0;
            for (int i = 1; i <= HuffTree.BITS_PER_LEAF_NODE; i++) {
                bit = inStream.readBits(1);
                if (bit == 1) {
                    value += Math.pow(2, HuffTree.BITS_PER_LEAF_NODE - i);
                }
            }
            if (value < ALPH_SIZE) {
                return new TreeNode(value, chunkFreqs[value]);
            } else {
                return new TreeNode(value, 1);

            }
        } else {
            myViewer.showError("Error in reading compressed file: Incorrect format.");
            return null;
        }
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s){
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}
