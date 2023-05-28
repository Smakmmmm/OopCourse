package ru.makhmedov.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private void checkGraphSize(int[][] connectivityMatrix) {
        if (connectivityMatrix.length == 0) {
            throw new IllegalArgumentException("Граф не должен быть пустым.");
        }
    }

    public void traverseInWidth(int[][] connectivityMatrix, IntConsumer action) {
        checkGraphSize(connectivityMatrix);

        boolean[] visited = new boolean[connectivityMatrix.length];

        Queue<Integer> graphVertexQueue = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                graphVertexQueue.add(i);
            }

            while (!graphVertexQueue.isEmpty()) {
                int currentVertexIndex = graphVertexQueue.poll();

                if (visited[currentVertexIndex]) {
                    continue;
                }

                action.accept(currentVertexIndex);
                visited[currentVertexIndex] = true;

                for (int j = 0; j < connectivityMatrix.length; j++) {
                    if (!visited[j] && connectivityMatrix[currentVertexIndex][j] == 1) {
                        graphVertexQueue.add(j);
                    }
                }
            }
        }
    }

    public void traverseInDepth(int[][] connectivityMatrix, IntConsumer action) {
        checkGraphSize(connectivityMatrix);

        boolean[] visited = new boolean[connectivityMatrix.length];

        Deque<Integer> graphVertexDeque = new ArrayDeque<>();

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                graphVertexDeque.addFirst(i);
            }

            while (!graphVertexDeque.isEmpty()) {
                int currentVertexIndex = graphVertexDeque.removeFirst();

                if (visited[currentVertexIndex]) {
                    continue;
                }

                action.accept(currentVertexIndex);
                visited[currentVertexIndex] = true;

                for (int j = connectivityMatrix.length - 1; j >= 0; j--) {
                    if (connectivityMatrix[currentVertexIndex][j] == 1 && !visited[j]) {
                        graphVertexDeque.addFirst(j);
                    }
                }
            }
        }
    }

    public void traverseInDepthRecursive(int[][] connectivityMatrix, IntConsumer action) {
        checkGraphSize(connectivityMatrix);

        boolean[] visited = new boolean[connectivityMatrix.length];

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (!visited[i]) {
                visit(connectivityMatrix, i, visited, action);
            }
        }
    }

    private void visit(int[][] connectivityMatrix, int i, boolean[] visited, IntConsumer action) {
        action.accept(i);
        visited[i] = true;

        for (int j = 0; j < connectivityMatrix.length; j++) {
            if (connectivityMatrix[i][j] == 1 && !visited[j]) {
                visit(connectivityMatrix, j, visited, action);
            }
        }
    }
}
