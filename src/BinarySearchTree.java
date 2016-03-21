import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class BinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

	}
	public class Node {
		public int data;
		public Node Left;
		public Node Right;
		private Node() { }
		public Node(int data) {
			this.data = data;
			this.Left = null;
			this.Right = null;
		}
	}

	class BST {
		Node root;
		
		// insert, remove
		public BST() {
			root = null;
		}
		
		public void insert(int data) {
			// define a new node to store this value
			// corner check-- root is null
			// use a parent pointer and a current point to iterater bst
			// when current data > target data must to left tree;
			// when current data < target data must to right tree;
			Node node = new Node(data);
			if( root == null ) {
				root = node;
				return;
			}
			Node parent = null;
			Node current = root;
			while(current != null) {
				parent = current;
				if(current.data > data) {
					current = current.Left;
				} else {
					current = current.Right;
				}
			}
			if(parent.data > data) {
				parent.Left = node;
			} else {
				parent.Right = node;
			}
			return;
		}
		// find minimum node
		public Node FindMin(Node node) {
			// corner check
			if( node == null ) {
				return null;
			}
			if( node.Left == null ) {
				return node;
			}
			return FindMin(node.Left);
		}
		private Node FindMinIter(Node node) {
			if(node == null) {
				return null;
			}
			Node curr = root;
			while(curr.Left != null) {
				curr = curr.Left;
			}
			return curr;
		}
		// remove
		public void Remove(int data) {
			Remove(root, null, data);
		}
		private void Remove(Node curr, Node parent, int data) {
			// recursion to dealing
			// corner check
			// if not find node judge go to left or right to process this func
			// find this node divide as several situation:
			// 1 this node no children : parent's left or right is null and this node set null
			// 2 this node only has left children-left to instead the node
			// 3 this node only has right children-right to instead of the node
			// 4 thid node has both 
			if(curr == null || parent == null) {
				return;
			}
			if( curr.data > data) {
				Remove(curr.Left, curr, data);
			} else {
				if( curr.data < data ) {
					Remove(curr.Right, curr, data);
				} else {
					// has find the data to deal by children position
					// no children
					if( curr.Left == null && curr.Right == null ) {
						// both children not existed
						if( parent.Left == curr ) {
							parent.Left = null;
						} else {
							parent.Right = null;
						}
						curr = null;
						return;
					} else {
						if( curr.Left == null || curr.Right == null) {
							// only exist one children
							if( curr.Left != null ) {
								// why don't deal the parent.right == curr situation ???
								if( parent.Left == curr )
									parent.Left = curr.Left;
							} else {
								if( parent.Right == curr)
									parent.Right = curr.Right;
							}
						} else {
							// both children exist
							// to find the min ele of the node right then instead of this node
							curr.data = FindMin(curr.Right).data;
							Remove(curr.Right, curr, curr.data);
						}
					}
				} // else children
			}
			return;
		}
		
		// preorder
		public void PreOrder() {
			PreOrder(root);
		}
		private void PreOrder(Node node) {
			if(node == null) return;
			System.out.println(node.data);
			PreOrder(node.Left);
			PreOrder(node.Right);
		}
		
		// inorder
		public void InOrder() {
			InOrder(root);
		}
		private void InOrder(Node node) {
			if(node != null) {
				InOrder(node.Left);
				System.out.println(node.data);
				InOrder(node.Right);
			}	
		}
		
		// the height of BST
		public int GetHeight() {
			return GetHeight(root);
		}
		private int GetHeight(Node node) {
			if(node == null) return 0;
			// divide as two part
			int lHeight = GetHeight(node.Left);
			int rHeight = GetHeight(node.Right);
			// conquer
			return 1 + Math.max(lHeight, rHeight);
		}
		
		// size- get the size of this tree
		public int Size() {
			return Size(root);
		}
		private int Size(Node node) {
			if( node == null ) {
				return 0;
			}
			// recuresion: divde & conquer
			return Size(node.Left) + Size(node.Right) + 1;
			// non-recursive method--follow up
		}
		
		// isBst - assertion this tree is BST
		public boolean isBST() {
			return isBST( root, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		private boolean isBST(Node node, int min, int max) {
			if( node == null ) return true;
			
			// not satify BST condition min and max represent this sub tree among
			if( node.data < min || node.data > max ) {
				return false;
			}
			return isBST(node.Left, min, node.data) && isBST(node.Right, node.data, max);
		}
		
		// levelOrder
		public void PrintLevelOrder() {
			PrintLevelOrder(root);
		}
		private void PrintLevelOrder(Node node) {
			if( node == null ) return;
			// user queue data structure to store every setp
			// first root put into queue
			// then loop the queue while not empty
			// Inside loop: count the current size of queue
			// loop the size and poll every ele and print this layer content
			// for every ele push every left and right ele to queue
			Queue<Node> queue = new LinkedList<Node>();
			queue.offer(root);
			while(!queue.isEmpty()) {
				int qsize = queue.size();
				for(int i = 0; i < qsize; i++) {
					Node temp = queue.poll();
					System.out.println(temp.data);
					if(temp.Left != null) {
						queue.offer(temp.Left);
					}
					if(temp.Right != null) {
						queue.offer(temp.Right);
					}
				}
			}
			return;
		}
		
		// print ZigZag
		public void PrintZigZag() {
			PrintZigZag(root);
		}
		private void PrintZigZag( Node node ) {
			if( node == null ) return;
			Queue<Node> queue = new LinkedList<Node>();
			queue.offer(root);
			boolean bRighttoLeft = false;
			while(!queue.isEmpty()) {
				int qsize = queue.size();
				Stack<Node> zigStack = new Stack<Node>();
				for(int i = 0; i < qsize; i++) {
					Node temp = queue.poll();
					System.out.println(temp.data);
					if(temp.Left != null) {
						queue.offer(temp.Left);
					}
					if(temp.Right != null) {
						queue.offer(temp.Right);
					}
					if(bRighttoLeft) {
						zigStack.push(temp);
					}
				}
				while(!zigStack.isEmpty()) {
					Node stackcurr = zigStack.pop();
					System.out.println(stackcurr.data);
				}
				if(bRighttoLeft) bRighttoLeft = false;
				else bRighttoLeft = true;
			}
		}
		
		// print perimeter of anti-clockwise
		public void PrintPerimeter() {
			// break the problem in 3 parts:
			// 1 print the left boundary in top-down manner
			// 2 print the leaf from left to right, which can again be sub-divided into two sub-parts
			//   2.1 print all leaf nodes of left sub-tree from left to right
			//   2.2 print all leaf nodes of right sub-tree from left to right
			// 3 print the right boundary in bottom-up manner
			PrintPerimeter(root, 0, 0);
			System.out.println(root.data);
		}
		private void PrintPerimeter(Node node, int left, int right) {
			if (node != null)
            {
                int l = left;
                int r = right;
                if (right == 0 && left != 0)
                    System.out.println(node.data);
                else if (left == 0 && right != 0)
                	System.out.println(node.data);
                else if (node.Left == null && node.Right == null)
                	System.out.println(node.data);
                 l = l +1;
                PrintPerimeter(node.Left, l, r);
                r = r + 1;
                PrintPerimeter(node.Right, l, r);
            }
		}
		
		// Horizontal distance(hd) of root = 0
	    // If you go left then hd = hd(of its parent)-1, and 
	    //	    if you go right then hd = hd(of its parent)+1.
		public void PrintBottomView() {
			PrintBottomView(root, 0);
		}
		private void PrintBottomView(Node node, int level) {
			if( node == null ) return;
			Queue<SuperNode> queue = new LinkedList<SuperNode>();
			Map<Integer, Node> map = new TreeMap<Integer, Node>();
			SuperNode superroot = new SuperNode(node, level);
			queue.offer(superroot);
			while(!queue.isEmpty()) {
				SuperNode temp = queue.poll();
				Node tnode = temp.node;
				int tlevel = temp.level;
				map.put(tlevel, tnode);
				
				if(tnode.Left != null) {
					queue.offer(new SuperNode(tnode.Left, tlevel - 1));
				}
				if(tnode.Right != null) {
					queue.offer(new SuperNode(tnode.Right, tlevel + 1));
				}
			} // finish all bottom view ele put into TreeMap
			
			// user iterator to loop out all ele of treemap
			Set<Integer> keys = map.keySet();
			for(Integer key : keys) {
				System.out.println(map.get(key));
			}
		}
		
		class SuperNode {
			int level;
			Node node;
			public SuperNode(Node node, int level) {
				this.level = level;
				this.node = node;
			}
		}
		
		//http://www.geeksforgeeks.org/bottom-view-binary-tree/ for bottom view iterator
	} // end class BST
}
