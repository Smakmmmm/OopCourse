package ru.makhmedov.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] connectivityMatrix;

    public Graph(int[][] connectivityMatrix) {
        this.connectivityMatrix = connectivityMatrix;
    }

    public Graph() {
        connectivityMatrix = null;
    }

    public void traverseInWidth(IntConsumer intConsumer) {
        if (connectivityMatrix == null || connectivityMatrix.length == 0) {
            System.out.println("Граф пуст.");
            return;
        }

        boolean[] visited = new boolean[connectivityMatrix.length];

        Queue<Integer> graphVerticesIndicesQueue = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                graphVerticesIndicesQueue.add(i);
            } else {
                continue;
            }

            while (!graphVerticesIndicesQueue.isEmpty()) {
                int currentVertexIndex = graphVerticesIndicesQueue.poll();

                if (visited[currentVertexIndex]) {
                    continue;
                }

                intConsumer.accept(currentVertexIndex);
                visited[currentVertexIndex] = true;

                for (int j = 0; j < connectivityMatrix.length; j++) {
                    if (!visited[j] && connectivityMatrix[currentVertexIndex][j] == 1) {
                        graphVerticesIndicesQueue.add(j);
                    }
                }
            }
        }
    }

    public void traverseInDepth(IntConsumer intConsumer) {
        if (connectivityMatrix == null || connectivityMatrix.length == 0) {
            System.out.println("Граф пуст.");
            return;
        }

        boolean[] visited = new boolean[connectivityMatrix.length];

        Deque<Integer> graphVerticesIndicesDeque = new ArrayDeque<>();

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                graphVerticesIndicesDeque.addFirst(i);
            } else {
                continue;
            }

            while (!graphVerticesIndicesDeque.isEmpty()) {
                int currentVertexIndex = graphVerticesIndicesDeque.removeFirst();

                if (visited[currentVertexIndex]) {
                    continue;
                }

                intConsumer.accept(currentVertexIndex);
                visited[currentVertexIndex] = true;

                for (int j = connectivityMatrix.length - 1; j >= 0; j--) {
                    if (connectivityMatrix[currentVertexIndex][j] == 1 && !visited[j]) {
                        graphVerticesIndicesDeque.addFirst(j);
                    }
                }
            }
        }
    }

    public void traverseInDepthRecursive(IntConsumer intConsumer) {
        if (connectivityMatrix == null || connectivityMatrix.length == 0) {
            System.out.println("Граф пуст.");
            return;
        }

        boolean[] visited = new boolean[connectivityMatrix.length];

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (!visited[i]) {
                visit(i, visited, intConsumer);
            }
        }
    }

    private void visit(int vertexIndex, boolean[] visited, IntConsumer intConsumer) {
        if (connectivityMatrix == null || connectivityMatrix.length == 0) {
            System.out.println("Граф пуст.");
            return;
        }

        intConsumer.accept(vertexIndex);
        visited[vertexIndex] = true;

        for (int i = 0; i < connectivityMatrix.length; i++) {
            if (connectivityMatrix[vertexIndex][i] == 1 && !visited[i]) {
                visit(i, visited, intConsumer);
            }
        }
    }
}
