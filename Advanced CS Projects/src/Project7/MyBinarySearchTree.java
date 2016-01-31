package Project7;

import java.util.ArrayList;
import java.util.LinkedList;

import Project6.MyBinaryTree;
import Project6.TreeNode;

public class MyBinarySearchTree<T extends Comparable<T>> extends MyBinaryTree<T> {
	
	// Default constructor
	public MyBinarySearchTree() {
		// root = new TreeNode<T>(); // Created without any data...
	}
	
	// Constructor for specific data 
	public MyBinarySearchTree(T data) {
		if (data == null) {
			throw new IllegalArgumentException("No adding null values");
		} 
		root = new TreeNode<T>(data);
	}
	
	// Calls the private add method
	// which recursively adds a node to the tree
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean add(T data) {
		if (data == null) {
			throw new IllegalArgumentException("No adding null values");
		} else if(root == null) {
			root = new TreeNode(data);
		} else {
			TreeNode<T> newNode = new TreeNode<T>(data);
			return addRec(root, newNode);
		}
		return false;
	}
	
	// Recursively adds node to the tree
	private boolean addRec(TreeNode<T> n, TreeNode<T> toAdd) {
		// If the data we're adding is smaller than n's data, go to the left if n.left exists, otherwise add it
		if(n.getData().compareTo(toAdd.getData()) > 0) {
			if(n.left != null) {
				addRec(n.left, toAdd);
			} else {
				n.left = toAdd;
			}
		// If we find a duplicate, just replace it because that's what Roop said we're supposed to do
		} else if (n.getData().compareTo(toAdd.getData()) == 0) {
			n.setData(toAdd.getData());
		// If the node we're adding is bigger, add it to the right if n.right exists, otherwise add it
		} else if (n.getData().compareTo(toAdd.getData()) < 0){
			if(n.right != null) {
				addRec(n.right, toAdd);
			} else {
				n.right = toAdd;
			}
		}
		return true;
	}
	
	// Recursively look for and return the node with the correct data
	public T get(T data) {
		if (data == null) {
			throw new IllegalArgumentException("No adding null values");
		} 
		return getRec(root, data);
	}
	
	// Recursively called get() method
	private T getRec(TreeNode<T> n, T data) {
		if(n == null) {
			return null;
		}
		
		if(n.getData().compareTo(data) == 0) {
			return n.getData();
		// If the data we're getting is smaller...
		} else if(n.getData().compareTo(data) > 0) {
			return getRec(n.left, data);
		// Else if the data we're getting is bigger...
		} else if(n.getData().compareTo(data) < 0) {
			return getRec(n.right, data);
		}
		
		return null;
	}
	
	public void remove(T data) {
		// Can't remove nulls from the list (can't add them either)
		if (data == null) {
			throw new IllegalArgumentException("No adding null values");
		} else if (root == null) {
			return;
		}
		root = removeRec(root, data);
	}
	
	private TreeNode<T> removeRec(TreeNode<T> n, T data) {
		if(n == null) {
			return null;
		}
		
		// We've found a match!
		if(n.getData().compareTo(data) == 0) {
			// Zero children
			if(n.left == null && n.right == null) {
				// Return null because there's nothing below 
				return null;
			}
			
			// One Child
			if(n.left == null) {
				return n.right;
			} else if(n.right == null) {
				return n.left;
			}
			
			// I had an if-statement surround the following code. However, the program
			// would only ever each the if-statement if the condition was true, so I just
			// removed the if-statement as it was redundant. 
			
			// Find the minimum data value to the right of the node to be removed
			// Replace the node-to-be-removed's data with the minimum data found
			T minData = findMin(n.right);
			n.setData(minData);
			
			// Remove the minimum node now
			n.right = removeMin(n.right);
			
		} else {
			// If the data we're adding is smaller than the root's data, go to the left
			if(n.getData().compareTo(data) > 0) {
				n.left = removeRec(n.left, data);
			} 
			
			// If the data we're adding it bigger than the root's data, go to the right
			if(n.getData().compareTo(data) < 0) {
				n.right = removeRec(n.right, data);
			}
		}
		return n;
	}
	
	// Recursively finds the minimum value of the entire tree that's specified
	private T findMin(TreeNode<T> n) {
		if(n.left == null) {
			return n.getData();
		} else {
			return findMin(n.left);
		}
	}
	
	// Recursively removes the minimum value of the tree specified
	private TreeNode<T> removeMin(TreeNode<T> n) {
		if(n.left != null) {
			n.left = removeMin(n.left);
		} else {
			if(n.right != null) {
				return n.right;
			} else {
				return null;
			}
		}
		return n;
	}
	
	// Compute the size recursively 
	public int size() {
		return countNodes(root);
	}
	
