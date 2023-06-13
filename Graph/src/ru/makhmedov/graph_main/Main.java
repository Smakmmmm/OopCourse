package ru.makhmedov.graph_main;

import ru.makhmedov.graph.Graph;

import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        int[][] connectivityMatrix = {
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0}
        };

        Graph graph = new Graph(connectivityMatrix);

        StringBuilder stringBuilder = new StringBuilder();
        IntConsumer consumer = node -> stringBuilder.append(node).append(", ");

        stringBuilder.append('[');
        graph.traverseInWidth(consumer);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в ширину:");
        System.out.println(stringBuilder);

        stringBuilder.delete(1, stringBuilder.length());
        graph.traverseInDepth(consumer);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в глубину:");
        System.out.println(stringBuilder);

        stringBuilder.delete(1, stringBuilder.length());
        graph.traverseInDepthRecursive(consumer);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в глубину рекурсивно:");
        System.out.println(stringBuilder);

        Graph graph1 = new Graph(new int[0][0]);
        System.out.println("Проверка случая, когда граф пуст:");
        graph1.traverseInDepthRecursive(consumer);
    }
}
