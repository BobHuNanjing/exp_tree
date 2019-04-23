import com.fasterxml.jackson.annotation.JsonIgnore;

public class LiteralToRuleEdge extends Edge{

    public LiteralToRuleEdge(Node startNode, Node endNode) {
        super(startNode, endNode);
    }

    @Override
    @JsonIgnore
    public String getEdgeElement() {
        String edgeElement = "";
        return edgeElement;
    }
}
