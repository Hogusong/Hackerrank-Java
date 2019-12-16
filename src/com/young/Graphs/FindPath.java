package com.young.Graphs;

import java.util.*;

public class FindPath {


    public static void main(String[] args) {
        String[] cities = {"Boston", "Providence", "New York", "Chicago", "Denver", "Phoenix", "Los Angeles"};
        DiGraph graph = new DiGraph();
        buildNodes(graph, cities);
        buildEdges(graph);

        findingPath(graph, "Boston", "Phoenix", "DFS");
        findingPath(graph, "Boston", "Phoenix", "BFS");
    }

    private static void findingPath(DiGraph graph, String start, String end, String type) {
        Node from = graph.findNode(start);
        Node to = graph.findNode(end);
        if (from == null || to == null) {
            System.out.println(start + " or " + end + " is not on the map.");
            return;
        }

        List<Node> result = (type == "BFS") ? findBFS(graph, from, to) :
            findDFS(graph, from, to, new LinkedList<Node>(), new LinkedList<Node>());
        if (result.size() > 0) {
            printPath(result, "\n" + type + ": The shortest path of " + from.name + " -> " + to.name + " is\n     ");
        } else System.out.println("\nThere is no path of " + from.name + " -> " + to.name + ".");
    }

    private static List<Node> findDFS (DiGraph graph, Node from, Node to, List<Node> path, List<Node> shortest) {
        path.add(from);
        if (from.equalTo(to)) return path;

        if (shortest.size() > 0 && path.size() >= shortest.size()) return null;

        printPath(path, "Current Path is ");

        for (Node node : graph.getDestinations(from)) {
            if (path.contains(node)) {
                System.out.println("Already visited " + node.name + ".");
                continue;
            }
            List<Node> newPath = findDFS(graph, node, to, new LinkedList<Node>(path), new LinkedList<Node>(shortest));
            if (newPath != null) shortest = newPath;
        }

        return shortest;
    }

    private static List<Node> findBFS (DiGraph graph, Node from, Node to) {
        Queue<List<Node>> Q = new LinkedList<>();
        List<Node> temp = new LinkedList<>();
        temp.add(from);
        Q.add(temp);
        while (Q.size() > 0) {
            temp = Q.poll();
            List<Node> dest = graph.getDestinations(temp.get(temp.size()-1));
            List<Node> nodes;
            for (int i = 0; i < dest.size(); i++) {
                nodes = new LinkedList<>(temp);
                if (!nodes.contains(dest.get(i))) {
                    nodes.add(dest.get(i));
                    if (to.equalTo(dest.get(i))) return nodes;
                    Q.add(nodes);
                }
            }
        }
        return null;
    }

    private static void printPath(List<Node> path, String prefix) {
        System.out.print(prefix);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).name);
            if (i < path.size()-1) System.out.print(" -> ");
        }
        System.out.println();;
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


    static class Node {
        String name;

        Node(String name) {
            this.name = name;
        }

        boolean equalTo(Node n) {
            return n.name == this.name;
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
            else System.out.println(node.name + " was added already.");
        }

        Node findNode(String name) {
            for (Node node : this.nodes.keySet()) {
                if (node.name == name) return node;
            }
            return null;
        }

        void addEdge(String start, String end) {
            Node from = this.findNode(start);
            Node to = this.findNode(end);

            if (from == null || to == null) {
                System.out.println(start + " or " + end + " is not on the map.");
            } else if (!this.isNewEdge(from, to)) {
                System.out.println("The edge " + from.name + " -> " + to.name + " is already added in.");
            } else {
                this.nodes.get(from).add(to);
                this.edges.add(new Edge(from, to));
            }
        }

        boolean isNewEdge(Node from, Node to) {
            return !(this.nodes.containsKey(from) && this.nodes.get(from).contains(to));
        }

        List<Node> getDestinations(Node node) {
            List<Node> temp = new LinkedList<>(this.nodes.get(node));
            return temp;
        }
    }

    static class Graph extends DiGraph {
        @Override
        void addEdge(String from, String to) {
            super.addEdge(from, to);
            super.addEdge(to, from);
        }
    }
}
