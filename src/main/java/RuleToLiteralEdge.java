import com.fasterxml.jackson.annotation.JsonIgnore;

public class RuleToLiteralEdge extends Edge{
    boolean dependency;

    public RuleToLiteralEdge(Node startNode, Node endNode, boolean dependency) {
        super(startNode, endNode);
        this.dependency = dependency;
    }

    @Override
    @JsonIgnore
    public String getEdgeElement() {
        String edgeElement = (dependency ? "+" : "-");
        return edgeElement;
    }

    public boolean getDependency(){
        return dependency;
    }
}
