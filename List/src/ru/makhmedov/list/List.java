package ru.makhmedov.list;

import java.util.NoSuchElementException;

public class List<T> {
    private int count;
    private Node<T> head;

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Сейчас индекс: " + index + ". Границы: (0, " + --count + ")");
        }
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> node = head;

        for (int i = 0; i != index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public T getByIndex(int index) {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        checkIndex(index);

        return getNodeByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index);

        Node<T> node = getNodeByIndex(index);

        T oldData = node.getData();
        node.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        Node<T> previousNode = getNodeByIndex(index - 1);
        T removedData = previousNode.getNext().getData();

        previousNode.setNext(previousNode.getNext().getNext());

        count--;

        return removedData;
    }

    public void addFirst(T data) {
        head = new Node<>(data, head);
        count++;
    }

    public void addByIndex(int index, T data) {
        if (index == 0) {
            addFirst(data);
        } else {
            checkIndex(index);

            Node<T> previousNode = getNodeByIndex(index - 1);

            previousNode.setNext(new Node<>(data, previousNode.getNext()));
        }
    }

    public boolean removeByData(T data) {
        if (head == null) {
            return false;
        }

        if (data.equals(head.getData())) {
            removeFirst();
            return true;
        }

        for (Node<T> current = head.getNext(), previous = head; current != null; previous = current, current = current.getNext()) {
            if (data.equals(current.getData())) {
                previous.setNext(current.getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        T removedData = head.getData();

        head = head.getNext();
        count--;

        return removedData;
    }

    public void revert() {
        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            Node<T> temporaryNode = current.getNext();

            current.setNext(previous);
            previous = current;
            head = current;

            current = temporaryNode;
        }
    }

    public List<T> copy() {
        if (head == null) {
            throw new NoSuchElementException("Копируемые массив пуст.");
        }

        List<T> copy = new List<>();
        Node<T> newNode = new Node<>(head.getData());

        copy.head = newNode;
        copy.count = count;

        Node<T> lastNode = newNode;

        for (Node<T> current = head.getNext(); current != null; current = current.getNext()) {
            newNode = new Node<>(current.getData());
            lastNode.setNext(newNode);
            lastNode = newNode;
        }

        return copy;
    }

    @Override
    public String toString() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст.");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (Node<T> current = head; current != null; current = current.getNext()) {
            stringBuilder.append(current.getData());
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.append("]").toString();
    }
}
