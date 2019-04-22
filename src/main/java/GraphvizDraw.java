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

    public GraphvizDraw(ExplanationSpace explanationSpace) {
        this.ruleNodeArrayList = explanationSpace.ruleNodeList;
        this.literalNodeArrayList = explanationSpace.literalNodeList;
        this.ruleToLiteralEdgeArrayList = explanationSpace.ruleToLiteralEdgeList;
        this.literalToRuleEdgeArrayList = explanationSpace.literalToRuleEdgeList;
        g = mutGraph().setDirected(true);
    }

    /**
     * 给定一个currentNode，创建一个从此出发的子图
     * @param currentNode
     */
    public ExplanationSpace subgraphMatch(ExplanationSpace graph, HashSet<String> answerSet,Node currentNode, HashSet<Node> ancestorList, boolean isNeg){
        ExplanationSpace subSpace = null;
        if(isIllegal(currentNode, answerSet, ancestorList, isNeg))
            return subSpace;

        Node curNode;

        if(currentNode instanceof LiteralNode){
            curNode = new LiteralNode(currentNode.getNodeElement());
        }
        else if(currentNode instanceof RuleNode){
            curNode = new RuleNode(((RuleNode) currentNode).getRule());
        }else
            curNode = null;

        ancestorList.add(curNode);

        subSpace.addNodeByType(curNode);

        //当前结点为文字结点
        if(curNode instanceof LiteralNode){
            if(isNeg) {
                return subSpace;
            }
            else{
                for (Edge e : currentNode.getEdgeList()) {
                    subSpace = subgraphMatch(graph, answerSet, e.endNode, ancestorList, false);
                    if(subSpace != null) {
                        Rule rule = ((RuleNode)e.endNode).getRule();
                        RuleNode rNode = new RuleNode(rule);
                        LiteralToRuleEdge literalToRuleEdge = new LiteralToRuleEdge(curNode, rNode);
                        curNode.setEdgeList(literalToRuleEdge);
                    }
                }
            }
        }
        else{

        }
        return subSpace;
    }

    private boolean isIllegal(Node currentNode, HashSet<String> answerSet, HashSet<Node> ancestorList, boolean isNeg) {
        if ((answerSet.contains(currentNode.getNodeElement()) && isNeg) || (!answerSet.contains(currentNode.getNodeElement()) && !isNeg) || (ancestorList.contains(currentNode)))
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

    public void displayGraph(){
        try {
            Graphviz.fromGraph(g).scale(0.5).render(Format.SVG).toFile(new File("example/ex1.svg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}