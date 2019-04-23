import java.util.ArrayList;

/**
 * 解释空间
 */
public class ExplanationSpace {
    ArrayList<LiteralNode> literalNodeList = new ArrayList<>();
    ArrayList<RuleNode> ruleNodeList = new ArrayList<>();
    ArrayList<LiteralToRuleEdge> literalToRuleEdgeList = new ArrayList<>();
    ArrayList<RuleToLiteralEdge> ruleToLiteralEdgeList = new ArrayList<>();

    public void initExplanationSpace() {
        RuleLoader ruleLoader = new RuleLoader("rules.txt");
        ruleLoader.loadRulesIntoList();
        ArrayList<Rule> rList = ruleLoader.getRules();
        constructionOfNodeAndEdge(rList);
    }

    public LiteralNode litNodeExist(String literal) {
        for (LiteralNode literalNode : literalNodeList) {
            if (literalNode.getAtom().equals(literal) && literalNode.getAtom().length()!=0)
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
            if(headLit.length() == 0)
                continue;
            LiteralNode literalNode = nodeInitialize(headLit);
            //头部节点：点+边（lit-rule）
            LiteralToRuleEdge literalToRuleEdge = new LiteralToRuleEdge(literalNode, ruleNode);
            literalNode.setEdgeList(literalToRuleEdge);
            literalToRuleEdgeList.add(literalToRuleEdge);
            //ruleNode.setAncestorNodeList(literalNode);
        }

        for (String posLit : r.getPositiveBody()) {
            if(posLit.length() == 0)
                continue;
            LiteralNode literalNode = nodeInitialize(posLit);
            //正体部节点：点+边（rule-lit）
            //literalNode.setAncestorNodeList(ruleNode);
            RuleToLiteralEdge ruleToLiteralEdge = new RuleToLiteralEdge(ruleNode, literalNode, true);
            ruleToLiteralEdgeList.add(ruleToLiteralEdge);
            ruleNode.setEdgeList(ruleToLiteralEdge);
        }

        for (String negLit : r.getNegativeBody()) {
            if(negLit.length() == 0)
                continue;
            LiteralNode literalNode = nodeInitialize(negLit);
            //负体部节点：点+边（rule-lit）
            //literalNode.setAncestorNodeList(ruleNode);
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

    public void setIdForEveryNode(){
        int count = 0;
        for (LiteralNode literalNode : literalNodeList) {
            literalNode.setId(count);
            count++;
        }

        for (RuleNode ruleNode : ruleNodeList) {
            ruleNode.setId(count);
            count++;
        }
    }

    public void updateNodeOnEdges(){
        for (Edge e : ruleToLiteralEdgeList) {
            e.setSource();
            e.setTarget();
        }

        for (Edge e : literalToRuleEdgeList) {
            e.setSource();
            e.setTarget();
        }

    }

    public void addNodeByType(Node node) throws ClassCastException{
        if(node instanceof LiteralNode){
            literalNodeList.add((LiteralNode) node);
        }

        else if(node instanceof RuleNode){
            ruleNodeList.add((RuleNode) node);
        }
    }

    public LiteralNode getNodeByAtom(String atom) {
        for (LiteralNode lit: literalNodeList) {
            //System.out.println(lit.getAtom()+","+atom);
            if(lit.getAtom().equals(atom))
                return lit;
        }
        return null;
    }
}
