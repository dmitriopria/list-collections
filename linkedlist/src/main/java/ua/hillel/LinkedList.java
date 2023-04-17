package ua.hillel;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implement doubly linked list that is based on {@link Node<T>} class (you should implement it as well).
 * This is a simplified version of {@link java.util.LinkedList}.
 */
public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        Node(T element) {
            this.element = element;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    private Node<T> getNode(int index) {
        Node<T> curr;
        if (index < size / 2) {
            curr = first;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = last;
            for (int i = size - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;
        } else if (index == 0) {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        } else if (index == size) {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        } else {
            Node<T> previousIndex = getNode(index - 1);
            newNode.next = previousIndex.next;
            newNode.prev = previousIndex;
            previousIndex.next = newNode;
            newNode.next.prev = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = getNode(index);
        return node.element;
    }

    @Override
    public T get(T element) {
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element)) {
                return current.element;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        Node<T> node = getNode(index);
        node.element = element;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index > size) {
            return false;
        }
        if (index == 0) {
            first = first.next;
            if (first == null) {
                last = null;
            } else {
                first.prev = null;
            }
        } else if (index == size - 1) {
            last = last.prev;
            last.next = null;
        } else {
            Node<T> previous = getNode(index - 1);
            previous.next = previous.next.next;
            previous.next.prev = previous;
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }
}
