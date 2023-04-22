package ru.makhmedov.tree_main;

import ru.makhmedov.tree.BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(8, 3, 10, 1, 6, 14, 4, 7, 13);

        System.out.println("Обход в глубину без рекурсии:");
        binaryTree.depthTraversal();

        System.out.println("Обход в глубину через рекурсию:");
        binaryTree.depthTraversalRecursion();
        System.out.println();

        System.out.println("Обход в ширину:");
        binaryTree.breadthTraversal();
        System.out.println("Размер дерева: " + binaryTree.getSize());

        binaryTree.remove(10);
        System.out.println("Обход в ширину после удаления 10:");
        binaryTree.breadthTraversal();
        System.out.println("Размер дерева после удаления элемента: " + binaryTree.getSize());

        System.out.println("Поиск числа 101:" + binaryTree.find(101));
        System.out.println("Поиск числа 7: " + binaryTree.find(7));
    }
}
