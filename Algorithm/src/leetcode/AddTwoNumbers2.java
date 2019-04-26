package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/3/2.
 * <p>
 * 两数相加
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class AddTwoNumbers2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode temp = null;
        boolean flag = false;
        int one;
        int two;
        int fin;
        while (l1 != null || l2 != null) {
            one = l1 != null ? l1.val : 0;
            two = l2 != null ? l2.val : 0;
            fin = ((flag ? 1 : 0) + (one + two));
            if (result == null) {
                result = new ListNode((one + two) % 10);
                temp = result;
            } else {
                temp.next = new ListNode(fin % 10);
                temp = temp.next;
            }

            flag = fin >= 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (flag) {
            temp.next = new ListNode(1);
        }
        return result;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
