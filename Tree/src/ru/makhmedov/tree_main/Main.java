package ru.makhmedov.tree_main;

import ru.makhmedov.tree.BinaryTree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(8, 3, 10, 1, 6, 14, 4, 7, 13);

        StringBuilder stringBuilder = new StringBuilder();
        Consumer<Integer> consumer = data -> stringBuilder.append(data).append(", ");

        System.out.println("Обход в глубину без рекурсии:");
        stringBuilder.append('[');
        binaryTree.traverseInDepth(consumer);

        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');
        System.out.println(stringBuilder);
        stringBuilder.delete(1, stringBuilder.length());


        System.out.println("Обход в глубину через рекурсию:");
        binaryTree.traverseInDepthRecursive(consumer);

        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');
        System.out.println(stringBuilder);
        stringBuilder.delete(1, stringBuilder.length());

        System.out.println("Обход в ширину:");
        binaryTree.traverseInWidth(consumer);

        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');
        System.out.println(stringBuilder);
        stringBuilder.delete(1, stringBuilder.length());

        System.out.println("Размер дерева: " + binaryTree.getSize());

        System.out.println("Удаление 8: " + binaryTree.remove(8));

        System.out.println("Обход в ширину после удаления 8:");
        binaryTree.traverseInWidth(consumer);

        if (stringBuilder.length() > 1) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');
        System.out.println(stringBuilder);
        stringBuilder.delete(1, stringBuilder.length());

        System.out.println("Размер дерева после удаления элемента: " + binaryTree.getSize());

        System.out.println("Поиск числа 101: " + binaryTree.contains(101));
        System.out.println("Поиск числа 7: " + binaryTree.contains(7));
    }
}
