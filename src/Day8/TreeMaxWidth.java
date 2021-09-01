package Day8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Classname TreeMaxWidth
 * @Description 寻找二叉树的最宽宽度
 * @Date 2021/8/30 21:21
 * @Created by ZhuBo
 */
public class TreeMaxWidth {

    public static class Node{
        public int value;

        public Node left;

        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 使用map维护每一层的等级
     * @param head
     * @return
     */
    public static int maxWidthUseMap(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> nodes = new LinkedList<>();//设置队列准备层序遍历
        Map<Node,Integer> levelMap = new HashMap<>();//设置一个 哈希表来维护 每一层的宽度(Key:当前的节点，value：当前节点层数)
        int curNodeLevel = 0;//设置当前所在层宽度（这里设置为0的时候，是因为，我们许要在每一次出队列时候进行统计当前层节点数）
        int curLevel = 1;//设置当前所在的层数
        int max = 0;//全局的宽度
        /** 初始化队列和集合，从Root（根节点）开始 */
        nodes.add(head);
        levelMap.put(head,1);
        //开始出队列
        while(!nodes.isEmpty()){
            Node poll = nodes.poll();
            Integer curNodeLevels = levelMap.get(poll);//当前出队列所在节点的层数
            if(poll.left != null){
                levelMap.put(poll.left,curNodeLevels + 1);
                nodes.add(poll.left);//当前节点入队列，准备统计
            }
            if(poll.right != null){
                levelMap.put(poll.right,curNodeLevels + 1);
                nodes.add(poll.right);//当前节点入队列，准备统计
            }
            if(curNodeLevels == curLevel){
                curNodeLevel++;//如果当前出队列的节点的的所在层数，等于当前正在统计的层数，那么说明可以增加当前所在层的宽度
            } else {
                max = Math.max(max,curNodeLevel);//计算上一次最大的宽度赋值给全局最长宽度
                curLevel++;//开始统计下一层，层数++
                curNodeLevel = 1;//当前层数初始化，开始从 “1” 重新统计
            }
        }
        /** 这里注意有个小问题 ，
         * 因为每次更新max 时机是“当你有最新的的数据可以从队列中取出的时候才会更新max，
         * 所以我们要在最后也做一次取最大值，
         * 保证，始终是最大宽度”  */
        max = Math.max(max,curNodeLevel);
        return max;
    }

    public static int maxWidthNotUseMap(Node head){
        if(head == null ){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);//从头结点还是
        Node curEnd = head;//当前层的最右节点 ，最右节点从头节点开始
        Node nextEnd = null;//每一层的最大宽度，的最后一个节点
        int curLevelNodes = 0;//当前从层的最大宽度
        int max = 0;//全局最大宽度
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            /** 使得nextEnd，永远指向下一层 */
            if(cur.left != null ){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;//如果没到每个节点的最右节点,那么就++
            if(cur == curEnd){
                max = Math.max(max,curLevelNodes);//判断当前的节点数的curLevelNodes和全局变量比较，是否是最大的
                curLevelNodes = 0;//当前从最大节点数重新计算
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
