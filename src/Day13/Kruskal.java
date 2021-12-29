package Day13;

import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

/**
 * K算法 在图中寻找最小生成树
 * 使用并查集的方法
 * 思路：
 * 1、先把所有的所有的节点按边的权重大小进行排序 （使用堆，小根堆）
 * 2、使用小根堆依次弹出 ，并且获取到对应的边的权重大小3
 * 3、然后使用并查集，抽取边权重最小的 并集操作，这样
 */
public class Kruskal {

    /************************首先实现个一个并查集************************/

    //扁平化后的map集合
    //key 为当前节点 value为 上一个节点 ！！！！！！！！ 这里注意
    HashMap<Node, Node> fatherMap;

    //key 为当前节点 ，value为当前节点的
    HashMap<Node, Integer> sizeMap;

    /**
     * 初始化
     */
    public Kruskal() {
        this.fatherMap = new HashMap<>();
        this.sizeMap = new HashMap<>();
    }

    /**
     * 初始化
     */
    public void initSet(Collection<Node> nodes) {
        fatherMap.clear();
        sizeMap.clear();
        for (Node node : nodes) {
            fatherMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    /**
     * 是否相同的 两个集
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSameSet(Node a, Node b) {
        if (!fatherMap.containsKey(a) || !fatherMap.containsKey(b)) return false;
        //如果内存地址 说明是一个 一个 点
        return findLastFather(fatherMap.get(a)) == findLastFather(fatherMap.get(b));
    }

    /**
     * 类似于并查集的finLastFather 并且进行一次后会有 扁平化操作
     *
     * @param cur
     * @return
     */
    public Node findLastFather(Node cur) {
        Stack<Node> nodeStack = new Stack<>();
        cur = fatherMap.get(cur);
        while (cur != fatherMap.get(cur)) {
            nodeStack.add(cur);
            cur = fatherMap.get(cur);
        }
        while (!nodeStack.isEmpty()) {
            fatherMap.put(nodeStack.pop(), cur);
        }
        return cur;
    }

    /**
     * 合并方法
     *
     * @param nodeA
     * @param nodeB
     */
    public void union(Node nodeA, Node nodeB) {
        if (nodeA == null || nodeB == null) return;
        //注意这里和取并集不一样的地方 ==>
        Node lastFatherA = findLastFather(nodeA);
        Node lastFatherB = findLastFather(nodeA);
        if (lastFatherA != lastFatherB) {
            Integer aSize = sizeMap.get(lastFatherA);//当前a的入度
            Integer bSize = sizeMap.get(lastFatherB);//当前b的入度
            if (aSize <= bSize) {
                fatherMap.put(lastFatherA, lastFatherB);
                sizeMap.put(lastFatherB, aSize + bSize);
                fatherMap.remove(lastFatherA);
            } else {
                fatherMap.put(lastFatherB, lastFatherA);
                sizeMap.put(lastFatherA, aSize + bSize);
                fatherMap.remove(lastFatherB);
            }
        }
    }
}
