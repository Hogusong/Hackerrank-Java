package com.young.Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SwapNodes {

    public static void main(String[] args) {

    }

    static class Node {
        int index;
        int depth;
        Node left;
        Node right;

        public Node(int index, int depth, Node left, Node right) {
            this.depth = depth;
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }

    static void printInOrder(Node cur, List<Integer> result) {

        if (cur == null) return ;

        printInOrder(cur.left, result);
        result.add(cur.index);
        printInOrder(cur.right, result);
    }

    static void swapInOrder(Node cur, int depth, int k) {

        if (cur == null) return ;

        swapInOrder(cur.left, depth + 1, k);
        swapInOrder(cur.right, depth + 1, k);

        if (depth >=k && depth % k == 0 ) {
            Node tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
        }

    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int numOfNodes = indexes.length;
        int numOfQueries = queries.length;
        int[][] result = new int[numOfQueries][numOfNodes];

        Node root = new Node(1, 1, null, null);
        Node cur = root;

        Queue<Node> nodes = new LinkedList<Node>();
        nodes.offer(cur);

        int N = 0; // = numOfNodes;
        while (N < numOfNodes) {
            cur = nodes.poll();
            int leftData = indexes[N][0];
            int rightData = indexes[N][1];
            cur.left = (leftData==-1)? null: new Node(leftData, cur.depth+1, null, null);
            cur.right = (rightData==-1)? null: new Node(rightData, cur.depth+1, null, null);

            if (cur.left != null && cur.left.index != -1)
                nodes.offer(cur.left);
            if (cur.right != null && cur.right.index != -1)
                nodes.offer(cur.right);

            N++;
        }

        //TODO: till here we have formed the tree, [not checked yet]
        for (int i = 0; i < numOfQueries; i++) {
            swapInOrder(root, 1, queries[i]);
            List<Integer> res = new ArrayList();
            printInOrder(root, res);
            result[i] = res.stream().mapToInt(r->r).toArray();
        }

        return result;
    }
}
