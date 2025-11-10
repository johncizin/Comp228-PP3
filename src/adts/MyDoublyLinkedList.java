// Fisher–Yates (Knuth) shuffle 
// An array-based shuffling algorithm that randomly selects an element 
// and swaps it with the element at the current position (i). 
// Starting from the last element (index n-1), it iterates backward through the array. 
// For each position i, it chooses a random index j between 0 and i (i included)
// and swaps elements at positions i and j.
//1)Start at the last index (i = n - 1).

//2)Choose a random index j between 0 and i (inclusive).

//3)Swap the elements at positions i and j.

//4)Decrease i by 1.

//5)Repeat steps 2–4 until you reach the beginning.
package adts;

import interfaces.ListInterface;
import nodes.DLLNode;
import java.util.*;

public class MyDoublyLinkedList<E extends Comparable<E>> implements ListInterface<E>, Iterable<E> {
    private DLLNode<E> head;
    private DLLNode<E> tail;
    private int size;

    public MyDoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    // --- BASIC ADD METHOD ---
    public void add(E element) {
        DLLNode<E> newNode = new DLLNode<>(element);
        if (head == null) { // first node
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    // --- REMOVE METHOD ---
    @Override
    public boolean remove(E element) {
        if (element == null) {
            return false;
        }
        
        DLLNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                // Found the element to remove
                DLLNode<E> prevNode = current.getPrev();
                DLLNode<E> nextNode = current.getNext();
                
                if (prevNode != null) {
                    prevNode.setNext(nextNode);
                } else {
                    // current is the head
                    head = nextNode;
                }
                
                if (nextNode != null) {
                    nextNode.setPrev(prevNode);
                } else {
                    // current is the tail
                    tail = prevNode;
                }
                
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // --- SIZE METHOD ---
    @Override
    public int size() {
        return size;
    }

    // --- IS EMPTY METHOD ---
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // --- CONTAINS METHOD ---
    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        
        DLLNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // --- GET METHOD (BY ELEMENT) ---
    @Override
    public E get(E element) {
        if (element == null) {
            return null;
        }
        
        DLLNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    // --- GET METHOD (BY INDEX) ---
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        
        DLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    // --- RANDOM ITERATOR PART  ---
    @Override
    public Iterator<E> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<E> {
        private Object[] shuffledArray;
        private int currentIndex = 0;

        public RandomIterator() {
            // Step 1: copy list data into array
            List<E> tempList = new ArrayList<>();
            DLLNode<E> current = head;
            while (current != null) {
                tempList.add(current.getData());
                current = current.getNext();
            }

            shuffledArray = tempList.toArray();

            // Step 2: Fisher-Yates shuffle (manual random shuffle)
            Random rand = new Random();
            for (int i = shuffledArray.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                Object temp = shuffledArray[i];
                shuffledArray[i] = shuffledArray[j];
                shuffledArray[j] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < shuffledArray.length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            return (E) shuffledArray[currentIndex++];
        }
    }
}
