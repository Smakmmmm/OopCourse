package ru.makhmedov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private TreeNode<T> root;
    private int size;

    private final Comparator<? super T> comparator;

    public BinaryTree() {
        comparator = null;
    }

    @SuppressWarnings("unused")
    public BinaryTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @SafeVarargs
    public final void add(T... data) {
        for (T d : data) {
            add(d);
        }
    }

    private int compare(T data, T currentNodeData) {
        if (data == null && currentNodeData == null) {
            return 0;
        }

        if (data == null) {
            return -1;
        }

        if (currentNodeData == null) {
            return 1;
        }

        if (comparator != null) {
            return comparator.compare(data, currentNodeData);
        }

        //noinspection unchecked
        Comparable<? super T> comparable = (Comparable<? super T>) data;

        return comparable.compareTo(currentNodeData);
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> addedNode = new TreeNode<>(data);

        while (true) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                }

                currentNode.setLeft(addedNode);
                size++;

                return;
            }

            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                continue;
            }

            size++;
            currentNode.setRight(addedNode);

            return;
        }
    }

    public boolean contains(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            if (compare(currentNode.getData(), data) == 0) {
                return true;
            }

            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                }

                return false;
            }

            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                continue;
            }

            return false;
        }
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean remove(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> nodeToRemove = root;
        TreeNode<T> parentNode = null;

        while (compare(nodeToRemove.getData(), data) != 0) {
            if (compare(data, nodeToRemove.getData()) < 0) {
                if (nodeToRemove.getLeft() != null) {
                    parentNode = nodeToRemove;
                    nodeToRemove = nodeToRemove.getLeft();
                    continue;
                }

                return false;
            }

            if (nodeToRemove.getRight() != null) {
                parentNode = nodeToRemove;
                nodeToRemove = nodeToRemove.getRight();
                continue;
            }

            return false;
        }

        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
            if (parentNode == null) {
                root = null;
                size = 0;

                return true;
            }

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
                (nodeToRemove.getLeft() != null && nodeToRemove.getRight() == null)) {
            TreeNode<T> nodeToRemoveChild = Objects.requireNonNullElse(nodeToRemove.getLeft(), nodeToRemove.getRight());

            if (parentNode == null) {
                root = nodeToRemoveChild;
                size--;

                return true;
            }

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

        if (parentNode == null) {
            root = minLeftNode;
        } else if (parentNode.getLeft() == nodeToRemove) {
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

    public void traverseInWidth(Consumer<T> action) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            TreeNode<T> currentNode = nodeQueue.poll();
            action.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                nodeQueue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                nodeQueue.add(currentNode.getRight());
            }
        }
    }

    public void traverseInDeep(Consumer<T> action) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> nodeDeque = new ArrayDeque<>();
        nodeDeque.addFirst(root);

        while (!nodeDeque.isEmpty()) {
            TreeNode<T> currentNode = nodeDeque.removeFirst();
            action.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                nodeDeque.addFirst(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                nodeDeque.addFirst(currentNode.getLeft());
            }
        }
    }

    public void traverseInDeepRecursive(Consumer<T> action) {
        if (root == null) {
            return;
        }

        visit(root, action);
    }

    private void visit(TreeNode<T> node, Consumer<T> action) {
        action.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), action);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), action);
        }
    }
}
