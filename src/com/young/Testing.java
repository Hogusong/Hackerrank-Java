package com.young;

import java.util.*;

public class Testing {

    public static void main(String[] args) {
        String[] cities = {"Boston", "Providence", "New York", "Chicago", "Seattle", "Denver", "Phoenix", "Los Angeles"};
        DiGraph graph = new DiGraph();
        buildNodes(graph, cities);
        buildEdges(graph);

        findingPath(graph, "Boston", "Phoenix", "DFS");
        findingPath(graph, "Boston", "Seattle", "DFS");
        findingPath(graph, "Boston", "Phoenix", "BFS");
        findingPath(graph, "Boston", "Seattle", "BFS");
    }

    private static void findingPath(DiGraph graph, String start, String end, String type) {
        Node from = graph.findNode(start);
        Node to = graph.findNode(end);
        if (from == null || to == null) {
            System.out.println(start + " or " + end + " is not on map.");
            return;
        }

        List<Node> result = type == "BFS" ? findBFS(graph, from, to) :
                findDFS(graph, from, to, new LinkedList<>(), new LinkedList<>());

        if (result.size() > 0) {
            String memo = "\n" + type + ": The shortest path of " + start + " -> " + end + " is \n     ";
            printPath(result, memo);
        } else {
            System.out.println("\n" + type + ": There is no path for " + start + " -> " + end + ".\n");
        }
    }

    // Depth First Search
    private static List<Node> findDFS(DiGraph graph, Node from, Node to, List<Node> path, List<Node> shortPass) {
        path.add(from);
        if (from.equals(to)) return path;
        if (shortPass.size() > 0 && path.size() >= shortPass.size()) return null;

        List<Node> destinations = graph.getDestination(from);
        for (Node node : destinations) {
            if (path.contains(node)) System.out.println("You've visited " + node.name + " already.");
            else {
                List<Node> newPath = findDFS(graph, node, to, new LinkedList<>(path), new LinkedList<>(shortPass));
                if (newPath != null) shortPass = newPath;
            }
        }

        return shortPass;
    }

    // Breadth First Search
    private static List<Node> findBFS(DiGraph graph, Node from, Node to) {
        Queue<List<Node>> Q = new LinkedList<>();
        Map<Node, List<Node>> nodesMap = new HashMap<>();
        List<Node> temp = new LinkedList<>();
        temp.add(from);
        Q.add(temp);
        while (Q.size() > 0) {
            temp = Q.poll();
            List<Node> destination = graph.getDestination(temp.get(temp.size()-1));
            for (Node node : destination) {
                if (temp.contains(node)) System.out.println("You've visited " + node.name + " already.");
                else {
                    List<Node> newPath = new LinkedList<>(temp);
                    newPath.add(node);
                    if (node.equals(to)) return newPath;
                    Q.add(newPath);
                }
            }
        }

        return new LinkedList<>();
    }

    private static void buildNodes(DiGraph graph, String[] cities) {
        for (int i = 0; i < cities.length; i++) {
            graph.addNode(new Node(cities[i]));
        }
    }

    private static void buildEdges(DiGraph graph) {
        graph.addEdge("Boston", "New York");
        graph.addEdge("Boston", "Providence");
        graph.addEdge("Providence", "Boston");
        graph.addEdge("Providence", "New York");
        graph.addEdge("New York", "Chicago");
        graph.addEdge("Chicago", "Denver");
        graph.addEdge("Chicago", "Phoenix");
        graph.addEdge("Denver", "Phoenix");
        graph.addEdge("Denver", "New York");
        graph.addEdge("Los Angeles", "Boston");
    }

    private static void printPath(List<Node> path, String prefix) {
        System.out.print(prefix);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).name);
            if (i < path.size() - 1) System.out.print( " -> ");
        }
        System.out.println("\n");
    }

    static class Node {
        String name;

        Node(String name) {
            this.name = name;
        }
    }

    static class Edge {
        Node from;
        Node to;

        Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }

        boolean equalTo(Edge edge) {
            return this.from.equals(edge.from) && this.to.equals(edge.to);
        }
    }

    static class DiGraph {
        List<Edge> edges;
        Map<Node, List<Node>> nodes;

        DiGraph() {
            this.edges = new LinkedList<>();
            this.nodes = new HashMap<>();
        }

        void addNode(Node node) {
            if (!this.nodes.containsKey(node)) this.nodes.put(node, new LinkedList<>());
            else System.out.println(node.name + " is added already.");
        }

        void addEdge(String start, String end) {
            Node from = this.findNode(start);
            Node to = this.findNode(end);
            if (from == null || to == null) {
                System.out.println(start + " or " + end + " is not on the map.");
            } else {
                if (this.isNewEdge(from, to)) {
                    this.edges.add(new Edge(from, to));
                    this.nodes.get(from).add(to);
                } else System.out.println(start + " -> " + end + " is not a new EDGE.");
            }
        }

        boolean isNewEdge(Node from, Node to) {
            Edge newEdge = new Edge(from, to);
            for (Edge edge : this.edges) if (edge.equalTo(newEdge)) return false;
            return true;
        }

        Node findNode(String name) {
            for (Node node : this.nodes.keySet()) {
                if (node.name == name) return node;
            }
            return null;
        }

        List<Node> getDestination(Node node) {
            return new LinkedList<>(this.nodes.get(node));
        }
    }

    static class Graph extends DiGraph {
        @Override
        void addEdge(String start, String end) {
            super.addEdge(start, end);
            super.addEdge(end, start);
        }
    }
}

