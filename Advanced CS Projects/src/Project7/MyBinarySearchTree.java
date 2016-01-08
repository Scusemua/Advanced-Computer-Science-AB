package Project7;

import java.util.ArrayList;

import Project6.MyBinaryTree;
import Project6.TreeNode;

public class MyBinarySearchTree<T extends Comparable<T>> extends MyBinaryTree<T> {
	
	// Default constructor
	public MyBinarySearchTree() {
		root = new TreeNode<T>(); // Created without any data...
	}
	
	// Constructor for specific data 
	public MyBinarySearchTree(T data) {
		root = new TreeNode<T>(data);
	}
	
	// Calls the private add method
	// which recursively adds a node to the tree
	public boolean add(T data) {
		TreeNode<T> newNode = new TreeNode<T>(data);
		return addRec(root, newNode);
	}
	
	// Recursively adds node to the tree
	private boolean addRec(TreeNode<T> root, TreeNode<T> toAdd) {
		if(root.getData().compareTo(toAdd.getData()) < -1) {
			if(root.left != null) {
				addRec(root.left, toAdd);
			} else {
				root.left = toAdd;
			}
		} else if (root.getData().compareTo(toAdd.getData()) == 0) {
			// They're the same...
		} else if (root.getData().compareTo(toAdd.getData()) > 1){
			if(root.right != null) {
				addRec(root.right, toAdd);
			} else {
				root.right = toAdd;
			}
		}
		return true;
	}
	
	// Recursively look for and return the node with the correct data
	public T get(T data) {
		return getRec(root, data);
	}
	
	// Recursively called get() method
	private T getRec(TreeNode<T> root, T data) {
		if(root == null) {
			return null;
		}
		
		if(root.getData().equals(data)) {
			return root.getData();
		}
		
		getRec(root.left, data);
		getRec(root.right, data);
		
		return null;
	}
	
	public boolean remove(T data) {
		return removeRec(root, data);
	}
	
	// Recursive remove value to find the node to be removed
	private boolean removeRec(TreeNode<T> n, T data) {
		if(n == null) return false;
		
		if(n.getData().equals(data)) {
			
			// Has no children
			if(n.left == null && n.right == null) {
				TreeNode<T> tempParent = new TreeNode<T>();
				tempParent.left = n;
				boolean toReturn = removeRec(root, tempParent);
				return toReturn;
			}
			
			// Has one child (XOR)
			if(n.left != null ^ n.right != null) {
				if(n.left != null) {
					// T tempData = n.getData();
					n = n.left;
					return true;
				} else {
					// T tempData = n.getData();
					n = n.right;
					return true;
				}
			}
			
			// Has two children
			if(n.left != null && n.right != null) {
				
			}
		}
		
		removeRec(n.left, data);
		removeRec(n.right, data);
		
		return false;
	}
	
	// Recursive remove method to find the PARENT of the node to be removed
	// Once the parent is found, remove the node to be removed
	private boolean removeRec(TreeNode<T> root, TreeNode<T> tempParent) {
		if(root == null) return false;
		if(root.left == tempParent.left) {
			root.left = null;
			return true;
		} else if(root.right == tempParent.left) {
			root.right = null;
			return true;
		}
		// Don't bother going down the right if the node is found on the left
		boolean cont = removeRec(root.left, tempParent);
		if (!cont) removeRec(root.right, tempParent);
		return false;
	}
	
	private TreeNode<T> minValue(TreeNode<T> start) {
		
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
}
