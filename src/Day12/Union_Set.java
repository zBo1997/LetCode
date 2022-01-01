package Day12;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 有若干样本a、b、c、d ....类型 假设都是V 把他认为是一个单列表结构
 * 在 并查集中 一开始认为每个样本都在单独的集合中，
 * 用户 可以在任何时候调用如下  boolean isSameSet(V v ,Y y); 这个方法查看 是否属于统一样本
 * void union() ; 把 所载集合中的 素有样本合并成一个集合
 * isSameSet 和 union 方法的时间和空间复杂的月底越好
 *
 * @Classname Union_Set
 * @Description 并查集 结构学习
 * @Date 2021/10/7 21:47
 * @Created by ZhuBo
 */
public class Union_Set {
    /**
     * 结构
     *
     * @param <V>
     */
    static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        /**
         * 存放当前节点和包装成Node类型节点
         */
        HashMap<V, Node<V>> nodes;

        /**
         * 每个节点的大小
         * 只有一个点，是这个集合的代表点，才会有记录
         * 什么叫“代表点”
         *     a
         *  /  |  \
         * b   c   e
         * 表示 素有4 条记录
         * |Key |Value|
         * | a  |  4  |
         */
        HashMap<Node<V>, Integer> nodeSize;

        /**
         * 存放代表点 如下
         *     a
         *  /  |  \
         * b   c   e
         * 所以这个HashMap的存放结构如下把他扁平化，
         * |Key |Value|
         * | a  |  a  |
         * | b  |  a  |
         * | c  |  a  |
         * | e  |  a  |
         */
        HashMap<Node<V>, Node<V>> parentMap;

        /**
         * 构造方法初始化所有得Map表结构，初始化所有的样本
         * 两个 a 怎么办
         *
         * @param list 批量传入得集合
         */
        public UnionSet(List<V> list) {
            //初始化时候，每一个节点的样本是他自己
            for (V cur : list) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parentMap.put(node, node);
                nodeSize.put(node, 1);
            }

        }


        /**
         * 从cur 传入得节点开始一直寻找，直到找到代表点返回。
         * 第一个优化:扁平化过程
         * @return
         */
        public Node<V> findLastFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            //寻找当前点的“代表点” ，用于修改“parentMap” 进行 修改
            while (cur != parentMap.get(cur)) {
                path.push(cur);//沿途的所有节点 ，全部记下来 后续扁平化要用
                cur = parentMap.get(cur);
            }
            //当上面的while 走完以后 ，一定到当前点的代表点
            //沿途的父节点 ，全部改成当前"代表点"
            while (!path.isEmpty()) {
                parentMap.put(path.pop(), cur);
            }
            return cur;
        }

        /**
         *
         * @param a
         * @param b
         * @return
         */
        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) return false;
            //如果内存地址 说明是一个 一个 点
            return findLastFather(nodes.get(a)) == findLastFather(nodes.get(b));
        }

        /**
         * 合并
         * 小的链子 去挂大的链子，小去挂大，减少便利链的高度
         * @param a
         * @param b
         */
        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) return;
            Node<V> fatherA = findLastFather(nodes.get(a));//A节点得头节点
            Node<V> fatherB = findLastFather(nodes.get(b));//B节点得头节点
            if (fatherB != fatherA) {//如果二者得不一样 ，也就是说二者得最终父节点不是同一个，那么 可以实现合并
                /**
                 * 查看当前节点得SetSize
                 */
                Integer aSetSize = nodeSize.get(a);
                Integer bSetSize = nodeSize.get(b);
                //判断 二者的集合大小
                if (aSetSize >= bSetSize) {
                    parentMap.put(fatherB, fatherA);//如果b得大小比a的小，直接把b得头换成a，与a相连接
                    nodeSize.put(fatherA, aSetSize + bSetSize);//换头后要记得换掉
                    parentMap.remove(fatherB);//记得要清除之前得B父节点记录
                } else {
                    //否则相反
                    parentMap.put(fatherA, fatherB);
                    nodeSize.put(fatherB, aSetSize + bSetSize);
                    parentMap.remove(fatherA);
                }

            }

        }

        /**
         * 返回并查集的数量
         * @return
         */
        public int getSizeNum(){
            return nodeSize.size();
        }
    }

}
