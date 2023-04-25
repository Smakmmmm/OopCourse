package ru.makhmedov.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость не может быть меньше нуля! Указанная вместимость: " + capacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
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
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен.");
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (E[]) new Object[DEFAULT_CAPACITY];
            return;
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int searchItemIndex = indexOf(o);

        if (searchItemIndex == -1) {
            return false;
        }

        remove(searchItemIndex);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс за границами диапазона: (0, " + size + "). Индекс: " + index);
        }

        int collectionSize = c.size();

        if (collectionSize == 0) {
            return false;
        }

        ensureCapacity(size + collectionSize);
        System.arraycopy(items, index, items, index + collectionSize, size - index);

        int i = index;

        for (E item : c) {
            items[i] = item;
            i++;
        }

        size += collectionSize;
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int i = 0;

        for (; !c.contains(items[i]); i++) {
            if (i == size) {
                return false;
            }
        }

        int j = i;
        i++;

        for (; i < size; i++) {
            if (!c.contains(items[i])) {
                items[j] = items[i];
                j++;
            }
        }

        Arrays.fill(items, j, size, null);

        modCount++;
        size = j;

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;

        for (; c.contains(items[i]); i++) {
            if (i == size) {
                return false;
            }
        }

        int j = i;
        i++;

        for (; i < size; i++) {
            if (c.contains(items[i])) {
                items[j] = items[i];
                j++;
            }
        }

        Arrays.fill(items, j, size, null);

        modCount++;
        size = j;

        return true;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, null);

        modCount++;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс за границами диапазона: (0, " + size + "). Индекс: " + index);
        }

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        modCount++;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс за границами диапазона: (0, " + (size - 1) + "). Индекс: " + index);
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        modCount++;
        size--;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        //noinspection unchecked
        ArrayList<E> otherArrayList = (ArrayList<E>) o;

        if (size != otherArrayList.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(items[i], otherArrayList.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (E item : items) {
            if (item == null) {
                hash = prime * hash;
            } else {
                hash = prime * hash + item.hashCode();
            }
        }

        return hash;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
