/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tahmidchoyon.processor;

import java.util.Random;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author tahmid
 */
public class CreateSingleGraph {

    private static final long DEFAULT_THREAD_SLEEP_TIME = 5L; //qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789`~!@#$%^&*()_+-={}|[]\:";'<>?,./

    private Graph graph;
    private int nodeNumber;

    public CreateSingleGraph() {
        this.graph = new SingleGraph(getRandomString(10));
    }

    /**
     * Removes the last added node from graph
     */
    public void removeNode() {
        graph.removeNode(String.valueOf(--nodeNumber));
        System.out.println("Node Removed -> " + nodeNumber);
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

    public int getNodeNumber() {
        return this.nodeNumber;
    }
}
