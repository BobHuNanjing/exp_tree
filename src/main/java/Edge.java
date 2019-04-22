public abstract class Edge {
    Node startNode;
    Node endNode;

    int source;
    int target;


    public Edge(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public int getSource() {
        return source;
    }

    public void setSource() {
        source = startNode.id;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget() {
        target = endNode.id;
    }

    public abstract String getEdgeElement();


}
