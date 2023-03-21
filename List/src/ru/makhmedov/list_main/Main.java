package ru.makhmedov.list_main;

import ru.makhmedov.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();

        for (int i = 0; i <= 5; i++) {
            list.addFirst(i);
        }

        System.out.println("Список: " + list);
        System.out.println("Длина списка: " + list.getCount());
        System.out.println("Первый элемент списка: " + list.getFirst());

        System.out.println("Элемент под индексом 4: " + list.getByIndex(4));

        list.revert();

        System.out.println();
        System.out.println("Список после реверса: " + list);

        List<Integer> listCopy = list.copy();

        System.out.println("Копия списка: " + listCopy);

        if (list.removeByData(null)) {
            System.out.println("Удаление элемента: " + list);
        }

        System.out.println("Оригинальный список: " + list);
        System.out.println("Копия списка: " + listCopy);

        System.out.println("Длина копии = " + listCopy.getCount());
        System.out.println("Длина списка = " + list.getCount());

        System.out.println("Старое значение элемента под индексом 0: " + listCopy.setByIndex(0, 55));
        System.out.println("Копия списка после изменения значения элемента: " + listCopy);

        System.out.println("Значение удаленного элемента: " + listCopy.removeByIndex(0));
        System.out.println("Копия списка теперь: " + listCopy);

        listCopy.addByIndex(4, 23);
        System.out.println("Копия после добавления в нее элемента по индексу 4: " + listCopy);
    }
}
