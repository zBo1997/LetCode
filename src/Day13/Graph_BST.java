package Day13;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 图的宽度优先遍历
 */
public class Graph_BST {


    /**
     * 宽度优先遍历
     *
     * @param node
     */
    public static void graphBstByWidth(Node node) {
        if (node == null) {
            return;
        }
        //准备队列
        Queue<Node> queue = new LinkedList();
        //为了解决图中 闭环的问题
        HashSet<Node> nodeSet = new HashSet<>();
        nodeSet.add(node);
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            System.out.println(curNode.value);//输出当前的节点
            for (Node nextNode : curNode.next) {
                //为了防止出现环，只存放set中没有的节点 ，而下一次队列 只会弹出 没有直接邻居的节点
                if (!nodeSet.contains(nextNode)) {
                    queue.add(nextNode);
                    nodeSet.add(nextNode);//继续放如到set 保证此节已经被使用过
                }
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param node
     */
    public static void dfsBstByDeep(Node node) {
        if (node == null) {
            return;
        }
        /**
         * 这个栈其实是你“图”深度遍历的路径
         */
        Stack<Node> nodeStack = new Stack<>();
        //为了解决图中 闭环的问题
        HashSet<Node> nodeSet = new HashSet<>();
        nodeStack.add(node);
        nodeSet.add(node);
        System.out.println(node);
        if (!nodeStack.isEmpty()) {
            Node cur = nodeStack.pop();
            for (Node nextNode : cur.next) {
                if (!nodeSet.contains(nextNode)) {
                    nodeStack.push(cur);//再次放到栈内 给下次使用
                    nodeStack.push(nextNode);//再放入当前节点的后代
                    nodeSet.add(nextNode);//继续放如到set 保证此节已经被使用过
                    System.out.println(nextNode);
                    break;
                }
            }
        }
    }
}