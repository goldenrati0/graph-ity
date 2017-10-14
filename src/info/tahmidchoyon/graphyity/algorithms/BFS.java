package info.tahmidchoyon.graphyity.algorithms;

import java.util.*;

public class BFS{

    private Graph graph;
    
    private Queue Q;
    private Node[] node;
    private Vertex source;

    private LinkedList result;

    public Node[] traverse(){
        ArrayList<Node> path = new ArrayList<Node>();
        int num;

        class Find {
            int gePosition(String key){
                for(int i = 0; i < graph.vertexes.size(); i++){
                    if(graph.vertexes.get(i).label == key)
                        return i;
                }
            }
        }

        Q.add(
            node[
               num = (new Find().getPosition(source))
            ]
        );

        Q.peek().changeValues(null, 1, 0);

        while(Q.peek() != null){
            Node current = Q.Dequeue();
            ArrayList<Edge> edges = current.vertex.getNeighbors();

            for(int i = 0, len = edges.size(); i < len; i++){
                Edge visit = edges.get(i);
                if(
                    node[
                        num = (new Find().getPosition(visit.getLabel()))
                    ].color == 0
                )   Q.add(
                        node[num] = node[num].changeValues(current.vertex, 1, current.distance + 1)
                    );
            }
            current.color = -1;
        }
        return node;
    }

    public BFS(){

    }

    public BFS(Graph g, String source){
        pull(g);
        AddSource(source);
        initiate();
        result.add(traverse());
    }

    public BFS(Graph g){
        pull(g);
        for(int i = 0, len = graph.size(); i < len; i++){
            AddSource(graph.vertexes.get(i).label);
            initiate();
            result.add(traverse());
        }
    }

    public Boolean AddSource(String s){
        if((source = graph.getVertex(s)) != null){
            System.out.println("The source not found");
            return false;
        }
        return true;
    }

    private void pull(Graph g){
        graph = g;
    }

    private void initiate(){
        if(graph.vertexes.isEmpty()) throw new IllegalArgumentException();

        Q = new LinkedList<Node>();
        int size = graph.vertexes.size();
        node = new Node[size];

        for(int i = 0; i < size; i++)
            node[i] = new Node(graph.vertexes.get(i), null, 0, -1);
        
    }

    public LinkedList getResult(){
        return result;
    }

    private class Node{
        Vertex vertex;
        Vertex parent;
        int color;
        int distance;
    
        Node(Vertex v, Vertex p, int c, int d){
            vertex = v;
            parent = p;
            color = c;
            distance = d;
        }

        changeValues(Vertex p, int c, int d){
            parent = p;
            color = c;
            distance = d;
        }
    }
}