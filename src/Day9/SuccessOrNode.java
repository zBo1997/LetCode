package Day9;


/**
 * @Classname SuccessOrNode
 * @Description
 * @Date 2021/9/2 23:01
 * @Created by ZhuBo
 */
public class SuccessOrNode {

    public static class Node {
        public int value;

        public Node left;

        public Node right;

        public Node parent;//父节点

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * （中序遍历的前提下） 指定节点node 寻找次二叉数的 后续节点 时间复杂度位 O(K) K位node的深度
     * @param node
     * @return
     */
    public static Node getSuccessNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMost(node);
        } else {//当前节点没有右子树（就说明 我们寻找的后继节点 需要找父节点）
            Node parent = node.parent;//
            //当前节点有父节点，并且当前节点不是父节点的左孩子
            while (parent != null && parent.left != node) {
                node = parent;//当前节点上移动
                parent = node.parent;//当前节点的父节点
            }
            return parent;
        }
    }

    /**
     * 始终遍历到最左节点
     * @param node
     * @return
     */
    private static Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
