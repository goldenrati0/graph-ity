package info.tahmidchoyon.graphyity.processor;

import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.Random;

public class MultiGraphCreator {

    private static Graph graph;

    public MultiGraphCreator() {
        graph = new MultiGraph(getRandomString(12));
        graph.addAttribute("ui.stylesheet", "url(info/tahmidchoyon/graphyity/styles/style.css)");
        graph.addAttribute("ui.antialias");
        graph.display();
    }

    public String addNode(String node) {
        if (graph.getNode(node) == null) {
            graph.addNode(node).addAttribute("ui.label", node);
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

    public String removeAllNodes() {
        while (graph.getNodeCount() > 0) {
            graph.getNodeSet().forEach(node -> {
                removeNode(node.getId());
            });
        }
        return "All nodes removed";
    }

    public String createEdge(String node1, String node2) {
        if (graph.getNode(node1) != null
                && graph.getNode(node2) != null) {
            if (graph.getEdge(node1.concat(node2)) != null) {
                return "Existing edge";
            }
            graph.addEdge(node1.concat(node2), node1, node2);
            return "Created Edge between: ".concat(node1.concat("-").concat(node2));
        }
        return "Error: Invalid Node";
    }

    public String deleteEdge(String edge) {
        if (graph.getEdge(edge) != null) {
            graph.removeEdge(edge);
            return edge.concat(" Edge deleted");
        } else {
            return "Error: Invalid Edge";
        }
    }

    public void starterPack() {
        String nodes[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (String n : nodes) {
            addNode(n);
        }

        for (String n : nodes) {
            graph.getNodeSet().forEach((node) -> {
                if (!node.getId().equals(n)) {
                    graph.addEdge("" + node.getId() + n + getRandomString(15), node, graph.getNode(n));
                }
            });
        }
    }

    public void randomStarterPack() {
        String nodes[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        Random random = new Random();
        for (String n : nodes) {
            addNode(n);
            for (int i = 0; i < graph.getNodeCount(); i++) {
                int tempRandom = random.nextInt(graph.getNodeCount());
                try {
                    graph.addEdge(graph.getNode(tempRandom).getId().concat(n), graph.getNode(tempRandom).getId(), graph.getNode(n).getId());
                } catch (IdAlreadyInUseException ex) {
                    continue;
                }
            }
        }

    }

    public void randomSix() {
        String nodes[] = {"a", "b", "c", "d", "e", "f"};
        for (String n : nodes) {
            addNode(n);
        }
        for (int i = 1; i < nodes.length; i++) {
            createEdge("a", nodes[i]);
        }
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
