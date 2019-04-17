public class RuleToLiteralEdge extends Edge{
    boolean dependency;

    public RuleToLiteralEdge(Node startNode, Node endNode, boolean dependency) {
        super(startNode, endNode);
        this.dependency = dependency;
    }

    @Override
    public String getEdgeElement() {
        String edgeElement = "edge type:" + "Rule-To-Literal;" + "dependency:" + (dependency ? "+" : "-");
        return edgeElement;
    }
}
