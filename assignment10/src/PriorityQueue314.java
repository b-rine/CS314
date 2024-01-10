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

public class PriorityQueue314<E extends Comparable<? super E>> {
    
    private PQNode<E> head;
    private PQNode<E> tail;

    // Constructor for PQ
    public PriorityQueue314() {
        head = null;
        tail = null;
    }

    // Returns true if there is only one element in the PQ, false otherwise
    public boolean onlyOne() {
        if (head == null) {
            throw new IllegalArgumentException("Head is null, no nodes in the queue.");
        }

        return head.next == null;
    }

    // Enqueue input node in the proper place (breaks ties fairly)
    public void enqueue(E data) {
        PQNode<E> newNode = new PQNode<>(null, data, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            PQNode<E> pointer = tail;
            boolean inserted = false;
            while (pointer != null && !inserted) {
                int comparison = data.compareTo(pointer.data);
                if (comparison >= 0) {
                    inserted = true;
                    PQNode<E> temp = pointer.next;
                    pointer.next = new PQNode<>(pointer, data, temp);
                    if (pointer == tail) {
                        tail = pointer.next;
                    } else {
                        temp.prev = pointer.next;
                    }
                } else {
                    pointer = pointer.prev;
                }
            }
            if (!inserted) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        }

    }

    // Dequeue node from the front of the queue
    public E dequeue() {
        E res = head.data;
        head = head.next;
        if (!isEmpty()) {
            head.prev = null;
        }
        return res;
    }

    // Return true if PQ is empty, false otherwise
    public boolean isEmpty() {
        return head == null;
    }

    // Class for PQ nodes
    private static class PQNode<E> {
        
        private E data;
        private PQNode<E> next;
        private PQNode<E> prev;

        // Constructor for PQNode
        public PQNode(PQNode<E> prev, E data, PQNode<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    } 
}
