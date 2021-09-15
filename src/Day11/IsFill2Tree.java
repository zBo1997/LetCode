package Day11;

/**
 * @Classname IsFill2Tree
 * @Description 是否是一棵满搜索二叉子树 , 这里注意 一个棵 搜素二叉字数满足的条件是
 *          如果 数的高度为 L ，节点数为 N 那么这棵树满二叉树 2的l次方 -1 = N
 * @Date 2021/9/14 21:32
 * @Created by ZhuBo
 */
public class IsFill2Tree {

    /**
     * Info信息
     */
    public static class Info{
        public int height;

        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    /**
     * Info信息
     */
    public static class Node{
        public Node right ;

        public Node left;

        public int value;

        public Node(Node right, Node left, int value) {
            this.right = right;
            this.left = left;
            this.value = value;
        }
    }

    /**
     *
     * @return
     */
    public static Info process(Node head){
        if(head == null){
            return new Info(0,0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int maxHeight = Math.max(leftInfo.height, rightInfo.height) + 1;//取出最大值，左右两个棵树的最大高度
        int maxNodes = leftInfo.nodes + rightInfo.nodes + 1;//取出最大的节点数
        return new Info(maxHeight,maxNodes);

    }

    /**
     * 主函数调用逻辑为 看看是否满足
     * @param head
     * @return
     */
    public static boolean isFull(Node head){
        Info process = process(head);

        //左移 相当于乘以 2 的 n 次方
        //右移 相当与除以 2 的 n 此方
        //此处计算的是  2 高度次方 - 1 = 节点数 ，那么这棵树 肯定是 满二叉树
        return (1 << process.height) - 1 == process.nodes;
    }

    public static void main(String[] args) {
        System.out.println(16 << 4);
    }
}
