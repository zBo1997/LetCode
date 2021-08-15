package Day1;

/**
 *
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        if(l1 == null && l2 == null){
            return null;
        }
        int addOne = 0 ;// 进位的数字
        ListNode dummyNode = new ListNode(0);//传统做法，设置一个空的节点，（或者我们叫假结点、或者叫头节点就仿佛链表中一开始指向nil那个节点）
        ListNode head = dummyNode;//一开这个
        while(l1 != null || l2 != null || addOne != 0 ){
            //开始计算
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + addOne;
            head.next = new ListNode(sum % 10);//指向下一个值（这是我们已经计算好的）
            head = head.next;//head 指向下一个指针
            addOne = sum / 10;//取出计算需要进位的整数
            //然后l1,l2分别指向第二个节点
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        return dummyNode.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
