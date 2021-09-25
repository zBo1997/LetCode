package Day11;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Classname COMMON_PARENT
 * @Description 二叉树的公共祖先
 * @Date 2021/9/25 22:40
 * @Created by ZhuBo
 */
public class COMMON_PARENT {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int i) {
            this.value = i;
        }
    }

    /**
     * 求出此问题的相关学习
     */
    static class Info {
        Node ans ;//两个点在这里
        boolean findO1;//当前子树是否找到o1节点
        boolean findO2;//当前子树是否找了o2节点

        public Info(Node ans, boolean findO1, boolean findO2) {
            this.ans = ans;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }

    /**
     *
     * @param x 以当前二叉树某一棵树的头节点
     * @param o1 节点o1
     * @param o2 节点o2
     * @return
     */
    public static Info process(Node x ,Node o1 ,Node o2){
        Info leftInfo = process(x.left,o1,o2);
        Info rightInfo = process(x.right,o1,o2);

        boolean finOd1 = x == o1 || leftInfo.findO1 || rightInfo.findO1;
        boolean finOd2 = x == o2 || leftInfo.findO2 || rightInfo.findO2;

        Node ans = null;
        /** 无论做视还是右树，出现了共共节，那么肯定就是这个树的ans  ,若 没有公共节点，ans 始终为 null */
        if(leftInfo.ans != null){
            ans = leftInfo.ans;
        }
        if(rightInfo.ans != null){
            ans = rightInfo.ans;
        }

        /** 判断这两个点是否有共同的节点 */
        if (ans == null){
            /** 发现了o1 和 o2 那么说明这个X 就是 这两个节点的公共节点 */
            if (finOd1 && finOd2){
                ans = x;
            }
        }

        return new Info(ans,finOd1,finOd2);
    }































    /**
     *
     * 使用一个去重的HashSet一直判断到出现包 包含这个这个节点的父节点 因为在这个Set中如果存在这么一个节点，
     * 那么肯定是这两个节点的公共父节点
     * @return
     */
    public static Node lowestAncestor1(Node head ,Node o1,Node o2){
        if (head == null){
            return null;
        }
        // key的父节点是value
        HashMap<Node,Node> parentMap = new HashMap<>();
        parentMap.put(head,null);//头节点的父节点是空
        fillParentMap(head,parentMap);
        HashSet<Node> nodeSet = new HashSet<>();
        Node cur = o1;//初始化一个节点
        nodeSet.add(cur);
        while (parentMap.get(cur) != null ){
            cur = parentMap.get(cur);
            nodeSet.add(cur);
        }
        cur = o2;
        while(nodeSet.contains(cur)){
            cur = parentMap.get(cur);
        }
        return cur;
    }

    /**
     * 初始化 父节点Hash表 key是当前这个节点，value是这个key的父节点
     * @param head
     * @param parentMap
     */
    private static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        //添加左树
        if(head.left != null){
            parentMap.put(head.left,head);
            fillParentMap(head.left,parentMap);
        }
        //添加右树
        if(head.right != null){
            parentMap.put(head.right,head);
            fillParentMap(head.right,parentMap);
        }
    }

}
