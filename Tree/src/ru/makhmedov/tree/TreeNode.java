package ru.makhmedov.tree;

import java.util.ArrayList;

class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "" + data;
    }

    public ArrayList<TreeNode<T>> getChildren() {
        ArrayList<TreeNode<T>> children = new ArrayList<>();

        if (left != null) {
            children.add(left);
        }

        if (right != null) {
            children.add(right);
        }

        return children;
    }
}
