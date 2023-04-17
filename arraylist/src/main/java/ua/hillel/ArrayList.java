package ua.hillel;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implement a list that is based on array. This resizable data structure
 * is based on an array and is a simplified version of {@link java.util.ArrayList}.
 */
public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[10];
    }

    private void resize() {
        if (elements.length == size) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public void add(T element) {
        resize();
        elements[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size);
        resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[size++] = element;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public T get(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return (T) elements[i];
            }
        }
        return null;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) elements[0];
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) elements[size - 1];
    }

    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        elements[index] = element;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return true;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
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
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}
