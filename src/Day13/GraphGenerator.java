package Day13;

/**
 * 生成图
 */
public class GraphGenerator {


    /**
     * 这是一个N*3的矩阵
     * [weight,form,to]
     *
     * @param matrix
     * @return
     */
    public static Graph generatorGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        //matrix[0][0] matrix[0][1] matrix[0][2] 第一个位置是 权重 第二个为止是 from 第三个为止是to
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            //如果说这图上没有这个节点
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            //to所对应地点
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            //初始化一条边
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.next.add(toNode);//直接邻居添加toNode
            fromNode.out++;//from的出度++
            toNode.in++;//to的入度++
            fromNode.edges.add(edge);//给当前的from节点天加“边”
            graph.edges.add(edge);//添加所有的边
        }
        return graph;
    }

}
