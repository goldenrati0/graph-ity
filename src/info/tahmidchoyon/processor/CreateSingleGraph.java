/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.tahmidchoyon.processor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author tahmid
 */
public class CreateSingleGraph {

    private static final long DEFAULT_THREAD_SLEEP_TIME = 250L;

    private String nodeString;
    private Graph graph;

    public CreateSingleGraph(String nodeString) {
        this.nodeString = nodeString;
    }

    public void createGraph() {
        String str = removeDuplicate(toCharacterArray(nodeString));
        graph = new SingleGraph(getRandomString(10));

        graph.display();

        for (int i = 0; i < str.length(); i++) {
            graph.addNode(String.valueOf(str.charAt(i)));
            try {
                Thread.sleep(DEFAULT_THREAD_SLEEP_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(CreateSingleGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                graph.addEdge("" + str.charAt(i) + str.charAt(j), "" + str.charAt(i), "" + str.charAt(j));
                try {
                    Thread.sleep(DEFAULT_THREAD_SLEEP_TIME);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CreateSingleGraph.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private String removeDuplicate(Character[] charArray) {
        Set<Character> charSet = new HashSet<>(Arrays.asList(charArray));
        StringBuilder stringBuilder = new StringBuilder("");
        charSet.forEach((c) -> {
            stringBuilder.append(c);
        });
        return stringBuilder.toString();
    }

    private Character[] toCharacterArray(String str) {
        char[] tempCharArray = str.toCharArray();
        Character[] characters = new Character[tempCharArray.length];
        for (int i = 0; i < tempCharArray.length; i++) {
            characters[i] = (Character) tempCharArray[i];
        }
        return characters;
    }

    public Graph getGraph() {
        graph = new SingleGraph(getRandomString(10));
        graph.display();
        return graph;
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
