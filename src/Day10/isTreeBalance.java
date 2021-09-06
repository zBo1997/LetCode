package Day10;

/**
 * @Classname isTreeBalance
 * @Description 使用的后序遍历
 * 先序（1）先(根)序遍历（根左右）
 *
 * 中序（2）中(根)序遍历（左根右）
 *
 * 后序（3）后(根)序遍历（左右根）
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
        //左边的树是 平衡的 ，右数 是平衡的  并且高度 差值没有超过1
        if (leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) < 2) {
            isBalance = true;
        }
        return new Info(height, isBalance);
    }
}
