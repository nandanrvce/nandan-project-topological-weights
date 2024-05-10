I have a graph with followign definition:
@AllArgsConstructor
@Getter
@Setter
public class Node {
String val;
List<Edge> edges;
}

@AllArgsConstructor
@Setter
@Getter
public class Edge {

    Node src;
    Node dst;

    Double weight;
}


public class Graph {

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, Integer> incomingEdgesCount = new HashMap<>();

    private void simplify(Node node) {


    }
}
Implement above simplify method which takes a node and transitively simplifies the weights of edges by multiplying them Using topological sorting just like we do while simplifying expenses in splitwise.
while merging the 2 edges, you have to multiple the weights, reduce the transferred weight from the original edge and add a new edge with the multiplied value example if A to B has weight 0.25 and B to C has 0.5 then result should remain with 2 edges with A to C as 0.125 and A to B also as 0.125. Another example if A to B is 0.5 and B to C is 0.5 and B to D is 0.5 then result should be A to C is 0.25 and A to D is 0.25.
if you have any questions then first ask. Also, if you make the mistake again, then you will be penalized.