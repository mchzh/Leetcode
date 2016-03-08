
public class ListNodeMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Solution sol = new Solution();
		ReorderList relist =new ReorderList();
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
		ListNode headre =new ListNode(1);
		ListNode nodere = headre;
		nodere.next = new ListNode(-1);
		nodere = node.next;
		nodere.next = null;
		relist.reorderList(headre);*/
		
		// beging queue implement test
		Queue queuetest = new Queue();
		queuetest.enqueue(438);
		queuetest.dequeue();
		queuetest.enqueue(452);
		queuetest.enqueue(379);
		queuetest.enqueue(884);
		queuetest.enqueue(332);
		queuetest.enqueue(55);
		queuetest.dequeue();
		queuetest.enqueue(825);
		queuetest.dequeue();
		queuetest.dequeue();
		queuetest.dequeue();
		queuetest.dequeue();
		queuetest.enqueue(983);
		queuetest.dequeue();
		queuetest.dequeue();
		queuetest.enqueue(616);
		queuetest.enqueue(66);
		String str = "178542";
		int k = 4;
		//String temp1 = str.substring(2,2);
    	//String temp2 = str.substring(1,4);
		DeleteDigits(str, k);
	}
	
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public static String DeleteDigits(String A, int k) {
        // write your code here
        // brute force if DFS to iterator all k element;
        // usr string to chararray store every char and DFS to select char;
        // algrithoem law/rule is if prve ele more than neighbor, the prev will be discarded
        // related to delete the peak element
        if(A.length() <= k) return "";
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < A.length(); j++) {
                if(j == A.length() -1 || A.charAt(j+1) < A.charAt(j)) {
                    A = remove(A, j);
                    break;
                }
            }
        }
        // the rest of ele of A need to delet the front zero
        int i = 0;
        while(i < A.length()-1 && A.charAt(i) == '0') {
            i++;
        }
        return A.substring(i, A.length() - i);
    }
    private static String remove(String str, int pos) {
    	String temp1 = str.substring(0,pos);
    	String temp2 = str.substring(pos+1, str.length() - pos - 1);
        return temp1;
    }
    
	public void sortColors(int[] nums) {
        // write your code here
        // left and right pointer
        int left = 0;
        int right = nums.length - 1;
        int passpos = left;
       
            while(nums[left] == 0 && left < right) {
                left++;
            }
            while(nums[right] == 2 && left < right) {
                right--;
            }
            passpos = left;
            while(passpos <= right) {
                if(nums[passpos] == 0) {
                    swap(nums, left, passpos);
                    
                    left++;
                }
                passpos++;
            }
            while(nums[right] == 2 && left < right) {
                right--;
            }
            passpos = right;
            while(passpos >= left) {
                if(nums[passpos] == 2) {
                    swap(nums, passpos, right);
                    
                    right--;
                }
                passpos--;
            }
       
        
    }
    
    private void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
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

class ReorderList {
    /**
     * @param head: The head of linked list.
     * @return: void
     */
    public void reorderList(ListNode head) {  
        // write your code here
        // find middle
        // reverse the behind part
        // merge the first part and the reversed part
        if(head == null || head.next == null) {
            return;
        }
        
        ListNode middle = fidnMiddleNode(head);
        ListNode righthead = reverseNode(middle.next);
        middle.next = null;
        
        merge(head, righthead);
    }
    private ListNode fidnMiddleNode(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private ListNode reverseNode(ListNode rNode) {
        if(rNode == null || rNode.next == null) {
            return rNode;
        }
        ListNode preNode = null;
        while(rNode != null) {
            ListNode temp = rNode.next;
            rNode.next = preNode;
            preNode = rNode;
            rNode = temp;
        }
        return preNode;
    }
    private void merge(ListNode halfoffirst, ListNode halfofreverse) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        ListNode halfofhead = halfoffirst;
        while(halfofhead != null && halfofreverse != null) {
            tail.next = halfofhead;
            halfofhead = halfofhead.next;
            tail.next.next = halfofreverse;
            //halfofhead = halfofhead.next; // reference problem;
            halfofreverse = halfofreverse.next;
            tail = tail.next.next;
        }
        if(halfofhead != null) {
            tail.next = halfofhead;
        } else {
            tail.next = halfofreverse;
        }
        halfoffirst = dummy.next;
    }
}

class Queue {
    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }
    int size;
     ListNode dummy;
     ListNode tail;
    
    public Queue() {
        // do initialize if necessary
        dummy = new ListNode(0);
        tail = dummy;
        size = 1;
    }

    public void enqueue(int item) {
        // Write your code here
        if(tail != null) {
            tail.next = new ListNode(item);
            size++;
            tail = tail.next;
        }
        
    }

    public int dequeue() {
        // Write your code here
        if( size == 1 ) {
            return 0;
        } else {
            //ListNode head = dummy.next;
            if(dummy.next.next == null) {
                int temp = dummy.next.val;
                dummy.next = null;
                tail = dummy;
                size--;
                return temp;
            }
            ListNode head = dummy.next;
            dummy.next = dummy.next.next;
            size--;
            return head.val;
        }
    }
}

