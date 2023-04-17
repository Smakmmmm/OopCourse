package ru.makhmedov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_TABLE_SIZE = 5;

    private final LinkedList<E>[] table;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        table = (LinkedList<E>[]) new LinkedList[DEFAULT_TABLE_SIZE];
    }

    public HashTable(int tableSize) {
        if (tableSize < 0) {
            throw new IllegalArgumentException("Вместимость не может быть меньше 0: " + tableSize);
        }

        //noinspection unchecked
        table = (LinkedList<E>[]) new LinkedList[tableSize];
    }

    private int getIndex(E item) {
        return Math.abs(item.hashCode() % table.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
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
        //noinspection unchecked
        int tableIndex = getIndex((E) o);

        if (table[tableIndex] == null) {
            return false;
        }

        return table[tableIndex].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements java.util.Iterator<E> {
        private final int expectedModCount = modCount;
        private int itemCount = 0;
        private int currentIndex = 0;
        Iterator<E> bucketIterator;

        @Override
        public boolean hasNext() {
            return itemCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Хэш-таблица закончилась.");
            }

            if (bucketIterator == null || !bucketIterator.hasNext()) {
                for (; hasNext(); currentIndex++) {
                    if (table[currentIndex] != null) {
                        bucketIterator = table[currentIndex].listIterator();
                        currentIndex++;
                        break;
                    }
                }
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена.");
            }

            itemCount++;

            assert bucketIterator != null;
            return bucketIterator.next();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        Iterator<E> iterator = iterator();

        for (int i = 0; i < size; i++) {
            array[i] = iterator.next();
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        }

        Iterator<E> iterator = iterator();

        for (int i = 0; i < size; i++) {
            //noinspection unchecked
            a[i] = (T) iterator.next();
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E t) {
        if (t == null) {
            return false;
        }

        int itemIndex = getIndex(t);

        if (table[itemIndex] == null) {
            table[itemIndex] = new LinkedList<>();
        }

        table[itemIndex].add(t);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        //noinspection unchecked
        int itemIndex = getIndex((E) o);

        if (table[itemIndex] == null) {
            return false;
        }

        if (table[itemIndex].remove(o)) {
            modCount++;
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }

        for (E item : c) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty() || isEmpty()) {
            return false;
        }

        boolean hasChanged = false;

        for (LinkedList<E> item : table) {
            if (item != null) {
                int listSize = item.size();
                item.removeAll(c);

                size -= listSize - item.size();

                hasChanged = true;
            }
        }

        if (hasChanged) {
            modCount++;
        }

        return hasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty() || isEmpty()) {
            return false;
        }

        boolean hasChanged = false;

        for (LinkedList<E> item : table) {
            if (item != null) {
                int listSize = item.size();
                item.retainAll(c);

                size -= listSize - item.size();

                hasChanged = true;
            }
        }

        if (hasChanged) {
            modCount++;
        }

        return hasChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(table, null);

        modCount++;
        size = 0;
    }
}
