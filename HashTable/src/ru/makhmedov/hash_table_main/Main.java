package ru.makhmedov.hash_table_main;

import ru.makhmedov.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(4);
        hashTable.add(5);
        System.out.println(hashTable);

        HashTable<Integer> anotherHashTable = new HashTable<>(10);
        anotherHashTable.add(1);
        anotherHashTable.add(2);
        anotherHashTable.add(3);
        anotherHashTable.add(5);
        anotherHashTable.add(null);
        System.out.println(anotherHashTable);

        anotherHashTable.remove(6);
        System.out.println(anotherHashTable);

        System.out.println("Проверка RetainAll:");
        List<Integer> listForRetainAllCheck = new ArrayList<>();
        anotherHashTable.retainAll(listForRetainAllCheck);
        System.out.println(anotherHashTable + " Размер: " + anotherHashTable.size());

        System.out.println("Проверка RemoveAll:");
        List<Integer> listForRemoveAllCheck = new ArrayList<>(Arrays.asList(1, 543, 5));
        hashTable.removeAll(listForRemoveAllCheck);

        System.out.println(hashTable);
        System.out.println("Size: " + hashTable.size());

        Object[] array1 = hashTable.toArray();

        System.out.println("В массив: " + Arrays.toString(array1));
        System.out.println("Array Size: " + array1.length);

        System.out.println("В массив вторым методом:");
        Integer[] array2 = new Integer[7];

        array2 = hashTable.toArray(array2);
        System.out.println("Size: " + array2.length);
        System.out.println("Итог: " + Arrays.toString(array2));

        System.out.println("Проход итератором:");
        for (Integer number : hashTable) {
            System.out.println(number);
        }
    }
}
