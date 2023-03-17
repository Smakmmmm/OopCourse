package ru.makhmedov.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Некорректная вместимость" + capacity);
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
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
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            ++currentIndex;

            if (currentIndex >= size) {
                throw new NoSuchElementException("Список закончился.");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {

            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void grow() {
        items = Arrays.copyOf(items, size + 5);
    }

    private void grow(int newLength) {
        items = Arrays.copyOf(items, newLength);
    }

    @Override
    public boolean add(T element) {
        modCount++;

        if (size == items.length) {
            grow();
        }

        items[size] = element;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int searchElementIndex = indexOf(o);

        if (searchElementIndex == -1) {
            return false;
        }

        remove(searchElementIndex);

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
    public boolean addAll(Collection<? extends T> c) {
        modCount++;

        //noinspection unchecked
        T[] addedArray = (T[]) c.toArray();
        int addedLength = addedArray.length;

        if (addedLength == 0) {
            return false;
        }

        if (addedLength > items.length - size) {
            grow(size + addedLength);
        }

        System.arraycopy(addedArray, 0, items, size, addedLength);
        size = size + addedLength;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index);

        //noinspection unchecked
        T[] addedArray = (T[]) c.toArray();
        int addedLength = addedArray.length;

        if (addedLength == 0) {
            return false;
        }

        if (addedLength > items.length - size) {
            grow(size + addedLength);
        }

        int slidingPartSize = size - index;
        System.arraycopy(items, index, items, index + addedLength, slidingPartSize);
        System.arraycopy(addedArray, 0, items, index, addedLength);
        size = size + addedLength;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int i = 0;

        while (true) {
            if (i == size) {
                return false;
            }

            if (c.contains(items[i])) {
                break;
            }

            i++;
        }

        int j = i++;

        while (i < size) {
            if (!c.contains(items[i])) {
                items[j++] = items[i];
            }

            i++;
        }

        modCount += size - j;

        for (i = j; i < size; i++) {
            items[j] = null;
        }

        size -= size - j;

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0;

        while (true) {
            if (i == size) {
                return false;
            }

            if (!c.contains(items[i])) {
                break;
            }

            i++;
        }

        int j = i++;

        while (i < size) {
            if (c.contains(items[i])) {
                items[j++] = items[i];
            }

            i++;
        }

        modCount += size - j;

        for (i = j; i < size; i++) {
            items[j] = null;
        }

        size -= size - j;

        return true;
    }

    @Override
    public void clear() {
        modCount++;

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        T previousItem = items[index];
        items[index] = element;

        return previousItem;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);

        modCount++;

        if (size == items.length) {
            grow();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс за границами диапазона: (0, " + --size + ")" + "Индекс: " + index);
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        modCount++;
        T removedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
