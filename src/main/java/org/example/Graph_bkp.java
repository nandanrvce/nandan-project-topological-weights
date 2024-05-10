//package org.example;
//
//import java.util.*;
//
//public class Graph {
//
//    private Map<String, Node> nodes = new HashMap<>();
//    private Map<Node, Integer> incomingEdgesCount = new HashMap<>();
//
//    private List<Edge> edges = new ArrayList<>();
//
//    public void prepare(List<DBRow> rows) {
//        for (DBRow row: rows) {
//            nodes.putIfAbsent(row.getSrc(), new Node(row.getSrc(), new ArrayList<>()));
//            nodes.putIfAbsent(row.getDst(), new Node(row.getDst(), new ArrayList<>()));
//
//            final Node srcNode = nodes.get(row.getSrc());
//            final Node dstNode = nodes.get(row.getDst());
//            final Edge edge = new Edge(srcNode, dstNode, row.getWeight());
//            edges.add(edge);
//            srcNode.getEdges().add(edge);
//
//            incomingEdgesCount.putIfAbsent(dstNode, 0);
//            incomingEdgesCount.put(dstNode, incomingEdgesCount.get(dstNode) + 1);
//        }
//    }
//
//    public void simplify() {
//        for (Node node: nodes.values()) {
//            Integer incomingCount = incomingEdgesCount.get(node);
//            if (incomingCount == null || incomingCount == 0) {
//                simplify1(node);
//            }
//        }
//    }
//
//    public void simplify1(Node node) {
//        if (node == null) {
//            return;
//        }
//        if (node.getEdges() == null || node.getEdges().isEmpty()) {
//            return;
//        }
//        final List<Edge> newEdges = new ArrayList<>(node.getEdges());
//
//        for (Edge edge: node.getEdges()) {
//            final Node neighbour = edge.getDst();
//            simplify1(neighbour);
//            List<Edge> neighbourEdges = neighbour.getEdges();
//
//            final Double edgeWt = edge.getWeight();
//            for (Edge neighbourEdge : neighbourEdges) {
//                final Node transitiveDst = neighbourEdge.getDst();
//                final double transWt = edgeWt * neighbourEdge.getWeight();
//                boolean found = false;
//                for (Edge newEdge : newEdges) {
//                    if (newEdge.getDst().getVal().equals(transitiveDst.getVal())) {
//                        newEdge.setWeight(newEdge.getWeight() + transWt);
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    newEdges.add(new Edge(node, transitiveDst, transWt));
//                }
//                edge.setWeight(edge.getWeight() - transWt);
//            }
//            neighbour.setEdges(new ArrayList<>());
//        }
//        node.setEdges(newEdges);
//    }
//
//    // Depth First Search to calculate transitive weights
//    private void dfs(Node startNode, Node currentNode, double currentWeight, Set<Node> visited, Map<Node, Double> transitiveWeights) {
//        // Prevent cycles by using a visited set
//        if (!visited.add(currentNode)) return;
//
//        // If not starting node, calculate and update weight
//        if (currentNode != startNode) {
//            transitiveWeights.merge(currentNode, currentWeight, Math::min);
//        }
//
//        // Visit each adjacent node
//        for (Edge edge : currentNode.edges) {
//            dfs(startNode, edge.dst, currentWeight * edge.weight, visited, transitiveWeights);
//        }
//
//        // Remove from visited to allow other paths
//        visited.remove(currentNode);
//    }
//
//    public void print() {
//        for (Node node: nodes.values()) {
//            Integer incomingCount = incomingEdgesCount.get(node);
//            if (incomingCount == null || incomingCount == 0) {
//                print(node);
//            }
//        }
//    }
//
//    public void print(Node node) {
//        if (node == null || node.getEdges() == null || node.getEdges().isEmpty()) {
//            return;
//        }
//        for (Edge edge: node.getEdges()) {
//            System.out.println(edge.getSrc() + " -> " + edge.getDst() + ": " + edge.getWeight());
//            print(edge.getDst());
//        }
//    }
//}
