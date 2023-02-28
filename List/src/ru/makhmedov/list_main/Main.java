package ru.makhmedov.list_main;

import ru.makhmedov.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> myList = new List<>();

        for (int i = 0; i <= 5; i++) {
            myList.addFront(i);
        }

        System.out.println("Список: " + myList);
        System.out.println("Длина списка" + myList.getCount());
        System.out.println("Первый элемент списка: " + myList.getFirstElement());

        System.out.println("Элемент под индексом 4: " + myList.getElementDateByIndex(4));

        myList.revert();

        System.out.println();
        System.out.println("Список после реверса: " + myList);

        List<Integer> myListCopy = myList.copy();

        System.out.println("Копия списка: " + myListCopy);

        if (myList.removeByDate(3)) {
            System.out.println("Удаление элемента: " + myList);
        }

        System.out.println("Оригинальный список: " + myList);
        System.out.println("Копия списка: " + myListCopy);

        System.out.println("Длина копии = " + myListCopy.getCount());
        System.out.println("Длина списка = " + myList.getCount());

        System.out.println("Старое значение элемента под индексом 0: " + myListCopy.changeElementDateByIndex(0, 55));
        System.out.println("Копия списка после изменения значения элемента: " + myListCopy);

        System.out.println("Значение удаленного элемента: " + myListCopy.removeByIndex(0));
        System.out.println("Копия списка теперь: " + myListCopy);

        myListCopy.addByIndex(4, 23);
        System.out.println("Копия после добавления в нее элемента по индексу 4: " + myListCopy);
    }
}
