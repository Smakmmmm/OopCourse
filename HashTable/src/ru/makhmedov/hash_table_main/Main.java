package ru.makhmedov.hash_table_main;

import ru.makhmedov.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>();
        hashTable.add("Первый");
        hashTable.add("Второй");
        hashTable.add("Третий");
        hashTable.add("Пятый");
        System.out.println(hashTable);

        HashTable<String> anotherHashTable = new HashTable<>(10);
        anotherHashTable.add("Первый");
        anotherHashTable.add("Второй");
        anotherHashTable.add("Третий");
        anotherHashTable.add("Пятый");
        System.out.println(anotherHashTable);

        System.out.println("Проверка RemoveAll:");
        List<String> list123 = new ArrayList<>(Arrays.asList("Первый", "ывффв", "Пятый"));
        hashTable.removeAll(list123);

        System.out.println(hashTable);
        System.out.println("Size: " + hashTable.size());

        Object[] tableInArray = hashTable.toArray();

        System.out.println("В массив: " + Arrays.toString(tableInArray));
        System.out.println("Array Size: " + tableInArray.length);

        System.out.println("В массив вторым методом:");
        String[] tableInArray2 = new String[7];

        tableInArray2 = hashTable.toArray(tableInArray2);
        System.out.println("Size: " + tableInArray2.length);
        System.out.println("Итог: " + Arrays.toString(tableInArray2));
    }
}
