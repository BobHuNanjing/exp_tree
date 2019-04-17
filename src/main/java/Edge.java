public abstract class Edge {
    Node startNode;
    Node endNode;

    public Edge(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public abstract String getEdgeElement();


}
