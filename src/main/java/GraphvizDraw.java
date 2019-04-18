import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static guru.nidi.graphviz.model.Factory.*;

public class GraphvizDraw {
    ArrayList<RuleNode> ruleNodeArrayList = null;
    ArrayList<LiteralNode> literalNodeArrayList = null;
    ArrayList<RuleToLiteralEdge> ruleToLiteralEdgeArrayList = null;
    ArrayList<LiteralToRuleEdge> literalToRuleEdgeArrayList = null;
    Graph g;

    public GraphvizDraw(ExplanationSpace explanationSpace) {
        this.ruleNodeArrayList = explanationSpace.ruleNodeList;
        this.literalNodeArrayList = explanationSpace.literalNodeList;
        this.ruleToLiteralEdgeArrayList = explanationSpace.ruleToLiteralEdgeList;
        this.literalToRuleEdgeArrayList = explanationSpace.literalToRuleEdgeList;
        g = graph("Example").directed().graphAttr().with(RankDir.TOP_TO_BOTTOM);
    }

    public void drawNode() {
        for (LiteralNode litNode : literalNodeArrayList) {
            g = g.with(node(litNode.getAtom()));
        }

        for (RuleNode ruleNode : ruleNodeArrayList) {
            g = g.with(node(ruleNode.getNodeElement()).with(Shape.RECTANGLE));
        }

    }

    public void drawEdge() {
        for (RuleToLiteralEdge ruleToLiteralEdge : ruleToLiteralEdgeArrayList) {
            g = g.with(node(ruleToLiteralEdge.startNode.getNodeElement()).link(node(ruleToLiteralEdge.endNode.getNodeElement())));
        }

        for (LiteralToRuleEdge literalToRuleEdge : literalToRuleEdgeArrayList) {
            g = g.with(node(literalToRuleEdge.startNode.getNodeElement()).link(node(literalToRuleEdge.endNode.getNodeElement())));
        }

    }

    public void displayGraph(){
        try {
            Graphviz.fromGraph(g).height(1000).render(Format.SVG).toFile(new File("example/ex1.svg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}