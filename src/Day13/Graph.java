package Day13;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    /**
     * 点集合
     */
    public HashMap<Integer,Node> nodes;

    /**
     * 边集合
     */
    public HashSet<Edge> edges;


    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
