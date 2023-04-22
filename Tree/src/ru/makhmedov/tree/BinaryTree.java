package ru.makhmedov.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public BinaryTree() {
        root = new TreeNode<>();
    }

    @SafeVarargs
    public final void add(T... data) {
        for (T d : data) {
            add(d);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean add(T data) {
        if (data == null) {
            return false;
        }

        TreeNode<T> currentNode = root;

        if (currentNode.getData() == null) {
            currentNode.setData(data);
            size++;

            return true;
        }

        TreeNode<T> addedNode = new TreeNode<>();
        addedNode.setData(data);

        while (true) {
            if (data.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                }

                currentNode.setLeft(addedNode);
                size++;

                return true;
            }

            if (currentNode.getRight() == null) {
                size++;
                currentNode.setRight(addedNode);

                return true;
            }

            currentNode = currentNode.getRight();
        }
    }

    public TreeNode<T> find(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Значение не может быть \"null\".");
        }

        if (size == 0) {
            return null;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            if (currentNode.getData().equals(data)) {
                return currentNode;
            }

            if (data.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                }

                return null;
            }

            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                continue;
            }

            return null;
        }
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Значение не может быть \"null\".");
        }

        if (size == 0) {
            return false;
        }

        TreeNode<T> nodeToRemove = root;
        TreeNode<T> parentNode = nodeToRemove;
        boolean contain = false;

        while (true) {
            if (nodeToRemove.getData().equals(data)) {
                contain = true;
                break;
            }

            if (data.compareTo(nodeToRemove.getData()) < 0) {
                if (nodeToRemove.getLeft() != null) {
                    parentNode = nodeToRemove;
                    nodeToRemove = nodeToRemove.getLeft();
                    continue;
                }

                break;
            }

            if (nodeToRemove.getRight() != null) {
                parentNode = nodeToRemove;
                nodeToRemove = nodeToRemove.getRight();
                continue;
            }

            break;
        }

        if (!contain) {
            return false;
        }

        if (nodeToRemove == root && nodeToRemove.getRight() == null && nodeToRemove.getLeft() == null) {
            root = null;
            size = 0;

            return true;
        }

        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
            if (parentNode.getLeft() == nodeToRemove) {
                parentNode.setLeft(null);
                size--;

                return true;
            }

            parentNode.setRight(null);
            size--;

            return true;
        }

        if ((nodeToRemove.getLeft() == null && nodeToRemove.getRight() != null) ||
                nodeToRemove.getLeft() != null && nodeToRemove.getRight() == null) {
            TreeNode<T> nodeToRemoveChild = Objects.requireNonNullElse(nodeToRemove.getLeft(), nodeToRemove.getRight());

            if (parentNode.getLeft() == nodeToRemove) {
                parentNode.setLeft(nodeToRemoveChild);
                size--;

                return true;
            }

            parentNode.setRight(nodeToRemoveChild);
            size--;

            return true;
        }

        TreeNode<T> minLeftNode = nodeToRemove.getRight();
        TreeNode<T> minLeftParentNode = nodeToRemove;

        while (minLeftNode.getLeft() != null) {
            minLeftParentNode = minLeftNode;
            minLeftNode = minLeftNode.getLeft();
        }

        if (parentNode.getLeft() == nodeToRemove) {
            parentNode.setLeft(minLeftNode);
        } else {
            parentNode.setRight(minLeftNode);
        }

        if (minLeftNode != nodeToRemove.getRight()) {
            minLeftParentNode.setLeft(minLeftNode.getRight());
            minLeftNode.setRight(nodeToRemove.getRight());
        }

        minLeftNode.setLeft(nodeToRemove.getLeft());

        size--;

        return true;
    }

    public void breadthTraversal() {
        Queue<TreeNode<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        TreeNode<T> currentNode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        while (!nodeQueue.isEmpty()) {
            currentNode = nodeQueue.poll();
            stringBuilder.append(currentNode).append(", ");

            if (currentNode.getLeft() != null) {
                nodeQueue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                nodeQueue.add(currentNode.getRight());
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        System.out.println(stringBuilder.append(']'));
    }

    public void depthTraversal() {
        Stack<TreeNode<T>> nodeStack = new Stack<>();
        nodeStack.push(root);

        TreeNode<T> currentNode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        while (!nodeStack.empty()) {
            currentNode = nodeStack.pop();
            stringBuilder.append(currentNode).append(", ");

            if (currentNode.getRight() != null) {
                nodeStack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                nodeStack.push(currentNode.getLeft());
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        System.out.println(stringBuilder.append(']'));
    }

    public void depthTraversalRecursion() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        visit(root, stringBuilder);

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        System.out.println(stringBuilder.append(']'));
    }

    private void visit(TreeNode<T> node, StringBuilder stringBuilder) {
        stringBuilder.append(node).append(", ");

        for (TreeNode<T> child : node.getChildren()) {
            visit(child, stringBuilder);
        }
    }
}
