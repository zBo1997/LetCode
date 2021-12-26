package Day13;


import java.util.ArrayList;

/**
 * 包N*3的矩阵转换成对应的Node结构
 * 图的结构
 */
public class Node {

    public int value;

    //入度
    public int in;

    //出度
    public int out;

    public ArrayList<Node> next; //直接邻居
    public ArrayList<Edge> edges; //边

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.next = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
