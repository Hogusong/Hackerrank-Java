package com.young.Graphs;

import java.util.*;

public class FindNearestClone {

    public static void main(String[] args) {
        int[] from = {1,1,4}, to = {2,3,2};
        long[] ids = {1,2,3,4};
        System.out.println(findShortest(4, from, to, ids, 2));
    }

    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        int minPath = Integer.MAX_VALUE;
        Map<Integer, List<Node>> edges = new HashMap<>();
        for (int i = 0; i < graphFrom.length; i++) {
            int key = graphFrom[i];
            long color = ids[key-1];
            int toKey = graphTo[i];
            long toColor = ids[toKey-1];

            if (!edges.containsKey(key)) edges.put(key, new LinkedList<>());
            if (isNewDestination(edges.get(key), toKey)) edges.get(key).add(new Node(toKey, toColor));

            if (!edges.containsKey(toKey)) edges.put(toKey, new LinkedList<>());
            if (isNewDestination(edges.get(toKey), key)) edges.get(toKey).add(new Node(key, color));
        }

        boolean foundPath = false;
        for (Integer k : edges.keySet()) {
            if (ids[k-1] == val) {
                int count = findBFS(edges, k, val);
                if (count > 0) {
                    minPath = Math.min(minPath, count);
                    foundPath = true;
                }
            }
        }

        return foundPath ? minPath : -1;
    }

    static int findBFS(Map<Integer, List<Node>> edges, int from, long target) {
        Queue<List<Node>> Q = new LinkedList<>();
        List<Node> temp = new LinkedList<>();
        temp.add(new Node(from, target));
        Q.add(temp);
        while (Q.size() > 0) {
            temp = Q.poll();
            List<Node> newPath = new LinkedList<>(temp);
            Node last = temp.get(temp.size()-1);
            for (Node node : edges.get(last.value)) {
                if (!isNewInPath(newPath, node)) continue;
                if (node.color == target) return newPath.size();
                newPath.add(node);
                Q.add(newPath);
            }
        }
        return 0;
    }

    static boolean isNewInPath(List<Node> path, Node node) {
        for (Node p : path) {
            if (p.value == node.value) return false;
        }
        return true;
    }

    static boolean isNewDestination(List<Node> nodes, int val) {
        for (Node node : nodes) {
            if (node.value == val) return false;
        }
        return true;
    }

    static class Node {
        int value;
        long color;
        Node(int v, long c) {
            this.value = v;
            this.color = c;
        }
    }
}

/*
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        Graph graph = new Graph();
        for (int i = 0; i < graphFrom.length; i++) {
            int start = graphFrom[i];
            int end = graphTo[i];
            graph.addNode(start, ids[start-1]);
            graph.addNode(end, ids[end-1]);
            graph.addEdge(start, end);
        }
        // solve here
        return findPath(graph, val);
    }

    static int findPath(Graph graph, int val) {
        int minPath = Integer.MAX_VALUE;
        for (Node node : graph.edges.keySet()) {
            if (node.color == val) {
                minPath = Math.min(minPath, findBFS(graph, node, val));
            }
        }
        return minPath;
    }

    static int findBFS(Graph graph, Node from, int val) {
        Queue<List<Node>> Q = new LinkedList<>();
        List<Node> temp = new LinkedList<>();
        temp.add(from);
        Q.add(temp);
        while (Q.size() > 0) {
            temp = Q.poll();
            Node last = temp.get(temp.size()-1);
            for (Node node : graph.getDestination(last)) {
                if (temp.contains(node)) continue;
                if (node.color == val) return temp.size();
                List<Node> newPath = new LinkedList<>(temp);
                newPath.add(node);
                Q.add(newPath);
            }
        }
        return -1;
    }

    static class Node {
        int value;
        long color;
        Node(int value, long color) {
            this.value = value;
            this.color = color;
        }
    }

    static class DiGraph {
        Map<Node, List<Node>> edges;
        DiGraph() {
            this.edges = new HashMap<>();
        }
        void addNode(int value, long color) {
            if (this.findNode(value) == null) {
                Node node = new Node(value, color);
                this.edges.put(node, new LinkedList<>());
            }
        }
        Node findNode(int value) {
            for (Node node : this.edges.keySet()) {
                if (node.value == value) return node;
            }
            return null;
        }
        void addEdge(int start, int end) {
            Node from = this.findNode(start);
            Node to = this.findNode(end);
            if (from == null || to == null) return;
            if (!this.isNewEdge(from, to)) return;
            this.edges.get(from).add(to);
        }
        boolean isNewEdge(Node from, Node to) {
            return !(this.edges.containsKey(from) && this.edges.get(from).contains(to));
        }

        List<Node> getDestination(Node node) {
            return this.edges.get(node);
        }
    }

    static class Graph extends DiGraph {
        void addEdge(int start, int end) {
            super.addEdge(start, end);
            super.addEdge(end, start);
        }
    }
*/