package Project6;

import java.util.List;

public class MyBinaryTree<T extends Comparable<T>> {
	
	protected TreeNode<T> root;
	
	// Default constructor
	// When the user adds a node to the tree, if the root node has no
	// data, the data the user is trying to add will be given 
	// to the root node of the tree
	public MyBinaryTree() {
		root = new TreeNode<T>();
	}
	
	// Constructor for specific root
	public MyBinaryTree(TreeNode<T> n) {
		root = n;
	}
	
	// Constructor for specific data
	public MyBinaryTree(T data) {
		root = new TreeNode<T>(data);
	}
	
	/*
	 * Count the number of nodes in a given tree by specifying the node
	 * Returns an int count for the number of nodes in the tree
	 */
	public int countNodes(TreeNode<T> n) {
		int count = 0;
		if (n == null) {
			return count; // The tree is empty if the root is null 
		} else {
			count += 1; // The root node isn't null, so increment count
			count += countNodes(n.left); // Count all the left nodes
			count += countNodes(n.right); // Count all the right nodes
			return count; // Return the number of nodes
		}
	}
	
	// Builds a list with the nodes in the order root, left, right
	@SuppressWarnings("unchecked")
	public void preOrder(TreeNode<?> n, List<T> al) {
		if (n == null) {
			return;
		} else {
			al.add((T) n.getData()); // Add the root data to the tree
			preOrder(n.left, al); // Go to the left
			preOrder(n.right, al); // Go to the right	
		}
	}
	
	// Builds a list with the nodes in the order left, right, root
	@SuppressWarnings("unchecked")
	public void postOrder(TreeNode<?> n, List<T> al) {
		if(n == null) {
			return;
		} else {
			postOrder(n.left, al);
			postOrder(n.right, al);
			al.add((T) n.getData());
		}
	}
	
	// Builds a list with the nodes in the order left, root, right
	public void inOrder(TreeNode<? extends T> n, List<T> al) {
		if (n == null) {
			return;
		} else {
			inOrder(n.left, al);
			al.add(n.getData());
			inOrder(n.right, al);
		}
	}
	
	// Sets the root of the tree to a new node holding specified data
	public void setRoot(T data) {
		root = new TreeNode<T>(data);
	}
	
	// Sets the root of the tree to a specified node
	public void setRoot(TreeNode<T> newRoot) {
		root = newRoot;
	}
	
	// Return if the tree is empty or not 
	public boolean isEmpty() {
		return (countNodes(root) == 0);
	}
	
	// Return the number of nodes in the tree
	// Calls the countNodes method so the user can call size()
	// without having to know about the existence of nodes
	public int size() {
		return countNodes(root);
	}
	
	// Return the root node of the tree
	public TreeNode<T> getRoot() {
		return root;
	}
	
	// Populate the binary tree with ints
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populateInts() {
		
		root = new TreeNode(0);
		
		root.left = new TreeNode(1);
		root.right = new TreeNode(2);
		
		TreeNode<T> newRoot = root.left;
		newRoot.left = new TreeNode(3);
		newRoot.right = new TreeNode(4);
		
		newRoot = root.right;
		newRoot.left = new TreeNode(5);
		newRoot.right = new TreeNode(6);
		
		newRoot = root.left.left;
		newRoot.left = new TreeNode(7);
		newRoot.right = new TreeNode(8);
		
		newRoot = root.left.right;
		newRoot.left = new TreeNode(9);
		newRoot.right = new TreeNode(10);
		
		newRoot = root.right.left;
		newRoot.left = new TreeNode(11);
		newRoot.right = new TreeNode(12);
		
		newRoot = root.right.right;
		newRoot.left = new TreeNode(13);
		newRoot.right = new TreeNode(14);
	}
	
	public static void main(String[] args) {

	}
}
