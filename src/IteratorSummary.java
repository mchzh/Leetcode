import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IteratorSummary {
	/*Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
	Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
	Call next() gets you 1, the first element in the list.
	Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
	You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.*/
	
}

//Java Iterator interface reference:
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
	
	Iterator<Integer> it;
	Integer peek;
	
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.it = iterator;
	    peek = it.hasNext() ? it.next() : null;
	}

 // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return peek;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(!hasNext()) return 0;
	    Integer ret = peek;
	    peek = it.hasNext() ? it.next() : null;
	    return ret;
	}

	@Override
	public boolean hasNext() {
	    return peek != null;
	}
}

/*Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.
Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
class BSTIterator {
// stack + preorder + helper include the pre part of preorder + next include the last part of preorder
	Stack<TreeNode> stack = new Stack<TreeNode>();
    public BSTIterator(TreeNode root) {
        helper(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
    	if( hasNext() ) { return 0; }
        TreeNode curr = stack.pop();
        helper(curr.right);
        return curr.val;
    }
    private void helper(TreeNode node) {
    	while(node != null) {
    		stack.push(node);
    		node = node.left;
    	}
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

class ZigZagIteratorTwo {
	
	Iterator<Integer> it1;
	Iterator<Integer> it2;
	int turn;
	
	public ZigZagIteratorTwo(List<Integer> v1, List<Integer> v2) {
		this.it1 = v1.iterator();
		this.it2 = v2.iterator();
		this.turn = 0;
	}
	
	public boolean hasNext() {
		return it1.hasNext() || it2.hasNext();
	}
	public int next() {
		if( !hasNext() ) {
			return 0;
		}
		turn++;
		// turn is odd return it1.next
		if(((turn % 2 == 1) && it1.hasNext()) || (!it2.hasNext())) {
			return it1.next();
		}
		if(((turn % 2 == 0) && it2.hasNext()) || (!it1.hasNext())) {
			return it2.next();
		}
		return 0;
	}
}

class ZigZagIteratorList {
	
	List<Iterator<Integer>> itGetList;
	//Iterator<Integer> it2;
	int turn;
	
	public ZigZagIteratorList(List<Iterator<Integer>> itList) {
		this.itGetList = new LinkedList<Iterator<Integer>>();
		for(Iterator<Integer> it : itList) {
			if( !it.hasNext() ) {
				itGetList.add(it);
			}
		}
		this.turn = 0;
	}
	
	public boolean hasNext() {
		return itGetList.size() > 0;
	}
	public int next() {
		if( !hasNext() ) {
			return 0;
		}
		turn++;
		// turn mark the position of list which need to next
		// int index =
		while( !itGetList.get(turn % itGetList.size()).hasNext() ) {
			itGetList.remove(turn % itGetList.size());
			turn--;
		}
		return itGetList.get(turn % itGetList.size()).next();
		
		/*Integer res = 0;
        // 算出本次使用的迭代器的下标
        int pos = turns % itlist.size();
        Iterator<Integer> curr = itlist.get(pos);
        res = curr.next();
        // 如果这个迭代器用完，就将其从列表中移出
        if(!curr.hasNext()){
            itlist.remove(turns % itlist.size());
            // turns变量更新为上一个下标
            turns = pos - 1;
        }
        turns++;
        return res;*/
	}
}