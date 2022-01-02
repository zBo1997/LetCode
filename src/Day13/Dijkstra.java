package Day13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * D算法 特斯拉算法
 * 前提是，这个图中不能有 负数的边权重，传入一个头节点
 */
public class Dijkstra {


    public static HashMap<Node, Integer> dijkstra1(Node from) {
        //key 为 从开始点到达出的 key
        //value 为 从开始点到这个  key 的最小距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);//放入头节点
        //被锁定的Node 节点
        HashSet<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            Integer minDistance = distanceMap.get(minNode);//拿到到这个点的最小距离
            //枚举所有的边
            for (Edge edge : minNode.edges) {
                //拿到这个边所指向的点
                Node to = edge.to;
                //当前这条边不在最小记录的集合中？
                if (!distanceMap.containsKey(to)){
                    //从某一个节点来到当前节点最小的距离 加上当前这条边的地权重
                    distanceMap.put(to,minDistance + edge.weight);
                } else {
                    //尝试更新来到在to这个节点的最小距离 看看是 之前的距离 和 老的距离 那个一个更小了
                    distanceMap.put(to,Math.min(distanceMap.get(to),minDistance + edge.weight));
                }
            }
            //当前的minNode已经锁定，加入锁定的集合
            selectedNodes.add(minNode);
            //再去寻找是否还有最小的节点
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 获取最没有被解锁的做小节点
     * @param distanceMap
     * @param selectedNodes
     * @return
     */
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;//初始化一个最大边
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                //若当前的节点没有被锁定，并且当前节点的距离是所有节点中最小的点 说明当前节点可用为最小节点
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;

    }


}
