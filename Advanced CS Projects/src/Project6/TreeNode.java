package Project6;

public class TreeNode<T extends Comparable<T>> {
	
	// Comment
	public TreeNode<T> left,right;
	private T data;
	
	// Default constructor
	public TreeNode() {
		
	}
	
	// Constructor that takes data
	public TreeNode(T data) {
		this.data = data;
	}
	
	// Constructor that takes data, a left node, and a right node
	public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	// Sets the node's data to something new
	// Returns the old data
	public T setData(T newData) {
		T oldData = data;
		data = newData;
		return oldData;
	}
	
	public T getData() {
		return data;
	}
	
}
