/*/
Reverse Order Doubly Linked List Implementation:
    Extends Comparable [X]
    Implements ListInterface<E> [X]
    Implements Iterable<E> [X]
    
    ** Switcing between search contexts: **


*/

package adts;

import interfaces.ListInterface;
import nodes.DLLNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReverseOrderDLL<E extends Comparable<E>> implements ListInterface<E>, Iterable<E> {

    private DLLNode<E> head;
    private DLLNode<E> tail;
    private int size;
    // I start with sequential search because it's the basic one we coded first in class
    private SearchContext searchContext = SearchContext.SEQUENTIAL;

    // Enum for search context types could be strings or booleans but enums are cleaner
    public enum SearchContext {
        SEQUENTIAL,
        BINARY
    }

    public ReverseOrderDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    //mutator for search context
    public void setSearchContext(SearchContext context) {
        if (context != null) {
            this.searchContext = context;
        }
    }

    @Override
    public void add(E element) {
        //edge case
        if (element == null) {
            return; //could default later?
        }

        DLLNode<E> newNode = new DLLNode<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size = 1;
            return;
        }

        // drop it at the front if its the biggest so far
        if (element.compareTo(head.getData()) >= 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
            size++;
            return;
        }

        if (element.compareTo(tail.getData()) <= 0) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            size++;
            return;
        }

        DLLNode<E> current = head;
        while (current != null && current.getData().compareTo(element) > 0) {
            current = current.getNext();
        }

        DLLNode<E> previous = current.getPrev();
        previous.setNext(newNode);
        newNode.setPrev(previous);
        newNode.setNext(current);
        current.setPrev(newNode);
        size++;
    }

    @Override
    public boolean remove(E element) {
        if (element == null || size == 0) {
            return false;
        }

        DLLNode<E> target = find(element);
        if (target == null) {
            return false;
        }

        DLLNode<E> prevNode = target.getPrev();
        DLLNode<E> nextNode = target.getNext();

        if (prevNode != null) {
            prevNode.setNext(nextNode);
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.setPrev(prevNode);
        } else {
            tail = prevNode;
        }

        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        if (element == null || size == 0) {
            return false;
        }
        return find(element) != null;
    }

    @Override
    public E get(E element) {
        if (element == null || size == 0) {
            return null;
        }
        DLLNode<E> target = find(element);
        return target != null ? target.getData() : null;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        if (index == 0) {
            return head.getData();
        }
        if (index == size - 1) {
            return tail.getData();
        }

        DLLNode<E> current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current.getData();
    }

    @Override
    public Iterator<E> iterator() {
        return new DescendingIterator();
    }

    //testing purposes here:
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        DLLNode<E> current = head;
        while (current != null) {
            builder.append(current.getData());
            current = current.getNext();
            if (current != null) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    // helper method switches between the binary and sequential search
    private DLLNode<E> find(E element) {
        if (element == null || size == 0) {
            return null;
        }
        // ternary: determine calls based on search context state
        return searchContext == SearchContext.BINARY ? binarySearch(element) : sequentialSearch(element);
    }

    // sequential:
    private DLLNode<E> sequentialSearch(E element) {
        DLLNode<E> current = head;
        while (current != null) {
            int comparison = current.getData().compareTo(element);
            if (comparison == 0) {
                return current;
            } else if (comparison < 0) {
                return null;
            }
            current = current.getNext();
        }
        return null;
    }

    // binary:
   private DLLNode<E> binarySearch(E element) {
    int low = 0;
    int high = size - 1;

    while (low <= high) {
        int mid = (low + high) >>> 1;

        // Inline nodeAt(mid)
        DLLNode<E> current;
        if (mid <= size / 2) {
            current = head;
            for (int i = 0; i < mid; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > mid; i--) {
                current = current.getPrev();
            }
        }

        // Compare
        int comparison = current.getData().compareTo(element);

        if (comparison == 0) {
            return current;
        } else if (comparison < 0) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return null;
}

    private class DescendingIterator implements Iterator<E> {
        private DLLNode<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            //edge case
            if (current == null) {
                throw new NoSuchElementException();
            }
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }

}
