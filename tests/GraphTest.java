import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.*;

import static guru.nidi.graphviz.model.Factory.graph;
import static java.lang.Boolean.TRUE;

public class GraphTest {
    public static void main(String[] args) throws IOException {
        MutableGraph graph = mutGraph().setDirected(true);
        MutableNode a = mutNode("a");
        MutableNode b = mutNode("b");
        MutableNode c = mutNode("c");
        a.addLink(to(b));
        a.addLink(to(c));
        graph.add(a);
        Graphviz.fromGraph(graph).scale(4).render(Format.PNG).toFile(new File("example/ex1i.png"));
    }

}
