public class LiteralToRuleEdge extends Edge{

    public LiteralToRuleEdge(Node startNode, Node endNode) {
        super(startNode, endNode);
    }

    @Override
    public String getEdgeElement() {
        String edgeElement = "edge type:" + "Literal-To-Rule;";
        return edgeElement;
    }
}
