package Project5;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MyStack<T> {

	private LinkedList<T> elements;
	private int size;
	
	public MyStack() {
		elements = new LinkedList<T>();
		size = 0;
	}
	
	public T peek() {
		if (size != 0) { 
			return elements.get(elements.size() - 1); 
		} else { 
			throw new EmptyStackException();
		}
	}
	
	public T push(T data) {
		elements.addLast(data);
		size++;
		return data;
	}
	
	public T pop() {
		if (size > 0) {
			T removed = elements.getLast();
			elements.removeLast();
			size--;
			return removed;	
		} else {
			throw new EmptyStackException();
		}
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	public int search(T searchFor) {
		if(elements.contains(searchFor)) {
			int index = elements.lastIndexOf(searchFor);
			index = elements.size() - index;
			return index;
		} else {
			return -1;
		}
	}
	
	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		MyStack<Integer> stackKek = new MyStack<Integer>();
		stackKek.push(3);
		stackKek.push(4);
		stackKek.push(5);
		System.out.println(stackKek.search(5));
		stackKek.pop();
		System.out.println(stackKek.peek());
	}
	
}
