import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    private List<Edge> edgeList = new ArrayList<>();
    private List<Node> ancestorNodeList = new ArrayList<>();

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Edge edge) {
        edgeList.add(edge);
    }

    public List<Node> getAnscestorNodeList() {
        return ancestorNodeList;
    }

    public void setAncestorNodeList(Node ancestorNode) {
        if(ancestorNode != null)
            ancestorNodeList.add(ancestorNode);
    }

    public abstract String getNodeElement();
}
