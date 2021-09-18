package Day11;

import java.util.LinkedList;

/**
 * @Classname IS_BST
 * @Description 使用宽度优先遍历，检查是否是完全二叉树
 * @Date 2021/9/17 22:40
 * @Created by ZhuBo
 */
public class IS_BST {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int i) {
            this.value = i;
        }
    }

    /**
     * 队列宽度遍历解决方式
     *
     * @param head
     * @return
     */
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList();
        boolean isLeaf = true;//标识是否是叶子节点
        Node l = head.left;
        Node r = head.right;
        //先把头节点防区队列中去
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((isLeaf && !(l == null && r == null) || (l == null & r != null))) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * 对于每一颗树而言，我们需要 3 类信息
     * 1、是否是满二叉树（左右子树的高度相当的树）
     * 2、是否是完全二叉树（从左树到右树依次变满的 树就是 完全二叉树）
     * 3、这棵树的高度
     */
    static class Info {
        boolean isFull;
        boolean isCBT;
        int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    /**
     * 动态规划方式 二叉树 递归套路解决办法
     *
     * @param x
     * @return
     */
    public static Info isCBT2(Node x) {
        if (x == null) {
            new Info(true, true, 0);
        }
        Info leftInfo = isCBT2(x.left);
        Info rightInfo = isCBT2(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = rightInfo.isFull && leftInfo.isFull && leftInfo.height == rightInfo.height;//那就是一颗满二叉树
        boolean isCBT = false;
        if (isFull) {//如果它是一颗满二叉树，特也绝对是以可完全二叉树
            isCBT = true;
        } else {
            if (leftInfo.isCBT && rightInfo.isCBT){
                /** 左树的是一颗完全二叉树，并且右树是满二叉树 ，并且 左树的高度 要比右树的高度 大一层 */
                if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
                    isCBT = true;
                }
                /** 左树的是一颗满二叉树，并且右树是完全 ，并且 左树的高度 要比右树的高度 大一层 */
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
                    isCBT = true;
                }
                /** 左树的是一满二叉树，并且右树是完全二叉树 ，并且 左树的高度等于右树的高度 */
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
                    isCBT = true;
                }
            }
        }
        return new Info(isFull,isCBT,height);
    }
}
