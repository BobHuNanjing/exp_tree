import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class App {
    public static void main(String[] args) {
        ExplanationSpace explanationSpace = new ExplanationSpace();
        explanationSpace.initExplanationSpace();
        explanationSpace.setIdForEveryNode();
        explanationSpace.updateNodeOnEdges();
        GraphvizDraw graphvizDraw = new GraphvizDraw(explanationSpace);
        //graphvizDraw.drawNode();
        //graphvizDraw.displayGraph();
        HashSet<String> answerSet = new HashSet<>();
        answerSet.add("\"不建议复电\"");
        answerSet.add("\"存在天气风险\"");
        answerSet.add("\"位于三跨点\"");
        answerSet.add("\"不复电\"");
        HashSet<Node> ancestorList = new HashSet<>();
        LiteralNode currentNode = explanationSpace.getNodeByAtom("\"不建议复电\"");
        graphvizDraw.subgraphMatch(explanationSpace, answerSet, currentNode, ancestorList, false);
        ExplanationSpace subGraph = graphvizDraw.subSpace;
        subGraph.displayTheSpace();
        GraphvizDraw graphvizDraw1 = new GraphvizDraw(subGraph);
        graphvizDraw1.drawNode();
        graphvizDraw1.displayGraph();
    }

//    private static void dumpListIntoJson(ArrayList<?> NodeList) {
//        final ByteArrayOutputStream out = new ByteArrayOutputStream();
//        final ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            mapper.writeValue(out, NodeList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        final byte[] data = out.toByteArray();
//        System.out.println(new String(data));

}
