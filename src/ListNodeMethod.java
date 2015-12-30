
public class ListNodeMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode tail = head.next.next;
        ListNode newhead = head.next;
        newhead.next = head;
        head.next = swapPairs(tail);
        return newhead;
        /*if(head == null) return head;
        ListNode tmphead = new ListNode(0);
        tmphead.next = head;
        head = tmphead;
        ListNode node = head.next;
        ListNode prenode = head;
        ListNode aftnode = node.next;
        ListNode conbehind;
        while(node!= null && aftnode!=null) {
            //swap(node,node.next);
            prenode.next = aftnode;
            conbehind = aftnode.next;
            aftnode.next = node;
            node.next = conbehind;
            prenode = node;
            node = conbehind;
            if(node != null) {
                aftnode = node.next;
            }
        }
        
        return head.next;*/
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ListNode {
	     int val;
	    ListNode next;
	    ListNode(int x) { val = x; }
}