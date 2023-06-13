package ru.makhmedov.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] connectivityMatrix;

    public Graph(int[][] connectivityMatrix) {
        final int expectedColumnsCount = connectivityMatrix.length;

        for (int[] matrix : connectivityMatrix) {
            if (matrix.length != expectedColumnsCount) {
                throw new IllegalArgumentException("Матрица связности должна быть квадратной!");
            }
        }

        this.connectivityMatrix = connectivityMatrix;
    }

    public void traverseInWidth(IntConsumer consumer) {
        if (connectivityMatrix.length == 0) {
            return;
        }

        boolean[] visited = new boolean[connectivityMatrix.length];

        Queue<Integer> graphVerticesIndicesQueue = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            graphVerticesIndicesQueue.add(i);

            while (!graphVerticesIndicesQueue.isEmpty()) {
                int currentVertexIndex = graphVerticesIndicesQueue.poll();

                if (visited[currentVertexIndex]) {
                    continue;
                }

                consumer.accept(currentVertexIndex);
                visited[currentVertexIndex] = true;

                for (int j = 0; j < connectivityMatrix.length; j++) {
                    if (!visited[j] && connectivityMatrix[currentVertexIndex][j] == 1) {
                        graphVerticesIndicesQueue.add(j);
                    }
                }
            }
        }
    }

    public void traverseInDepth(IntConsumer consumer) {
        if (connectivityMatrix.length == 0) {
            return;
        }

        boolean[] visited = new boolean[connectivityMatrix.length];

        Deque<Integer> graphVerticesIndicesDeque = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            graphVerticesIndicesDeque.addFirst(i);

            while (!graphVerticesIndicesDeque.isEmpty()) {
                int currentVertexIndex = graphVerticesIndicesDeque.removeFirst();

                if (visited[currentVertexIndex]) {
                    continue;
                }

                consumer.accept(currentVertexIndex);
                visited[currentVertexIndex] = true;

                for (int j = connectivityMatrix.length - 1; j >= 0; j--) {
                    if (connectivityMatrix[currentVertexIndex][j] == 1 && !visited[j]) {
                        graphVerticesIndicesDeque.addFirst(j);
                    }
                }
            }
        }
    }

    public void traverseInDepthRecursive(IntConsumer consumer) {
        if (connectivityMatrix.length == 0) {
            return;
        }

        boolean[] visited = new boolean[connectivityMatrix.length];

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (!visited[i]) {
                visit(i, visited, consumer);
            }
        }
    }

    private void visit(int vertexIndex, boolean[] visited, IntConsumer consumer) {
        consumer.accept(vertexIndex);
        visited[vertexIndex] = true;

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (connectivityMatrix[vertexIndex][i] == 1 && !visited[i]) {
                visit(i, visited, consumer);
            }
        }
    }
}
