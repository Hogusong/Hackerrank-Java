package com.young.Trees;

public class IsBinarySearchTree {

    public static void main(String[] args) {
    }

    static boolean checkBST(Node root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean checkBST(Node node, int minV, int maxV) {
        if (node == null) return true;
        if (minV > node.data || node.data > maxV) return false;

        return checkBST(node.left, minV, node.data-1) && checkBST(node.right, node.data+1, maxV);
    }


    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
