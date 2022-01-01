package Day13;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * P算法 求最小生成树
 */
public class Prim {


    /**
     * P算法生成最小生成树
     *
     * @param graph
     * @return
     */
    public static Set<Node> primMST(Graph graph) {
        //解锁的边翻入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        //解锁的点
        HashSet<Node> nodeSet = new HashSet<>();
        //已经进入的边的Set
        HashSet<Edge> edgeSet = new HashSet<>();
        HashSet<Node> result = new HashSet<>();

        /**
         * 这个循环是为防止什么 ？？？？ 好像没有什么必要
         *
         */
        for (Node node : graph.nodes.values()) {
            //初始化第一个解锁的点和边
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);//在Set中初始化第一个点
                for (Edge edge : node.edges) {
                    edgeSet.add(edge);//放入这个点的所有的边
                    priorityQueue.add(edge);//往小根堆里放入已经解锁的边
                }
            }
            //重头戏： 这里开始生成最小生成树
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll();
                //获取这条边的点
                Node toNode = edge.to;
                //如果说目前已经解锁的点中没有这个边所指向的点，往结果集中加入这个点
                if (!nodeSet.contains(toNode)) {
                    nodeSet.add(toNode);
                    result.add(toNode);
                }
                //toNode 下一条边没有被解锁，继续放入小根堆中，继续while循环;并且同时放入没有解锁的边的集合中
                for (Edge nextEdge : toNode.edges) {
                    if (!edgeSet.contains(nextEdge)) {
                        edgeSet.add(edge);
                        priorityQueue.add(nextEdge);
                    }
                }
            }
            break;
        }
        return result;
    }

}
