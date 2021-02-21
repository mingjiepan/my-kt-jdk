package com.mjie.tree;

public class Node {
    private int value;
    private Node left;
    private Node right;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void sortPrint(Node node) {
        if (node == null) {
            return;
        }
        //node.value先序
        sortPrint(node.left);
        //node.value 中序
        sortPrint(node.right);
        //node.value 后序
    }
}
