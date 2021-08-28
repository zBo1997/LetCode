package Day6;

import java.util.Stack;

/**
 * @Classname LinkedListMid
 * @Description 链表问题 找链表中找中间的位置
 * @Date 2021/8/28 0:02
 * @Created by ZhuBo
 */
public class LinkedListMid {

    /**
     * Node的结构类型
     */
    public static class Node {
        public int value;

        public Node next;

        public Node(int v) {
            value = v;
        }

    }

    /**
     * 快慢指针 （偶数返回上中节点）
     *
     * @param head
     * @return
     */
    public static Node midOrUpMidNode(Node head) {
        //判断当前节点是
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        /** 快指针边界 */
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 快慢指针 （偶数返回下中节点）
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;//快指针起始节点不一样了
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 链表回文数 O(M)空间复杂度
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 减少一般空间负载度和 回文数字检验
     * @param head
     * @return
     */
    public static boolean isPalindrome2(Node head) {
        if(head == null || head.next ==null){
            return true;
        }
        Node right = head.next;
        Node cur = head;
        //找到中点
        while(cur.next != null && cur.next.next != null){
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while(right != null){
            stack.push(right);
            right = right.next;
        }
        while(!stack.isEmpty()){
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 链表不用栈解决回文数问题O(1) 空间复杂度
     * @param head
     * @return
     */
    public static boolean isPalindrome3(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1 = head;//记录头节点
        Node n2 = head;//记录第二个头节点
        while(n1.next != null && n2.next.next != null){
            //两个相当于快慢指针同时移动 （最终n1来到中点位置）
            n1 = n1.next; // n1 -> mid
            n2 = n2.next.next; // n2 -> end
        }
        n2 = n1.next;//右半部分第一个节点
        n1.next = null;//中间部分指向空
        Node n3 = null;
        //开始逆序
        while(n2 != null){
            n3 = n2.next;//每次递归保存第一个节点的后一个节点
            n2.next = n1;//指向反转
            n1 = n2;//n1 指向下一个节点
            n2 = n3;//n2 指向下一个节点
        }
        /**逆序完毕，开始 进行回文检查*/
        n3 = n1;//保存最后一个节点
        n2 = head;//头节点
        //n2 n1 同时向 重点处罚
        boolean res = true;
        while(n2 != null && n1 != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }
            //n1 n2 同时转向下一个节点
            n1 = n1.next;
            n2 = n2.next;
        }
        /** 问检查完毕，需要恢复列表的初始指针 */
        n1 = n3.next;//回文检查之前的最后一个节点
        n3.next = null;//使得这个节点指向空
        /** n1开始恢复列表 */
        while(n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;//n3 指向下一个节点
            n1 = n2;//n1 指向下一个节点
        }
        return res;
    }

    public static boolean isPalindrome4(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) { // find mid node
            n1 = n1.next; // n1 -> mid
            n2 = n2.next.next; // n2 -> end
        }
        // n1 中点
        n2 = n1.next; // n2 -> right part first node
        n1.next = null; // mid.next -> null
        Node n3 = null;
        while (n2 != null) { // right part convert
            n3 = n2.next; // n3 -> save next node
            n2.next = n1; // next of right node convert
            n1 = n2; // n1 move
            n2 = n3; // n2 move
        }
        n3 = n1; // n3 -> save last node
        n2 = head;// n2 -> left first node
        boolean res = true;
        while (n1 != null && n2 != null) { // check palindrome
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next; // left to mid
            n2 = n2.next; // right to mid
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // recover list
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(2).next = new Node(3)
        .next = new Node(3).next = new Node(2).next = new Node(2);
        boolean palindrome1 = LinkedListMid.isPalindrome4(node);
        System.out.println(palindrome1);
    }
}
