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
        explanationSpace.setIdForEveryNode();
        explanationSpace.updateNodeOnEdges();
        GraphvizDraw graphvizDraw = new GraphvizDraw(explanationSpace);
        graphvizDraw.drawNode();
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
