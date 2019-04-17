import java.util.ArrayList;

/**
 * 解释空间
 */
public class ExplanationSpace {
    ArrayList<LiteralNode> literalNodeList = new ArrayList<>();
    ArrayList<RuleNode> ruleNodeList = new ArrayList<>();
    ArrayList<LiteralToRuleEdge> literalToRuleEdgeList = new ArrayList<>();
    ArrayList<RuleToLiteralEdge> ruleToLiteralEdgeList = new ArrayList<>();

    public ExplanationSpace() {
        RuleLoader ruleLoader = new RuleLoader("rules.txt");
        ruleLoader.loadRulesIntoList();
        ArrayList<Rule> rList = ruleLoader.getRules();
        constructionOfNodeAndEdge(rList);
    }

    public LiteralNode litNodeExist(String literal) {
        for (LiteralNode literalNode : literalNodeList) {
            if (literalNode.getAtom().equals(literal))
                return literalNode;
        }
        return null;
    }

    private void constructionOfNodeAndEdge(ArrayList<Rule> rList) {
        for (Rule r : rList) {
            RuleNode ruleNode = new RuleNode(r);
            ruleNodeList.add(ruleNode);
            setNodeAndEdge(r, ruleNode);
        }
    }

    private void setNodeAndEdge(Rule r, RuleNode ruleNode) {
        for (String headLit : r.getHead()) {
            LiteralNode literalNode = nodeInitialize(headLit);
            //头部节点：点+边（lit-rule）
            LiteralToRuleEdge literalToRuleEdge = new LiteralToRuleEdge(literalNode, ruleNode);
            literalNode.setEdgeList(literalToRuleEdge);
            literalToRuleEdgeList.add(literalToRuleEdge);
            ruleNode.setAncestorNodeList(literalNode);
        }

        for (String posLit : r.getPositiveBody()) {
            LiteralNode literalNode = nodeInitialize(posLit);
            //正体部节点：点+边（rule-lit）
            literalNode.setAncestorNodeList(ruleNode);
            RuleToLiteralEdge ruleToLiteralEdge = new RuleToLiteralEdge(ruleNode, literalNode, true);
            ruleToLiteralEdgeList.add(ruleToLiteralEdge);
            ruleNode.setEdgeList(ruleToLiteralEdge);
        }

        for (String negLit : r.getNegativeBody()) {
            LiteralNode literalNode = nodeInitialize(negLit);
            //负体部节点：点+边（rule-lit）
            literalNode.setAncestorNodeList(ruleNode);
            RuleToLiteralEdge ruleToLiteralEdge = new RuleToLiteralEdge(ruleNode, literalNode, false);
            ruleToLiteralEdgeList.add(ruleToLiteralEdge);
            ruleNode.setEdgeList(ruleToLiteralEdge);
        }


    }

    private LiteralNode nodeInitialize(String litStr) {
        LiteralNode literalNode = litNodeExist(litStr);
        if (literalNode == null) {
            literalNode = new LiteralNode(litStr);
            literalNodeList.add(literalNode);
        }
        return literalNode;
    }

    public void displayTheSpace() {
        System.out.println("literal node list:");
        for (LiteralNode literalNode : literalNodeList) {
            System.out.println("atom:" + literalNode.getAtom());
            System.out.println("all the edges:");

            for (Edge edge : literalNode.getEdgeList()) {
                System.out.println("edge type:" + edge.getClass().toString().replace("class ", "")
                        + ";connected node:" + edge.endNode.getNodeElement());
            }
        }

        System.out.println("rule node list:");
        for (RuleNode ruleNode : ruleNodeList) {
            System.out.println("rule:" + ruleNode.getNodeElement());
            for (Edge edge : ruleNode.getEdgeList()) {
                    System.out.println(edge.getEdgeElement() + ";connected node:" + edge.endNode.getNodeElement());
                }

            }

    }
}
