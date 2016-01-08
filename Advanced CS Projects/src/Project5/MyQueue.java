package Project5;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyQueue<T> {
	
	private LinkedList<T> elements;
	private int size;
	private int maxCapacity;
	
	public MyQueue(int max) {
		elements = new LinkedList<T>();
		size = 0;
		maxCapacity = max;
	}
	
	public MyQueue() {
		elements = new LinkedList<T>();
		size = 0;
		maxCapacity = 10;
	}
	
	public boolean add(T data) {
		if(data == null) {
			throw new NullPointerException("No nulls in this queue implementation!");
		} else if (size < maxCapacity) {
			elements.addLast(data);
			size++;
			return true;
		} else {
			throw new IllegalStateException("Queue is already at maximum capacity!");
		}
	}
	
	// Differs from add() in that it returns false instead of throwing an exception
	public boolean offer(T data) {
		if(data == null) {
			throw new NullPointerException("No nulls in this queue implementation!");
		} else if(size < maxCapacity) {
			elements.addLast(data);
			size++;
			return true;
		} else {
			return false;
		}
	}
	
	public T remove() {
		if(size != 0) {
			T removed = elements.getFirst();
			elements.removeFirst();
			size--;
			return removed;
		} else {
			throw new NoSuchElementException("Queue is empty!");
		}
	}
	
	// Returns and removes the head of the queue
	// Returns null if the queue is empty
	public T poll() {
		if (size != 0) {
			T removed = remove();
			return removed;
		} else {
			return null;
		}
	}
	
	// Returns, but does NOT remove, the head element of the queue 
	// Differs from peek() in that it throws an exception instead of returning null
	public T element() {
		if (size != 0) {
			return elements.getFirst();
		} else {
			throw new NoSuchElementException("Queue is empty!");
		}
	}
	
	// Returns, but does NOT remove, the head element of the queue
	// Differs from element() in that it doesn't return an exception but instead returns null
	public T peek() {
		if (size != 0) {
			return element();
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		MyQueue<Integer> queueKekDefault = new MyQueue<Integer>();
		MyQueue<Integer> queueKek = new MyQueue<Integer>(5);
		
		queueKek.add(1);
		queueKek.add(2);
		queueKek.add(3);
		queueKek.add(4);
		queueKek.add(5);
		// queueKek.add(6);
		
		System.out.println(queueKek.peek());
		System.out.println(queueKekDefault.poll());
		
		System.out.println(queueKek.peek());
	}
}
