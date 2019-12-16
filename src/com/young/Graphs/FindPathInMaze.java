package com.young.Graphs;

import java.util.*;

public class FindPathInMaze {

    public static void main(String[] args) {
        int[][] maze = new int[9][9];
        maze[0] = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1};
        maze[1] = new int[] {0, 1, 1, 1, 1, 0, 0, 1, 0};
        maze[2] = new int[] {0, 1, 0, 0, 1, 1, 1, 1, 0};
        maze[3] = new int[] {1, 1, 1, 0, 1, 0, 0, 0, 0};
        maze[4] = new int[] {1, 0, 1, 1, 1, 1, 1, 1, 1};
        maze[5] = new int[] {1, 1, 0, 0, 0, 0, 0, 0, 0};
        maze[6] = new int[] {0, 1, 0, 0, 1, 1, 1, 1, 0};
        maze[7] = new int[] {0, 1, 0, 0, 1, 0, 0, 1, 0};
        maze[8] = new int[] {0, 1, 1, 1, 1, 0, 0, 1, 1};

        int[][] maze2 = new int[6][7];
        maze2[0] = new int[] {1, 1, 1, 1, 1, 1, 1};
        maze2[1] = new int[] {1, 0, 1, 1, 1, 0, 1};
        maze2[2] = new int[] {1, 1, 0, 0, 0, 0, 1};
        maze2[3] = new int[] {1, 1, 1, 1, 1, 0, 1};
        maze2[4] = new int[] {1, 1, 1, 1, 1, 1, 1};
        maze2[5] = new int[] {1, 1, 1, 1, 1, 1, 1};


        findingPath(maze, "0:8", "8:8", "DiGraph", "DFS");
        findingPath(maze2, "1:4", "1:1", "DiGraph", "BFS");
    }

    static void findingPath(int[][] maze, String from, String to, String gType, String sType) {
        DiGraph graph;
        if (gType == "DiGraph") graph = new DiGraph();
        else graph = new Graph();

        buildNodes(graph, maze);
        buildEdges(graph, maze);

//        Iterator<String> it = graph.nodes.keySet().iterator();
//        while (it.hasNext()) {
//            System.out.print(it.next());
//            if (it.hasNext()) System.out.print(" -> ");
//        }

        List<String> result = sType == "BFS" ? findBFS(graph, from, to) :
                findDFS(graph, from, to, new LinkedList<>(), new LinkedList<>());

        if (result == null || result.size() < 1) {
            System.out.println("\n" + sType + ": There is no path of " + from + " -> " + to + "\n");
        } else {
            String prefix = "\n" + sType + ": The shortest path of " + from + " -> " + to + " is\n     ";
            printPath(result, prefix);
        }
    }

    static List<String> findBFS(DiGraph graph, String from, String to) {
        Queue<List<String>> Q = new LinkedList<>();
        List<String> temp = new LinkedList<>();
        temp.add(from);
        Q.add(temp);
        while (Q.size() > 0) {
            temp = Q.poll();
            String lastNode = temp.get(temp.size()-1);
            if (lastNode.equals(to)) return temp;
            for (String node : graph.getDestination(lastNode)) {
                if (temp.contains(node)) continue;
                List<String> newPass = new LinkedList<>(temp);
                newPass.add(node);
                Q.add(newPass);
            }
        }
        return null;
    }

    static List<String> findDFS(DiGraph graph, String from, String to, List<String> path, List<String> shortPath) {
        path.add(from);
        if (from.equals(to)) return path;
        if (shortPath.size() > 0 && path.size() >= shortPath.size()) return null;

        for (String node : graph.getDestination(from)) {
            if (path.contains(node)) continue;;
            List<String> newPath = findDFS(graph, node, to, new LinkedList<>(path), new LinkedList<>(shortPath));
            if (newPath != null) shortPath = newPath;
        }
        return shortPath;
    }

    static void buildNodes(DiGraph graph, int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (maze[r][c] > 0) {
                    String name = r + ":" + c;
                    graph.addNode(name);
                }

            }
        }
    }

    static void buildEdges(DiGraph graph, int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (maze[r][c] > 0) {
                    String key = r + ":" + c;
                    addEdgeToGraph(graph, key, r-1, c, maze);
                    addEdgeToGraph(graph, key, r+1, c, maze);
                    addEdgeToGraph(graph, key, r, c-1, maze);
                    addEdgeToGraph(graph, key, r, c+1, maze);
                }
            }
        }
    }

    static void addEdgeToGraph(DiGraph graph, String from, int r, int c, int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        if (r < 0 || r >= n || c < 0 || c >= m || maze[r][c] < 1) return;
        String to = r + ":" + c;
        graph.addEdge(from, to);
    }

    static void printPath(List<String> path, String prefix) {
        System.out.print(prefix);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" -> ");
        }
        System.out.println("\n");
    }

    static class Edge {
        String from;
        String to;
        Edge(String from, String to) {
            this.from = from;
            this.to = to;
        }

        boolean equalTo(Edge edge) {
            return this.from == edge.from && this.to == edge.to;
        }
    }

    static class DiGraph {
        List<Edge> edges;
        Map<String, List<String>> nodes;

        DiGraph() {
            this.edges = new LinkedList<>();
            this.nodes = new HashMap<>();
        }

        void addNode(String name) {
            if (!this.nodes.containsKey(name)) this.nodes.put(name, new LinkedList<>());
            else System.out.println("This node is already added in.");
        }

        void addEdge(String from, String to) {
            if (!this.nodes.containsKey(from) || !this.nodes.containsKey(to)) {
                System.out.println("This edge is not in this Maze.  " + from + "->" + to);
            } else if (this.isNewEdge(from, to)) {
                this.nodes.get(from).add(to);
                this.edges.add(new Edge(from, to));
            }
        }

        boolean isNewEdge(String from, String to) {
            if (this.nodes.containsKey(from) && this.nodes.get(from).contains(to)) return false;
            return true;
        }

        List<String> getDestination(String name) {
            return this.nodes.get(name);
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

