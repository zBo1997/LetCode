package Day10;

/**
 * @Classname isTreeBalance
 * @Description 按照整棵树的最大距离
 * 这到题的根本是
 * 1、整棵树的最长长度和这个所谓的X节点无关！！ 也就是说，最大长距离不经过这个节点
 * 2、整棵树的最长长度和这个所谓的X节点有关！！ 也就是是，整棵树的最长度经过此节点
 * <p>
 * 先序（1）先(根)序遍历（根左右）
 * <p>
 * 中序（2）中(根)序遍历（左根右）
 * <p>
 * 后序（3）后(根)序遍历（左右根）
 * @Date 2021/9/6 22:45
 * @Created by ZhuBo
 */
public class MaxDistance {

    static class Info {
        int height;//当前这棵树的高度
        int maxDistance;//当前树的最大距离

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int i) {

        }
    }

    /**
     * 递归计算方法
     * @param x
     * @return
     */
    public static Info process(Node x) {
        if (x == null) {//尾部节点
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;//当前节点的高度
                                            /**       左树上的最大距离     右数上的最大距离   如果有关，就是左树的高度 +  右树的高度 + 自己这个节点   ***/
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + rightInfo.height + 1);

        return new Info(height, maxDistance);
    }

    /**
     * 主函数调用，只关注节点的最后
     * @param head
     * @return
     */
    public static int maxDistance(Node head){
        return process(head).maxDistance;
    }


    /**
     * 随机生成一棵树
     * @param level
     * @param maxLevel
     * @param maxValue
     * @return
     */
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }
}
