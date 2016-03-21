
public class IsPalindrome {
	
	// for number
	public boolean isPalindromeNumber(int x) {
		if(x < 0) return false;
		// fist get all digits of this number
		int digitsten = 1;
		int digits = (int)(Math.log(x) / Math.log(10)) + 1;
		while( (x/10) != 0 ) {
			digitsten++;
			x /= 10;
		}
		// loop the half of this digits get the pre-part and post-part
		int num = 0;
		for(int i = 1; i <= digits/2; i++) {
			num = num*10 + x%10; // this procedure reverse the post-part
			x /= 10;
		}
		// reverse post-part to judge whether equal between pre-part and post-part
		// special deal digits is odd
		if(digits % 2 == 1) {
			//x /= 10;  // x the last digit dicard or num add this digits number
			num = num * 10 + x%10;
		}
		return num == x;
	}
	// for LinkedList
	public boolean isPalindromeList(ListNode head) {
		if(head == null || head.next == null) return true;
		ListNode slow = head;
		ListNode fast = head.next;
		ListNode prev = null;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			ListNode temp = slow.next;
			slow.next = prev;
			prev = slow;
			
			slow = temp;
		}
		// odd and even deal
		if(fast != null) { 
			slow.next = prev;
			prev = slow; 
		} 
		slow = slow.next;
		// from prev adn slow.next begin to compare
		while(slow!= null) {
			if(slow.val != prev.val) return false;
			prev = prev.next;
			slow = slow.next;
		}
		return true;
	}
}

class ListNode {
    int val;
   ListNode next;
   ListNode(int x) { val = x; }
}
