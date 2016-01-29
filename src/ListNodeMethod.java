
public class ListNodeMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		ListNode head =new ListNode(1);
		ListNode node = head;
		node.next = new ListNode(4);
		node = node.next;
		node.next = new ListNode(3);
		node = node.next;
		node.next = new ListNode(2);
		node = node.next;
		node.next = new ListNode(5);
		node = node.next;
		node.next = new ListNode(2);
		node = node.next;
		node.next = null;
		
		
		sol.partition(head, 3);
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


/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
class Solution {
    /**
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode 
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        // using two new listnode to store each other
        // one is store the value less than x and another is store the value of node more than x
        // corner bounary case
        if(head == null) { return null; }
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode list1 = dummy1;
        ListNode list2 = dummy2;
        /*while(head != null) {
            if(head.val < x) {
                list1.next = new ListNode(head.val);
                list1 = list1.next;
                //list1.next = null;
            } else {
                list2.next = new ListNode(head.val);
                list2 = list2.next;
                //list2.next = null;
            }
            head = head.next;
        }
        if(dummy1.next == null) {
            return dummy2.next;
        }
        if(dummy2.next != null) {
            list1.next = dummy2.next;
        }*/
        while(head != null) {
            if(head.val < x) {
                list1.next = head;
                //list1 = list1.next;
                list1 = head;
                //list1.next = null;
            } else {
                list2.next = head;
                //list2 = list2.next;
                list2 = head;
                //list2.next = null;
            }
            head = head.next;
        }
        list2.next = null;
        list1.next = dummy2.next;
        
        return dummy1.next;
    }
}
