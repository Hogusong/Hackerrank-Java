package com.young.Trees;

public class LowestCommonAncestor {
    public static void main(String[] args) {

    }

    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.
        int n1 = Math.min(v1, v2);
        int n2 = Math.max(v1, v2);
        if (root.data > n1 && root.data <= n2) return root;
        if (root.data < n1) return lca(root.right, n1, n2);
        if (root.data > n2) return lca(root.left, n1, n2);
        return root;
    }

    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
