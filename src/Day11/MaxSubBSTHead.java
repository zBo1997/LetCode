package Day11;

/**
 * @Classname MaxSubBSTHead
 * @Description 最大搜索子树的头节点是什么
 * @Date 2021/9/14 22:17
 * @Created by ZhuBo
 */
public class MaxSubBSTHead {

    static class Info {

        public Node maxSubBSTHead;//是否是搜搜二叉树

        public int maxSubBSTSize;//最大子搜索二叉树的大小

        public int min;//最大值

        public int max;//最小值

        public Info(Node maxSubBSTHead, int maxSubBSTSize, int min, int max) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int i) {
            this.value = i;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int min = x.value;
        int max = x.value;
        Node maxBSTSubNode = null;
        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxBSTSubNode = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            maxBSTSubNode = rightInfo.maxSubBSTHead;
            maxSubBSTSize = rightInfo.maxSubBSTSize;
        }
        //如果以下条件都满足 ，那么就说明他一个 和x 节点有关的 最大搜索二叉树的一系列值
        if (leftInfo == null ? true : (leftInfo.maxSubBSTHead == x.left && leftInfo.max < x.value) &&
                rightInfo == null ? true : (rightInfo.maxSubBSTHead == x.right && rightInfo.min > x.value)) {
            maxBSTSubNode = x;//那么这颗树的头节点
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1 ;
        }
        return new Info(maxBSTSubNode,maxSubBSTSize,min,max);
    }

}
