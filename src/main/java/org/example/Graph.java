package org.example;

import java.util.*;

public class Graph {

    private final Map<String, Node> nodes = new HashMap<>();
    private final Map<Node, Integer> incomingEdgesCount = new HashMap<>();

    private final List<Edge> edges = new ArrayList<>();

    private final Stack<Node> st = new Stack<>();

    private final Map<Node,Boolean> visited = new HashMap<>();

    public void prepare(List<DBRow> rows) {
        for (DBRow row: rows) {
            nodes.putIfAbsent(row.getSrc(), new Node(row.getSrc(), new ArrayList<>()));
            nodes.putIfAbsent(row.getDst(), new Node(row.getDst(), new ArrayList<>()));

            final Node srcNode = nodes.get(row.getSrc());
            final Node dstNode = nodes.get(row.getDst());
            final Edge edge = new Edge(srcNode, dstNode, row.getWeight());
            edges.add(edge);
            srcNode.getEdges().add(edge);

            incomingEdgesCount.putIfAbsent(dstNode, 0);
            incomingEdgesCount.put(dstNode, incomingEdgesCount.get(dstNode) + 1);
        }
    }

    public void simplify() {
        for (Node node: nodes.values()) {
            Integer incomingCount = incomingEdgesCount.get(node);
            if (incomingCount == null || incomingCount == 0) {
                simplify1(node);
            }
        }
        removeZeroWeightEdges();
    }

    private void removeZeroWeightEdges() {
        for (Node node : nodes.values()) {
            List<Edge> validEdges = new ArrayList<>();
            for (Edge edge : node.getEdges()) {
                if (edge.getWeight() > 0) {  // Check if the edge weight is greater than zero
                    validEdges.add(edge);
                }
            }
            node.setEdges(validEdges);  // Update the node's edges to only include valid edges
        }
    }


    public void simplify1(Node srcNode) {
        if (srcNode == null || srcNode.getEdges().isEmpty()) {
            return;
        }
        // Initialize visited map and stack
        for (Node node : nodes.values()) {
            visited.put(node, false);
        }
        pushToStack(st,srcNode);
        // Start by pushing the source node
        while (!st.isEmpty()) {
            final Node node = st.pop();

            // Check if the node has been visited
            if (!visited.get(node)) {
                visited.put(node, true);
                List<Edge> newEdges = new ArrayList<>(node.getEdges());

                for (Edge edge : node.getEdges()) {
                    final Node neighbour = edge.getDst();
                    List<Edge> neighbourEdges = neighbour.getEdges();
                    final double edgeWt = edge.getWeight();

                    for (Edge neighbourEdge : neighbourEdges) {
                        final Node transitiveDst = neighbourEdge.getDst();
                        final double transWt = edgeWt * neighbourEdge.getWeight();
                        boolean found = false;

                        for (Edge newEdge : newEdges) {
                            if (newEdge.getDst().getVal().equals(transitiveDst.getVal())) {
                                newEdge.setWeight(newEdge.getWeight() + transWt);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            newEdges.add(new Edge(node, transitiveDst, transWt));
                        }
                        edge.setWeight(edge.getWeight() - transWt);
                    }
                    neighbour.setEdges(new ArrayList<>());
                }
                node.setEdges(newEdges);
            }
        }
    }

    private void pushToStack(Stack<Node> st, Node srcNode) {
        // Use a HashSet to avoid pushing duplicates to the stack
        Set<Node> seen = new HashSet<>();
        Stack<Node> temp = new Stack<>();
        temp.push(srcNode);
        seen.add(srcNode);

        while (!temp.isEmpty()) {
            Node node = temp.pop();
            st.push(node); // Assumed it's already checked or marked as seen
            for (Edge edge : node.getEdges()) {
                Node neighbour = edge.getDst();
                if (seen.add(neighbour)) { // Only add if it hasn't been seen before
                    temp.push(neighbour);
                }
            }
        }
    }


    public void print() {
        for (Node node: nodes.values()) {
            Integer incomingCount = incomingEdgesCount.get(node);
            if (incomingCount == null || incomingCount == 0) {
                print(node);
            }
        }
    }

    public void print(Node srcNode) {
        if (srcNode == null || srcNode.getEdges() == null || srcNode.getEdges().isEmpty()) {
            return;
        }
        for (Edge edge: srcNode.getEdges()) {
            System.out.println(edge.getSrc() + " -> " + edge.getDst() + ": " + edge.getWeight());
            print(edge.getDst());
        }
    }

    public void printAllEdges() {
        for (Edge edge : edges) {
            System.out.println(edge.getSrc().getVal() + " -> " + edge.getDst().getVal() + ": " + edge.getWeight());
        }
    }
}
