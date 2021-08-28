package Day6;

/**
 * @Classname CopyListWithRand
 * @Description 链表Random指针问题 赋值问题
 * @Date 2021/8/28 15:49
 * @Created by ZhuBo
 */
public class CopyListWithRand {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyListWithRand(Node head){
        if(head == null){
            return null;
        }
        Node cur = head;
        Node next = null;//添加 每一个节点的下一个节点节点为 删一个节点的复制节点
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while(cur != null){
            next = cur.next;
            cur.next = new Node(cur.val);//复制节点
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy = null;//复制节点
        while (cur != null){
            next = cur.next.next;//原始节点
            copy = cur.next;//原始节点的复制节点
            //cur 老节点 random指针的下一个 就是copy radmom 应该指向的位置
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;//指向下一“对”节点
        }
        /** 分离链表（新老节点分离） next 方向设置 */
        Node res = head.next;
        cur = head;
        while(cur != null){
            next = cur.next.next;//记录老数据下一个节点
            copy = cur.next;//克隆节点
            cur.next = next;//当前节点下一个不再指向克隆节点
            copy.next = next != null ? next.next : null;//因为老数据的下一个，就是克隆节点的random
            cur = next;//指向下一“对”节点
        }
        return res;

    }
}
