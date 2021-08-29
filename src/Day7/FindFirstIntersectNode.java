package Day7;

/**
 * @Classname FindFirstIntersectNode
 * 两个链表，可能有环，可能无环；可能相交，
 * 可能不相交；若相交，返回相交的第一个节点，若不相交，返回null。
 * @Description Floyd 判圈算法实现
 * @Date 2021/8/28 16:58
 * @Created by ZhuBo
 */
public class FindFirstIntersectNode {

    /**
     * 结构类型
     */
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        if (loopNode1 != null && loopNode2 != null) {
            return bothLoop(head1, loopNode1, head2, loopNode2);
        } else if (loopNode1 == null && loopNode2 == null) {
            return noLoop(head1, head2);
        } else {
            return null;
        }

    }

    /**
     * 此处的寻找环的起始节点，用的是Floyd判圈算法（Floyd Cycle Detection Algorithm）
     * https://blog.csdn.net/SSHH_ZHU/article/details/119971535?spm=1001.2014.3001.5502
     * 上面的博客详细解释此算法的证明
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        //快慢指针寻找这个边表的环(这里记住,不能从null 开始节点一起出发，不凡无法进入下面的判断)
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            /** 当快指针越过边界 */
            if (fast.next == null || fast.next.next == null) {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        fast = head;//让快指针重新回到头节点,依次往下走 ，当发现内村地址相等的节点，证明找到了环的起始节点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    /**
     * 如果是两个有环的链表，返回第一个相交的节点 ，如果不相交，返回null
     *
     * @param head1 第一个链表的头节点
     * @param loop1 一个环的起始节点
     * @param head2 第二个链表的头节点
     * @param loop2 第二环的起始节点
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        //如果两个环的起始节点的内存地址相同
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur1 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            /** 判断选择 从相对相等的位置的头节点 */
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);//二者长度差值,准备让比较长的链表到相对与另外一个链表向等的位置，一起开始比较
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;//直到找到这个节点
        } else {
            /** 来就是两个环的节点不相同的情况，就说明这个这个链表已经形成一个环了 ，所以我们绕这个这环进行旋转 */
            cur1 = loop2.next;
            while (cur1 != loop2) {
                if (cur1 == loop1) {
                    return loop1;//次是找到换上的一个相交的节点，我们返回loop1 和loop 都可以
                }
                cur1 = cur1.next;
            }
            return null;//否则不相交
        }
    }

    /**
     * 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        // n  :  链表1长度减去链表2长度的值
        cur1 = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
        Math.abs(n);
        while (n != 0) {
            //来到两个链表相同长度的位置
            n--;
            cur1 = cur1.next;
        }
        //开始比较，如果二者相同则找到 两个链表相交的节点为cur1
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

}
