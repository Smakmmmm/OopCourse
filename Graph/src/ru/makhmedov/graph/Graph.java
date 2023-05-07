package ru.makhmedov.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Graph {
    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}
        };

        StringBuilder stringBuilder = new StringBuilder();
        Consumer<Integer> consumer = node -> stringBuilder.append(node).append(", ");

        stringBuilder.append('[');
        traverseInWidth(consumer, graph);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в ширину:");
        System.out.println(stringBuilder);

        stringBuilder.delete(1, stringBuilder.length());
        traverseInDeep(consumer, graph);
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(']');
        System.out.println("Обход графа в глубину:");
        System.out.println(stringBuilder);
    }

    public static void traverseInWidth(Consumer<Integer> action, int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> graphNodeQueue = new LinkedList<>();
        graphNodeQueue.add(0);

        while (!graphNodeQueue.isEmpty()) {
            int currentNode = graphNodeQueue.poll();
            action.accept(currentNode);
            visited[currentNode] = true;

            for (int i = 0; i < graph.length; i++) {
                if (visited[i]) {
                    continue;
                }

                if (graph[currentNode][i] == 1 && !graphNodeQueue.contains(i)) {
                    graphNodeQueue.add(i);
                }
            }

            if (graphNodeQueue.isEmpty()) {
                for (int i = 0; i < visited.length; i++) {
                    if (!visited[i]) {
                        graphNodeQueue.add(i);
                        break;
                    }
                }
            }
        }
    }

    public static void traverseInDeep(Consumer<Integer> action, int[][] graph) {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> graphNodeDeque = new ArrayDeque<>();
        graphNodeDeque.addFirst(0);

        while (!graphNodeDeque.isEmpty()) {
            int currentNode = graphNodeDeque.removeFirst();
            action.accept(currentNode);
            visited[currentNode] = true;

            for (int i = 0; i < graph.length; i++) {
                if (visited[i]) {
                    continue;
                }

                if (graph[currentNode][i] == 1 && !graphNodeDeque.contains(i)) {
                    graphNodeDeque.addFirst(i);
                }
            }

            if (graphNodeDeque.isEmpty()) {
                for (int i = 0; i < visited.length; i++) {
                    if (!visited[i]) {
                        graphNodeDeque.add(i);
                        break;
                    }
                }
            }
        }
    }
}
