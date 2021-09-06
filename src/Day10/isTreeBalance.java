package Day10;

/**
 * @Classname isTreeBalance
 * @Description 使用的后序遍历
 * @Date 2021/9/6 22:45
 * @Created by ZhuBo
 */
public class isTreeBalance {

    static class Info {
        int height;//当前这棵树的高度
        boolean isBalance;//当前这棵树是否平衡

        public Info(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;
    }

    public static Info process(Node x) {
        if (x == null) {//尾部节点
            return new Info(0, true);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;//为社么要+1 因为 我们需要加上头节点的高度

        boolean isBalance = false;
        if (leftInfo.isBalance && rightInfo.isBalance && height < 2) {
            isBalance = true;
        }
        return new Info(height, isBalance);
    }
}
