package Day13;

import java.util.*;

/**
 * 拓扑排序
 */
public class TopologySort {

    /**
     * 可以拓扑排序的前提是 必须 有向无环图 不然不能拓扑排序
     * @param graph
     * @return
     */
    public static List<Node> sortedTopology(Graph graph){
        /**
         * Map存放的结构，因为在遍历图的过程我们要减少每个节点的“入度”
         * key:某个node
         * value :剩余入读
         */
        HashMap<Node,Integer> inMap = new HashMap();
        //准备一个容器，存放入读为 0的 数据 ，就是说存放之前的 Node已经做完了 才可以存入队列
        Queue<Node> inIsZero = new LinkedList();
        //获取图中所有的节点
        for (Node node : graph.nodes.values()) {
            inMap.put(node,node.in);
            if (node.in == 0){
                //发现入读为0
                inIsZero.add(node);
            }
        }
        //拓扑排序 一次放入List中去
        ArrayList results = new ArrayList();
        while (!inIsZero.isEmpty()) {
            //弹出的顺序一定是已经做完的节点 并且 已经 没有入度
            Node cur = inIsZero.poll();
            results.add(cur);//把当前入读为 0 的节点放入到结果集中
            for (Node next : cur.nexts) {
                //把关于此时节点的所有节点“入度” - 1
                inMap.put(next, inMap.get(next) - 1);
                if (next.in == 0 ){
                    inIsZero.add(next);
                }
            }
        }
        return results;
    }
}
