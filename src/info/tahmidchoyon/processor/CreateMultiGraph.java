/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tahmidchoyon.processor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

/**
 *
 * @author tahmid
 */
public class CreateMultiGraph {

    private static final long DEFAULT_THREAD_SLEEP_TIME = 250L;

    private int nodeNumber;
    private Graph graph;

    /**
     * Constructor with no parameter. Create new MultiGraph object Adds some
     * nodes and creates edges among them
     */
    public CreateMultiGraph() {
        this.graph = new MultiGraph(getRandomString(10));
        graph.display();
        nodeNumber = 0;
        addInitialNodes();
    }

    /**
     * Adds a new node to the graph
     */
    public void addNode() {
        this.graph.addNode(String.valueOf(nodeNumber));
        System.out.println(String.valueOf("Node Added -> " + nodeNumber));
        graph.getNodeSet().forEach((node) -> {
            if (!node.getId().equals(String.valueOf(nodeNumber))) {
                graph.addEdge("" + node.getId() + nodeNumber + getRandomString(25), node, graph.getNode(String.valueOf(nodeNumber)));
            }
        });
        nodeNumber++;
    }

    /**
     * Removes the last added node from graph
     */
    public void removeNode() {
        graph.removeNode(String.valueOf(--nodeNumber));
        System.out.println("Node Removed -> " + (nodeNumber));
    }

    private void addInitialNodes() {
        for (; nodeNumber < 10; nodeNumber++) {
            this.graph.addNode(String.valueOf(nodeNumber));
            System.out.println(String.valueOf("Node Added -> " + nodeNumber));
            try {
                Thread.sleep(DEFAULT_THREAD_SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(CreateMultiGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
            graph.getNodeSet().forEach((node) -> {
                if (!node.getId().equals(String.valueOf(nodeNumber))) {
                    graph.addEdge("" + node.getId() + nodeNumber + getRandomString(15), node, graph.getNode(String.valueOf(nodeNumber)));
                }
            });
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

    /**
     *
     * @return The current value of nodeNumber
     */
    public int getNodeNumber() {
        return this.nodeNumber;
    }

}
