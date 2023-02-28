package ru.makhmedov.list;

public class List<T> {
    private int count;
    private Node<T> head;

    public int getCount() {
        return count;
    }

    public T getFirstElement() {
        if (head == null) {
            throw new NullPointerException("Список пуст.");
        }

        return head.getData();
    }

    public Node<T> getElementByIndex(int index) {
        if (index < 0 || index > count - 1) {
            throw new IndexOutOfBoundsException("Индекс за пределами размера списка.");
        }

        Node<T> p = head;

        for (int i = 0; i != index; i++) {
            p = p.getNext();
        }

        return p;
    }

    public T getElementDateByIndex(int index) {
        return getElementByIndex(index).getData();
    }

    public T changeElementDateByIndex(int index, T newDate) {
        T currentDate = getElementByIndex(index).getData();
        getElementByIndex(index).setData(newDate);

        return currentDate;
    }

    public T removeByIndex(int index) {
        if (index == 0) {
            return removeFirstElement();
        }

        Node<T> elementBeforeRemoved = getElementByIndex(index - 1);
        T remoteDate = elementBeforeRemoved.getNext().getData();

        elementBeforeRemoved.setNext(elementBeforeRemoved.getNext().getNext());

        count--;

        return remoteDate;
    }

    public void addFront(T data) {
        Node<T> newElement = new Node<>(data);

        if (head != null) {
            newElement.setNext(head);
        }

        head = newElement;
        count++;
    }

    public void addByIndex(int index, T data) {
        if (index == 0) {
            addFront(data);
        } else {
            Node<T> elementBeforeAdded = getElementByIndex(index - 1);
            Node<T> newElement = new Node<>(data, elementBeforeAdded.getNext());

            elementBeforeAdded.setNext(newElement);
        }
    }

    public boolean removeByDate(T date) {
        if (head == null) {
            return false;
        }

        if (date == head.getData()) {
            removeFirstElement();
            count--;
            return true;
        }

        for (Node<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (date == p.getData()) {
                prev.setNext(p.getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    public T removeFirstElement() {
        T remoteDate = head.getData();

        head = head.getNext();
        count--;

        return remoteDate;
    }

    public void revert() {
        Node<T> p = head;
        Node<T> prev = null;

        while (p != null) {
            Node<T> temp = p.getNext();

            p.setNext(prev);
            prev = p;
            head = p;

            p = temp;
        }
    }

    public List<T> copy() {
        List<T> copy = new List<>();
        Node<T> newElement = new Node<>();

        copy.head = newElement;
        copy.count = 1;

        newElement.setData(head.getData());
        Node<T> lastElement = newElement;

        for (Node<T> p = head.getNext(); p != null; p = p.getNext()) {
            newElement = new Node<>();
            newElement.setData(p.getData());
            lastElement.setNext(newElement);
            lastElement = newElement;

            copy.count++;
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder();
        stringList.append("[");

        for (Node<T> p = head; ; p = p.getNext()) {
            stringList.append(p.getData());

            if (p.getNext() == null) {
                return stringList.append("]").toString();
            }

            stringList.append(", ");
        }
    }
}
