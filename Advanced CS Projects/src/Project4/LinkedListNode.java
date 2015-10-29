package Project4;

public class LinkedListNode<T> {
	// The next and previous nodes
	private LinkedListNode<T> next, previous;
	
	// Data for the node
	private T data;
	
	// Constructor
	public LinkedListNode(LinkedListNode<T> next, LinkedListNode<T> previous, T data) {
		this.next = next;
		this.previous = previous;
		this.data = data;
	}
	
	// Get the previous node
	public LinkedListNode<T> getPrevious() {
		return previous;
	}
	
	// Get the next node
	public LinkedListNode<T> getNext() {
		return next;
	}
	
	// Get the node's data
	public T getData() {
		return data;
	}
	
	// Set method to change the next node this node is pointing to
	public void setNext(LinkedListNode<T> newNext) {
		next = newNext;
	}
	
	// Set method to change the previous node this node is pointing to 
	public void setPrevious(LinkedListNode<T> newPrevious) {
		previous = newPrevious;
	}
	
	// Change the node's data
	public void setData(T newData) {
		data = newData;
	}
}
