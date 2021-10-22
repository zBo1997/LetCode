package Day12;

import java.util.HashMap;
import java.util.List;

/**
 * @Classname Union_Set
 * @Description 并差集 结构学习
 * @Date 2021/10/7 21:47
 * @Created by ZhuBo
 */
public class Union_Set {

    static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    static class UnionSet<V> {
        /**
         * 存放当前节点和包装成Node类型节点
         */
        HashMap<V, Node<V>> nodes;

        /**
         * 每个节点的大小
         */
        HashMap<Node<V>, Integer> nodeSize;

        /**
         * 存放代表点
         */
        HashMap<Node<V>, Node<V>> parentMap;

        /**
         * 构造方法初始化所有得Map表结构
         *
         * @param list 批量传入得集合
         */
        public UnionSet(List<V> list) {
            for (V cur : list) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parentMap.put(node, node);
                nodeSize.put(node, 1);
            }

        }

        /**
         * 从cur 传入得节点开始一直寻找，直到找到代表点返回。
         *
         * @return
         */
        public Node<V> findLastFather(Node<V> cur) {
            return cur;
        }
         
        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) return false;
            return findLastFather(nodes.get(a)) == findLastFather(nodes.get(b));
        }

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
    }

}
