import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import base.util.ListNode;

public class ListNodeClassic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		list.add("car");
		list.add("ca");
		list.add("rs");
		wordBreak("cars", list);
	}
	// 8. String to Integer (atoi)
	public int myAtoi(String str) {
        // long also overflow have to double to save the middle result
        // "+1", "-1", "-", "    010"
        //corner
        if(str == null || str.length() == 0) return 0;
        // erase white space begin and end
        double result = 0;
        str.trim();
        // remove white space
        int left = 0;
        int right = str.length() -1;
        while(left < right && str.charAt(left) == ' ' ) { left++; }
        while(left < right && str.charAt(right) == ' ' ) { right--; }
        str = str.substring(left, right+1);
        // tranverse this str
        // the first char is sign + and -
        double sign = 1;
        for(int i = 0; i < str.length(); i++) {
            // "+-2" = 0
            char ch = str.charAt(i);
            if((ch=='+' || ch=='-') && i == 0) {
                sign = ch=='-' ? -1 : 1;
                // deal multiple sign following
                
                if((i+1) < str.length() && (str.charAt(i+1)=='+' || str.charAt(i+1)=='-')) {
                    return 0;
                }
                continue;
            }
            if(ch <'0' || ch >'9') break; //"    -12a42"
            else {
                result = result * 10 + Character.getNumericValue(str.charAt(i));
            }
        }
        
        /*long temp = Integer.MAX_VALUE;
        long temp1 = temp + 1;
        if(result > temp1) return 0;
        if(result == temp1 && sign == -1) return Integer.MIN_VALUE; //"-"*/
        double finali = sign * result;
        if(finali > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(finali < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int)finali;
    }
	//23. Merge k Sorted Lists
	private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            return left.val - right.val;
        }
    };
    
	public ListNode mergeKLists(ListNode[] lists) {
        // one method is use min heap priority queue
        // another is divide&conquer;  third method merge two by two
        if(lists==null || lists.length ==0) return null;
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, ListNodeComparator);
        
        //  first element of every listnode go into heap;
        for(ListNode list : lists) {
            if(list!=null) {
                heap.offer(list);
            }
        }
        
        //while heap not empty continu
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(!heap.isEmpty()) {
            ListNode temp = heap.poll();
            tail.next = temp;
            tail = temp;
            
            if(temp.next != null) {
                heap.offer(temp.next);
            }
        }
        return dummy.next;
    }
	//139. Word Break
	public static boolean wordBreak(String s, List<String> wordDict) {
        // Bfs + DP
        // dp[i] present previous i element can be segment into wordDict
        // dp[i] = true, if sub(0, i-1) is a word in wordDict
        //               if existed k(k < i), dp[k] true and sub(k+1) is a  word in wordDict
        //         false, if not exited k which k match the below requirenment
        if(s==null || s.length() == 0) return false;
        return helper(s, wordDict, 0);
    }
    public static boolean helper(String s, List<String> wordDict, int start) {
        if(start==s.length()) {
            return true;
        }
        
        for(int i = 0; i < wordDict.size(); i++) {
            String str = wordDict.get(i);
            int len = str.length();
            int end = start +len;
            if(end > s.length()) continue;
            
            if(s.substring(start, end).equals(str)) {
                return helper(s, wordDict, start+len);
            }
        }
        return false;
    }
	//141. Linked List Cycle
	public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
	//242. Valid Anagram
	public static boolean isAnagram(String s, String t) {
        if(s==null || t==null || s.length() != t.length()) return false;
        int[] mark = new int[26];
        int len = s.length();
        for(int i = 0; i < len; i++) {
            mark[s.charAt(i)-'a']++;
            mark[t.charAt(i)-'a']--;
        }
        for(int i = 0; i < 26; i++) {
            if(mark[i]!=0) return false;
        }
        return true;
    }
	//215. Kth Largest Element in an Array
	public int findKthLargest(int[] nums, int k) {
        if(nums==null || nums.length==0) return 0;
        // max heap priorityqueue
        /*PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b-a;
            }
        });*/
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k);
        for(int num : nums) {
            if(heap.size() < k) {
                heap.offer(num);
            } else {
                if(heap.peek() < num) {
                    heap.poll();
                    heap.offer(num);
                }
            }
            //heap.offer(num);
        }
        return heap.peek();
    }
	//234. Palindrome Linked List
	public boolean isPalindrome(ListNode head) {
        // find the middle node 
        // go left and right two direction, every step to compare every element
        if(head == null || head.next == null) return true;
        ListNode lencal = head;
        int len = getlen(lencal);
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            //reverse slow
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
            //slow = slow.next;
            fast = fast.next.next;
        }
        // from left and right direction to forward this listnode
        ListNode right = slow.next; // no matter even and odd, the right part is slow.next;
        if(len%2==0) {
            //ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
        }
        while(prev!=null && right!=null) {
            if(prev.val != right.val) return false;
            prev = prev.next;
            right = right.next;
        }
        return true;
    }
    public int getlen(ListNode node) {
        int count = 0;
        while(node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
}
