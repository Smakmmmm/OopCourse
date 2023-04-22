package ru.makhmedov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

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
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка. Сейчас индекс: " + index + ". Границы: (0, " + (count - 1) + ")");
        }
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public T getByIndex(int index) {
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
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Недопустимый индекс. Сейчас индекс: " + index + ". Границы: (0, " + (count - 1) + ")");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        Node<T> previousNode = getNodeByIndex(index - 1);

        previousNode.setNext(new Node<>(data, previousNode.getNext()));
        count++;
    }

    public boolean removeByData(T data) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(data, head.getData())) {
            removeFirst();
            return true;
        }

        for (Node<T> currentNode = head.getNext(), previousNode = head; currentNode != null; previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (Objects.equals(data, currentNode.getData())) {
                previousNode.setNext(currentNode.getNext());
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
        Node<T> currentNode = head;
        Node<T> previousNode = null;

        while (currentNode != null) {
            Node<T> nextNode = currentNode.getNext();

            currentNode.setNext(previousNode);
            previousNode = currentNode;
            head = currentNode;

            currentNode = nextNode;
        }
    }

    public List<T> copy() {
        if (head == null) {
            return new List<>();
        }

        List<T> copy = new List<>();

        copy.head = new Node<>(head.getData());
        copy.count = count;

        Node<T> lastCopyNode = copy.head;

        for (Node<T> currentNode = head.getNext(); currentNode != null; currentNode = currentNode.getNext()) {
            lastCopyNode.setNext(new Node<>(currentNode.getData()));
            lastCopyNode = lastCopyNode.getNext();
        }

        return copy;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (Node<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            stringBuilder.append(currentNode.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.append(']').toString();
    }
}