	// Represent tree as a String recursively 
	public String toString() {
		ArrayList<T> temp = new ArrayList<T>();
		inOrder(root, temp);
		return temp.toString();
	}
	
	// Convenience 
	private void printToString() {
		System.out.println(toString());
	}
	
	// Convenience
	private void printBreadthFirst() {
		System.out.println(breadthFirst());
	}
	
	// Convenience
	private void printBoth() {
		printToString();
		printBreadthFirst();
	}
	
	// toString() line-by-line (breadth first)
	public String breadthFirst() {
		// Here is my queue
		LinkedList<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
		
		// Although this really doesn't matter, using a StringBuffer from the beginning
		// and appending to it during iteration, than returning the StringBuffer converted to a String
		// is faster. This is because when you concatinate strings with a '+', Java converts the String to
		// a StringBuffer each time, so I'm saving time by skipping the whole conversion.
		StringBuffer build = new StringBuffer();
		
		// If the root node is null, just return an empty string.
		if(root == null) {
			// Convert to a string before returning
			return build.toString(); 
		} else {
			// Add the root to the queue to begin...
			queue.addFirst(root);
			// Continue until there's nothing left in the queue. The queue will always have
			// nodes in it until the tree is completely traversed. 
			while(!queue.isEmpty()) {
				// Remove the node from the queue and store it in another node
				TreeNode<T> current = queue.removeFirst();
				// Add the removed node's data to the StringBuffer build
				build.append(current.getData() + " ");
				// If the removed node has a left child, add it to the queue
				if(current.left != null) queue.addLast(current.left);
				// If the removed node has a right child, add it to the queue
				if(current.right != null) queue.add(current.right);
			}
		}
		return build.toString();
	}
	
	// Main method
	public static void main(String[] args) {
		MyBinarySearchTree<Integer> myBST = new MyBinarySearchTree<Integer>();
		/*
		 * Trees tested:
		 * 
		 * 1. null 			remove 3
		 * 
		 * 2. 10 			remove 3, remove 10
		 * 
		 * 3.  10 			remove 3, remove 10
		 *    /
		 *   3 
		 * 
		 * 4. 10			remove 15, remove 10
		 *      \
		 *       15
		 *       
		 * 5. 10			remove 3, remove 10, remove 15
		 *   /  \
		 *  3    15  
		 *  
		 *  
		 * 6.   10			remove 3
		 *     /  \
		 *    3    15
		 *   /
		 *  1
		 *  
		 * 7.   10			remove 3
		 *     /  \
		 *    3    15  
		 *     \
		 *      7
		 *      		
		 * 8.   10			remove 3
		 *     /  \
		 *    3    15  
		 *   / \
		 *  1   7
		 *     /
		 *    6    
		 */
		myBST.add(10);
		myBST.add(3);
		
		// myBST.printBoth();
		
		myBST.remove(3);
		myBST.remove(13);
		
		// myBST.printBoth();
		
		myBST.clear();
		
		myBST.add(10);
		myBST.add(13);
		// myBST.printBoth();
		myBST.remove(3);
		// myBST.printBoth();
		myBST.remove(13);
		// myBST.printBoth();
		
		myBST.clear();
		
		myBST.add(10);
		myBST.add(3);
		myBST.add(15);
		//myBST.printBoth();
		//myBST.remove(3);
		//myBST.printBoth();
		//myBST.remove(15);
		//myBST.printBoth();
		//myBST.remove(10);
		//myBST.printBoth();
		//myBST.add(2);
		//myBST.add(1);
		myBST.add(7);
		myBST.add(6);
		myBST.add(8);
		myBST.add(1);
		myBST.printBoth();
		//myBST.remove(4);
		myBST.remove(3);
		myBST.printBoth();
		
		/* myBST.add(10);
		myBST.add(7);
		myBST.add(15);
		myBST.add(5);
		myBST.add(12);
		myBST.add(20);
		myBST.printBoth();
		myBST.remove(15);
		myBST.printBoth(); */
		
		
		/* myBST.add(10);
		myBST.printToString();
		myBST.add(12);
		myBST.add(8);
		myBST.add(3);
		myBST.add(15);
		myBST.add(11);
		myBST.add(1);
		myBST.add(2);
		myBST.add(3);
		myBST.add(4);
		myBST.add(5);
		myBST.add(6);
		myBST.add(62);
		myBST.add(64);
		myBST.add(66);
		myBST.add(126);
		myBST.printToString();
		myBST.printBreadthFirst();
		System.out.println(myBST.size());
		myBST.add(122);
		System.out.println(myBST.size());
		myBST.remove(8);
		System.out.println(myBST.size());
		myBST.printToString();
		myBST.printBreadthFirst(); 
		System.out.println(myBST.get(126));
		System.out.println(myBST.get(8)); */
	}
}
