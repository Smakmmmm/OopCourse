package ru.makhmedov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private TreeNode<E> root;
    private int size;

    private final Comparator<? super E> comparator;

    public BinaryTree() {
        comparator = null;
    }

    @SuppressWarnings("unused")
    public BinaryTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @SafeVarargs
    public final void add(E... dataArray) {
        for (E d : dataArray) {
            add(d);
        }
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        Comparable<? super E> comparable = (Comparable<? super E>) data1;

        return comparable.compareTo(data2);
    }

    public void add(E data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<E> currentNode = root;
        TreeNode<E> addedNode = new TreeNode<>(data);

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

    public boolean contains(E data) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
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

    public boolean remove(E data) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> nodeToRemove = root;
        TreeNode<E> nodeToRemoveParent = null;

        while (true) {
            int comparisonResult = compare(data, nodeToRemove.getData());

            if (comparisonResult == 0) {
                break;
            }

            if (comparisonResult < 0) {
                if (nodeToRemove.getLeft() != null) {
                    nodeToRemoveParent = nodeToRemove;
                    nodeToRemove = nodeToRemove.getLeft();
                    continue;
                }

                return false;
            }

            if (nodeToRemove.getRight() != null) {
                nodeToRemoveParent = nodeToRemove;
                nodeToRemove = nodeToRemove.getRight();
                continue;
            }

            return false;
        }

        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
            if (nodeToRemoveParent == null) {
                root = null;
                size = 0;

                return true;
            }

            if (nodeToRemoveParent.getLeft() == nodeToRemove) {
                nodeToRemoveParent.setLeft(null);
                size--;

                return true;
            }

            nodeToRemoveParent.setRight(null);
            size--;

            return true;
        }

        if ((nodeToRemove.getLeft() == null && nodeToRemove.getRight() != null) ||
                (nodeToRemove.getLeft() != null && nodeToRemove.getRight() == null)) {
            TreeNode<E> nodeToRemoveChild = Objects.requireNonNullElse(nodeToRemove.getLeft(), nodeToRemove.getRight());

            if (nodeToRemoveParent == null) {
                root = nodeToRemoveChild;
                size--;

                return true;
            }

            if (nodeToRemoveParent.getLeft() == nodeToRemove) {
                nodeToRemoveParent.setLeft(nodeToRemoveChild);
                size--;

                return true;
            }

            nodeToRemoveParent.setRight(nodeToRemoveChild);
            size--;

            return true;
        }

        TreeNode<E> minLeftNode = nodeToRemove.getRight();
        TreeNode<E> minLeftParentNode = nodeToRemove;

        while (minLeftNode.getLeft() != null) {
            minLeftParentNode = minLeftNode;
            minLeftNode = minLeftNode.getLeft();
        }

        if (nodeToRemoveParent == null) {
            root = minLeftNode;
        } else if (nodeToRemoveParent.getLeft() == nodeToRemove) {
            nodeToRemoveParent.setLeft(minLeftNode);
        } else {
            nodeToRemoveParent.setRight(minLeftNode);
        }

        if (minLeftNode != nodeToRemove.getRight()) {
            minLeftParentNode.setLeft(minLeftNode.getRight());
            minLeftNode.setRight(nodeToRemove.getRight());
        }

        minLeftNode.setLeft(nodeToRemove.getLeft());

        size--;

        return true;
    }

    public void traverseInWidth(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.poll();
            action.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void traverseInDepth(Consumer<E> action) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> deque = new ArrayDeque<>();
        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode<E> currentNode = deque.removeFirst();
            action.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                deque.addFirst(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                deque.addFirst(currentNode.getLeft());
            }
        }
    }

    public void traverseInDepthRecursive(Consumer<E> action) {
        if (root == null) {
            return;
        }

        visit(root, action);
    }

    private void visit(TreeNode<E> node, Consumer<E> action) {
        action.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), action);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), action);
        }
    }
}
