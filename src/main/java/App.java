import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.graphviz.engine.Graphviz;

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
        //explanationSpace.displayTheSpace();
        explanationSpace.setIdForEveryNode();
        explanationSpace.updateNodeOnEdges();
        /*ObjectMapper objectMapper = new ObjectMapper();
        dumpListIntoJson(explanationSpace.literalNodeList);
        dumpListIntoJson(explanationSpace.ruleNodeList);
        dumpListIntoJson(explanationSpace.ruleToLiteralEdgeList);
        dumpListIntoJson(explanationSpace.literalToRuleEdgeList);*/
        GraphvizDraw graphvizDraw = new GraphvizDraw(explanationSpace);
        graphvizDraw.drawNode();
        graphvizDraw.drawEdge();
        graphvizDraw.displayGraph();

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
