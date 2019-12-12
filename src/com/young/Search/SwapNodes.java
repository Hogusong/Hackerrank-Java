package com.young.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SwapNodes {

    public static void main(String[] args) {

    }

    static class Node {
        int value;
        int depth;
        Node right;
        Node left;

        Node(int value, int depth, Node right, Node left) {
            this.value = value;
            this.depth = depth;
            this.right = right;
            this.left = left;
        }
    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int nOfNodes = indexes.length;
        int nOfQueries = queries.length;
        int[][] result = new int[nOfQueries][nOfNodes];

        Queue<Node> nodes = new LinkedList<>();
        Node root = new Node(1, 1, null, null);
        nodes.add(root);
        int n = 0;
        while (n < nOfNodes) {
            Node curr = nodes.poll();
            int lData = indexes[n][0];
            int rData = indexes[n][1];
            curr.left = (lData == -1) ? null : new Node(lData, curr.depth+1, null, null);
            curr.right = (rData == -1) ? null : new Node(rData, curr.depth+1, null, null);
            if (curr.left != null) nodes.add(curr.left);
            if (curr.right != null) nodes.add(curr.right);
            n++;
        }
        for (int i = 0; i < nOfQueries; i++) {
            swapNodesRec(root, 1, queries[i]);
            List<Integer> res = new ArrayList();
            printInOrder(root, res);
            int[] ans = new int[res.size()];
            for (int j = 0; j < res.size(); j++) ans[j] = res.get(j);
            result[i] = ans;
        }
        return result;
    }

    static void swapNodesRec(Node curr, int depth, int k) {
        if (curr == null) return;

        if (depth >= k && depth % k == 0) {
            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
        }

        swapNodesRec(curr.left, depth+1, k);
        swapNodesRec(curr.right, depth+1, k);
    }

    static void printInOrder(Node curr, List<Integer> result) {
        if (curr == null) return;

        printInOrder(curr.left, result);
        result.add(curr.value);
        printInOrder(curr.right, result);
    }
}
