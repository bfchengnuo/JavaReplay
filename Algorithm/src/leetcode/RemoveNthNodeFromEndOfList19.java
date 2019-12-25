package leetcode;

/**
 *  删除链表的倒数第N个节点
 *
 *  https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @author Created by 冰封承諾Andy on 2019/12/24.
 */
public class RemoveNthNodeFromEndOfList19 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        // 1 2 3 4
        print(removeNthFromEnd(listNode, 1));

        listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        // 1 2 3 5
        print(removeNthFromEnd(listNode, 2));

        listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        // 1 2 4 5
        print(removeNthFromEnd(listNode, 3));

        listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        // 1 3 4 5
        print(removeNthFromEnd(listNode, 4));

        listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        // 2 3 4 5
        print(removeNthFromEnd(listNode, 5));
    }

    private static void print(ListNode listNode) {
        ListNode node = listNode;
        String str = String.valueOf(node.val);
        do {
            node = node.next;
            str = str.concat("->").concat(String.valueOf(node.val));
        } while (node.next != null);

        System.out.println(str);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int index = 0;
        ListNode node = head;
        ListNode preNode = head;

        do {
            if (index++ > n) {
                preNode = preNode.next;
            }

            if (node.next == null) {
                if (index > n) {
                    preNode.next = preNode.next == null ? null : preNode.next.next;
                } else {
                    head = head.next;
                }
                return head;
            }
            node = node.next;
        } while (true);
    }



    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
