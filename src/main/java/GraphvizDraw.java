import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static guru.nidi.graphviz.attribute.Shape.RECTANGLE;
import static guru.nidi.graphviz.model.Factory.*;

public class GraphvizDraw {
    ArrayList<RuleNode> ruleNodeArrayList = null;
    ArrayList<LiteralNode> literalNodeArrayList = null;
    ArrayList<RuleToLiteralEdge> ruleToLiteralEdgeArrayList = null;
    ArrayList<LiteralToRuleEdge> literalToRuleEdgeArrayList = null;
    MutableGraph g;
    ArrayList<MutableNode> mutableNodes = new ArrayList<>();
    ExplanationSpace subSpace = new ExplanationSpace();

    public GraphvizDraw(ExplanationSpace explanationSpace) {
        this.ruleNodeArrayList = explanationSpace.ruleNodeList;
        this.literalNodeArrayList = explanationSpace.literalNodeList;
        this.ruleToLiteralEdgeArrayList = explanationSpace.ruleToLiteralEdgeList;
        this.literalToRuleEdgeArrayList = explanationSpace.literalToRuleEdgeList;
        g = mutGraph().setDirected(true);
    }

    /**
     * 给定一个currentNode，创建一个从此出发的子图（花琪论文）
     *
     * @param currentNode
     */
    public ExplanationSpace subgraphMatch(ExplanationSpace graph, HashSet<String> answerSet, Node currentNode, HashSet<Node> ancestorList, boolean isNeg) {

        ExplanationSpace res = new ExplanationSpace();
        if (isIllegal(currentNode, answerSet, ancestorList, isNeg))
            return null;

        Node curNode = null;

        //当前结点为文字结点
        if (currentNode instanceof LiteralNode) {
            curNode = new LiteralNode(currentNode.getNodeElement());
            subSpace.addNodeByType(curNode);
            ancestorList.add(curNode);
            if (isNeg) {
                return null;
            } else {
                for (Edge e : currentNode.getEdgeList()) {
                    res = subgraphMatch(graph, answerSet, e.endNode, ancestorList, false);
                    if (res != null) {
                        Rule rule = ((RuleNode) e.endNode).getRule();
                        RuleNode rNode = new RuleNode(rule);
                        LiteralToRuleEdge literalToRuleEdge = new LiteralToRuleEdge(curNode, rNode);
                        curNode.setEdgeList(literalToRuleEdge);
                    }
                }
            }
            ancestorList.remove(curNode);
        } else {
            if (currentNode instanceof RuleNode) {
                for (Edge e : currentNode.getEdgeList()) {
                    ExplanationSpace tmp1 = null , tmp2 = null;
                    if (((RuleToLiteralEdge) e).getDependency()) {
                        tmp1 = subgraphMatch(graph, answerSet, e.endNode, ancestorList, false);
                    } else {
                        tmp2 = subgraphMatch(graph, answerSet, e.endNode, ancestorList, true);
                    }
                    if(tmp1 == null && tmp2 == null){
                        res = null;
                    }else{
                        res = new ExplanationSpace();
                    }
                    if(!(res == null && subSpace.getNodeByAtom(e.endNode.getNodeElement())==null)){
                    curNode = new RuleNode(((RuleNode) currentNode).getRule());
                    LiteralNode literalNode = new LiteralNode(e.endNode.getNodeElement());
                    RuleToLiteralEdge ruleToLiteralEdge = new RuleToLiteralEdge(curNode, literalNode, ((RuleToLiteralEdge) e).getDependency());
                    curNode.setEdgeList(ruleToLiteralEdge);
                    subSpace.addNodeByType(literalNode);
                    subSpace.addNodeByType(curNode);
                    res = new ExplanationSpace();
                    }
                }
            }
        }
        return res;
    }


//    public ExplanationSpace subgraphMatch2(ExplanationSpace graph, HashSet<String> answerSet,Node currentNode, HashSet<Node> ancestorList, boolean isNeg){
//        ExplanationSpace subSpace = new ExplanationSpace();
//        //当前待解释节点必为literal，且在顶部
//        LiteralNode subSpaceLiteralNode = new LiteralNode(currentNode.getNodeElement());
//        subSpace.addNodeByType();
//    }


    private boolean isIllegal(Node currentNode, HashSet<String> answerSet, HashSet<Node> ancestorList, boolean isNeg) {
        if ((answerSet.contains(currentNode.getNodeElement()) && isNeg) || (!answerSet.contains(currentNode.getNodeElement()) && !isNeg && currentNode instanceof LiteralNode) || (ancestorList.contains(currentNode)))
            return true;
        else
            return false;
    }

    public void drawNode() {
        for (LiteralNode litNode : literalNodeArrayList) {
            MutableNode mutableNode = mutNode(litNode.getNodeElement());
            for (Edge e : litNode.getEdgeList()) {
                MutableNode endNode = mutNode(e.endNode.getNodeElement());
                mutableNode.addLink(to(endNode.port(Compass.NORTH)).add(Arrow.VEE));
            }
            mutableNodes.add(mutableNode);
            g.add(mutableNode);
        }

        for (RuleNode ruleNode : ruleNodeArrayList) {
            MutableNode mutableNode = mutNode(ruleNode.getNodeElement()).add(Shape.RECTANGLE);
            for (Edge e : ruleNode.getEdgeList()) {
                MutableNode endNode = mutNode(e.endNode.getNodeElement());
                mutableNode.addLink(to(endNode.port(Compass.NORTH)).add(Label.of(e.getEdgeElement())).add(Arrow.VEE));
            }
            mutableNodes.add(mutableNode);
            g.add(mutableNode);
        }
    }

    /*public void drawEdge() {
        for (RuleToLiteralEdge ruleToLiteralEdge : ruleToLiteralEdgeArrayList) {
            g = g.with(node(ruleToLiteralEdge.startNode.getNodeElement()).);
        }

        for (LiteralToRuleEdge literalToRuleEdge : literalToRuleEdgeArrayList) {
            g = g.with(node(literalToRuleEdge.startNode.getNodeElement()).link(node(literalToRuleEdge.endNode.getNodeElement())));
        }

    }*/

    public void displayGraph() {
        try {
            Graphviz.fromGraph(g).scale(0.5).render(Format.SVG).toFile(new File("example/ex12.svg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}