package ru.makhmedov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_TABLE_SIZE = 5;

    private final LinkedList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (LinkedList<E>[]) new LinkedList[DEFAULT_TABLE_SIZE];
    }

    public HashTable(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше 0. Текущее значение: " + tableSize);
        }

        //noinspection unchecked
        lists = (LinkedList<E>[]) new LinkedList[tableSize];
    }

    private int getIndex(Object item) {
        if (item == null) {
            return 0;
        }

        return Math.abs(item.hashCode() % lists.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(lists);
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
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private final int expectedModCount = modCount;
        private int visitedItemsCount;
        private int currentListIndex = -1;
        private Iterator<E> listIterator;

        @Override
        public boolean hasNext() {
            return visitedItemsCount < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Хэш-таблица закончилась.");
            }

            if (listIterator == null || !listIterator.hasNext()) {
                while (true) {
                    currentListIndex++;

                    if (lists[currentListIndex] != null && !lists[currentListIndex].isEmpty()) {
                        listIterator = lists[currentListIndex].listIterator();
                        break;
                    }
                }
            }

            visitedItemsCount++;

            return listIterator.next();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (E item : this) {
            array[i] = item;
            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(a, size, a.getClass());
        }

        int i = 0;

        for (E item : this) {
            //noinspection unchecked
            a[i] = (T) item;
            i++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }

        lists[index].add(item);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
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
        if (isEmpty()) {
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

        int initialSize = size;

        for (LinkedList<E> list : lists) {
            if (list != null) {
                int listSize = list.size();
                list.removeAll(c);

                size -= listSize - list.size();
            }
        }

        if (initialSize != size) {
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (LinkedList<E> item : lists) {
            if (item != null) {
                int listSize = item.size();
                item.retainAll(c);

                size -= listSize - item.size();
            }
        }

        if (initialSize != size) {
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(lists, null);

        modCount++;
        size = 0;
    }
}
