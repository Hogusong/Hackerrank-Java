package com.young.Search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NodesSwap {
    public static void main(String[] args) {
        int[][] indexes = {{2, 3}, {4, -1}, {5, -1}, {6, -1}, {7, 8}, {-1, 9}, {-1, -1}, {10, 11}, {-1, -1}, {-1, -1}, {-1, -1}};
        int[] queries = {2, 2, 4};
        swapNodes(indexes, queries);

    }

    static class Node {
        int value;
        int depth;
        Node left;
        Node right;

        public Node(int value, int depth, Node left, Node right) {
            this.value = value;
            this.depth = depth;
            this.left = left;
            this.right = right;
        }
    }

    private static int[][] swapNodes(int[][] indexes, int[] queries) {
        int numOfNodes = indexes.length;
        int qLen = queries[0];
        int[][] result = new int[qLen][numOfNodes];

        Node root = new Node(1, 1, null, null);
        Node cur = root;

        Queue<Node> nodes = new LinkedList<>();
        nodes.offer(cur);

        int N = 0; // = numOfNodes;
        while (N < numOfNodes) {
            cur = nodes.poll();
            int leftData = indexes[N][0];
            int rightData = indexes[N][1];
            cur.left = (leftData == -1) ? null : new Node(leftData, cur.depth + 1, null, null);
            cur.right = (rightData == -1) ? null : new Node(rightData, cur.depth + 1, null, null);

            if (cur.left != null && cur.left.value != -1)
                nodes.offer(cur.left);
            if (cur.right != null && cur.right.value != -1)
                nodes.offer(cur.right);

            N++;
        }

        List<Integer> nodeArray = new LinkedList<>();
        printNodes(root, nodeArray);
        System.out.println(nodeArray);
        return result;
    }

    private static void printNodes(Node curr, List<Integer> arr) {
        if (curr == null) return;

        printNodes(curr.left, arr);
        arr.add(curr.value);
        printNodes(curr.right, arr);
    }
}
