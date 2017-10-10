package info.tahmidchoyon.graphyity.processor;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.Random;

public class MultiGraphCreator {

    private static Graph graph;

    public MultiGraphCreator() {
        graph = new MultiGraph(getRandomString(12));
        graph.display();
    }

    public String addNode(String node) {
        if (graph.getNode(node) == null) {
            graph.addNode(node);
            return "Node added : ".concat(node);
        } else {
            return "Error: Node ".concat(node).concat(" is already in graph");
        }
    }

    public String removeNode(String node) {
        if (graph.getNode(node) != null) {
            graph.removeNode(node);
            return "Node removed : ".concat(node);
        } else {
            return "Error: Node ".concat(node).concat(" is not in graph");
        }
    }

    public String createEdge(String node1, String node2) {
        if (graph.getNode(node1) != null
                && graph.getNode(node2) != null) {
            graph.addEdge(node1.concat(node2), node1, node2);
            return "Created Edge between: ".concat(node1.concat("-").concat(node2));
        }
        return "Error: Invalid Node";
    }

    private String getRandomString(int length) {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(alphabets.charAt(random.nextInt(alphabets.length())));
        }
        return sb.toString();
    }
}
