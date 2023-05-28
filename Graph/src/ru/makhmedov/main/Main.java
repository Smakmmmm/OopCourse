package ru.makhmedov.main;

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

        Graph graph = new Graph();

        StringBuilder stringBuilder = new StringBuilder();
        IntConsumer intConsumer = node -> stringBuilder.append(node).append(", ");

        stringBuilder.append('[');
        graph.traverseInWidth(connectivityMatrix, intConsumer);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в ширину:");
        System.out.println(stringBuilder);

        stringBuilder.delete(1, stringBuilder.length());
        graph.traverseInDepth(connectivityMatrix, intConsumer);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в глубину:");
        System.out.println(stringBuilder);

        stringBuilder.delete(1, stringBuilder.length());
        graph.traverseInDepthRecursive(connectivityMatrix, intConsumer);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в глубину рекурсивно:");
        System.out.println(stringBuilder);
    }
}
