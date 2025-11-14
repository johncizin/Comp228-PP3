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

    public ReverseOrderDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    // Search context: Could use String or boolean but enum is cleaner
    public enum SearchSContext {
        SEQUENTIAL,
        BINARY
    }

    @Override
    public void add(E element) {
        if (element == null) {
            return;
        }

        DLLNode<E> newNode = new DLLNode<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size = 1;
            return;
        }

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

        SearchResult<E> result = find(element);
        if (!result.found) {
            return false;
        }

        DLLNode<E> target = result.node;
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
        return find(element).found;
    }

    @Override
    public E get(E element) {
        if (element == null || size == 0) {
            return null;
        }
        SearchResult<E> result = find(element);
        return result.found ? result.node.getData() : null;
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

    private SearchResult<E> find(E element) {
        SearchResult<E> result = new SearchResult<>();
        if (element == null || size == 0) {
            return result;
        }

        DLLNode<E> current = head;
        DLLNode<E> previous = null;
        int index = 0;

        while (current != null) {
            int comparison = current.getData().compareTo(element);
            if (comparison == 0) {
                result.node = current;
                result.previous = previous;
                result.index = index;
                result.found = true;
                return result;
            } else if (comparison < 0) {
                return result;
            }
            previous = current;
            current = current.getNext();
            index++;
        }
        return result;
    }

    private static class SearchResult<E> {
        private DLLNode<E> node;
        private DLLNode<E> previous;
        private int index = -1;
        private boolean found = false;
    }

    private class DescendingIterator implements Iterator<E> {
        private DLLNode<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
