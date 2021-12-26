package Day13;

/**
 * 有向图中的边 数据结构
 */
public class Edge {
    //
    public int weight;

    public Node from;

    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
