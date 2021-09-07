package Day10;

/**
 * @Classname MaxSubBST
 * @Description 最大搜索二叉子树问题
 * 根本上是还是 是否与所谓的head节点是否有关
 *
 * @Date 2021/9/8 0:10
 * @Created by ZhuBo
 */
public class MaxSubBST {

    static class Info {

        public boolean isAllBST;//是否是搜搜二叉树

        public int maxSubBSTSize;//最大子搜索二叉树的大小

        public int min;//最大值

        public int max;//最小值

        public Info(boolean isAllBST, int maxSubBSTSize, int min, int max) {
            this.isAllBST = isAllBST;
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

        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        /*开始加工信息*/
        int min = x.value;
        int max = x.value;
        /** 如果含有左树，当前节点参与全局最大值 和最小值判断 */
        if(leftInfo != null){
            max = Math.max(rightInfo.max,max);
            min = Math.min(rightInfo.min,min);
        }
        /** 如果含有右树，当前节点参与全局最大值 和最小值判断 */
        if(rightInfo != null){
            max = Math.max(min,max);
            min = Math.min(min,max);
        }
        /** 左树的答案先成为我的答案 ，也就是先考虑左树 ，在考虑右数 */
        int maxSubBSTSize = 0;
        if(leftInfo != null){
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if(rightInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize,rightInfo.maxSubBSTSize);
        }


        boolean isAllBST = false;

        /**
         * 1、如果左树信息为空，那么就为搜索二叉树，否则就去用这个节点的左树的信息
         * 2、如果右树信息为空，那么就为搜索二叉树，否则就去用这个节点的右树的信息
         * 3、如果左树信息为空，那么为搜索二叉树，就不需要判断当前左树的最大值大小
         * 4、如果右树信息为空，那么为搜索二叉树，就不需要判断当前右树的最小值大小
         *
         * */
        if((leftInfo == null ? true : leftInfo.isAllBST)
                && (rightInfo == null ? true : rightInfo.isAllBST)
                && (leftInfo == null ? true : leftInfo.max < x.value)
                && (rightInfo == null ? true : rightInfo.min > x.value)){
            maxSubBSTSize =
					(leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
					+
					(rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
					+
					1;
          isAllBST = true;
        }

        return new Info(isAllBST, maxSubBSTSize, min, max);
    }
}
