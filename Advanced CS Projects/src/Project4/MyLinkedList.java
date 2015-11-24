package Project4;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class MyLinkedList<T> implements List<T> {
	// Head node
	private LinkedListNode<T> head;
	
	// Tail node
	private LinkedListNode<T> tail;
	
	// Number of nodes
	private int nodeCount = 0;
	
	// Check for concurrent modification while iterating 
	private int modcount = 0;
	
	// Constructor 
	public MyLinkedList() {
		// Create the new MyLinkedList. The head does not hold any data,
		// nor does it hold a pointer to a previous node. It also currently does
		// not hold a pointer to the next node as the head node is currently the 
		// only node in the MyLinkedList
		head = new LinkedListNode<T>(tail, null, null);
		tail = new LinkedListNode<T>(null, head, null);
		head.setNext(tail);
		System.out.println("Constructor called!");
	}
	
	public boolean addFirst(T data) {
		LinkedListNode<T> toAdd = new LinkedListNode<T>(tail, head, data);
		head.setNext(toAdd);
		tail.setPrevious(toAdd);
		modcount++;
		nodeCount = 1;
		return true;
	}
	
	// Append a node to the end of the MyLinkedList
	// If the head node hasn't been initialized, initialize it
	// Adds at the end of the LinkedList
	@Override
	public boolean add(T data) {
		LinkedListNode<T> toAdd = new LinkedListNode<T>(tail, tail.getPrevious(), (T) data);
		tail.getPrevious().setNext(toAdd);
		tail.setPrevious(toAdd);
		toAdd.setNext(tail);
		
		modcount++;
		nodeCount++;
		
		return true;
	}
	
	// Add a new node at a specific index
	public void add(int index, T data) {
		if(index > size() || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
		
		LinkedListNode<T> temp = head.getNext();
		int i = 0;
		while(true) {
			if(temp == null) {
				throw new InternalError("add(int index, T data) iterates past the end");
			}
			if(index == i) {
				LinkedListNode<T> toAdd = new LinkedListNode<T>(temp, temp.getPrevious(), data);
				temp.getPrevious().setNext(toAdd);
				temp.setPrevious(toAdd);
				modcount++;
				nodeCount++;
				return;
			}
			i++;
			temp = temp.getNext();
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean addAll(Collection<? extends T> data) {
		for(T element: data) {
			add(element);
		}
		
		return true;
		
	}
	
	public boolean addAll(int index, Collection<? extends T> data) {
		if(data.size() == 0) return false;
		
		LinkedListNode<T> temp;
		if(index > size() || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		} else {
			temp = head.getNext();
			for(int i = 0; i < index; i++) {
				temp = temp.getNext();
			}
		}
		
		for(T element: data) {
			LinkedListNode<T> toAdd = new LinkedListNode<T>(temp, temp.getPrevious(), element);
			temp.getPrevious().setNext(toAdd);
			temp.setPrevious(toAdd);
		}
		
		nodeCount += data.size();
		
		return true;
	}
	
	// Clear all the nodes
	public void clear() {
		head.setNext(null);
		modcount++;
		nodeCount = 0;
	}
	
	public boolean contains(Object obj) {
		
		// Used to go thru the list
		LinkedListNode<T> temp = head.getNext();
		
		// Look at each node
		while(temp != tail) {
			// Check to see if the node's data is the same as the object
			if(temp.getData().equals(obj)) {
				return true;
			}
			
			temp = temp.getNext();
		}
		
		// If it wasn't found
		return false;
	}
	
	public boolean containsAll(Collection data) {
		Object[] dataArr = data.toArray();
		LinkedListNode<T> temp = head; // Navigate
		
		for(int i = 0; i < dataArr.length; i++) {	
			Object toCheck = dataArr[i];
			boolean found = false;
			
			while(temp.getNext() != null) {
				temp = temp.getNext();
				if(temp.getData().equals(toCheck)) {
					found = true;
				}
			}
			if(found == false) {
				return false;
			} else {
				found = false; // Reset the boolean
				// so you can use it to check if the next element
				// in the dataArr is found
			}
		}
		
		return true;
	}
	
	public T get(int index) {
		LinkedListNode<T> temp;
		if(index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		} else {
			temp = head.getNext();
			// System.out.println("Index: " + index);
			// System.out.println("Data: " + temp.getData());
			for(int i = 0; i < index; i++) {
				//System.out.println("iterate");
				temp = temp.getNext();
			}
		}
		
		return temp.getData();
	}
	
	// Return the index of the specified object (assuming it's in the list)
	public int indexOf(Object obj) {
		
		// If the list is empty
		if(head == null) {
			System.out.println("This list is empty so it does not contain the object in question.");
			return -1;
		}
		
		// Used to go thru the list
		LinkedListNode<T> temp = head.getNext();
		
		// Look at each node
		int index = 0;
		while(temp.getNext() != null) {
			
			// Check to see if the node's data is the same as the object
			if(temp.getData().equals(obj)) {
				
				// If it is, return the index we're at
				return index;
			}
			
			temp = temp.getNext();
			index++; // Increment the variable that keeps track of the index we're at 
		}
		
		// It wasn't found, so return -1
		return -1;
	}
	
	public boolean isEmpty() {
		if(head == null) return true;
		if(nodeCount == 0) return true;
		
		return false;
	}
	
	/*
	 * TODO: THIS METHOD RIGHT HERE, BOYS
	 */
	public Iterator iterator() {
		return new LinkedListIterator();
	}
	
	public int lastIndexOf(Object data) {
		LinkedListNode<T> temp = head;
		int index = 0;
		
		// Get to the end of the list
		while(temp.getNext() != null) {
			temp = temp.getNext(); 
			index++;
		}
		
		// Now that we're at the end, hunt thru the List backwards looking for the data
		while(temp.getPrevious() != null) {
			temp = temp.getPrevious();
			index--;
			if(temp.getData().equals(data)) return index;
		}
		
		// If nothing is found, return -1
		return -1;
	}
	
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ListIterator listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean remove(Object data) {
		LinkedListNode<T> temp = head.getNext();
		while(temp != tail) {
			if(temp.getData() == null) {
				if(data == null) {
					temp.getPrevious().setNext(temp.getNext());
					temp.getNext().setPrevious(temp.getPrevious());
					nodeCount--;
					modcount++;
					return true;
				}
			} else if(temp.getData().equals(data)) {
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				nodeCount--;
				modcount++;
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}
	
	public T remove(int i) {
		
		if(i >= size() || i < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
		
		LinkedListNode<T> temp = head.getNext();
		int index = 0;
		while(temp != tail) {
			if(index == i) {
				System.out.println("Node removed at index " + index);
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				nodeCount--;
			}
			index++;
			temp = temp.getNext();
		}
		
		modcount++;
		return temp.getData();
	}
	
	public boolean removeAll(Collection<?> data) {
		
		boolean b = false;
		
		for(Object element: data) {
			b = b || remove(element);
		}
		
		modcount++;
		return b;
	}
	
	public boolean retainAll(Collection data) {
		
		int originalNodeCount = nodeCount;
		
		if(data.size() == 0) {
			return false;
		} 
		
		if(size() == 0) {
			return false;
		}
		
		LinkedListNode<T> temp = head.getNext();
		while(temp != tail) {
			T dat = temp.getData();
			if(!data.contains(dat)) {
				temp.getNext().setPrevious(temp.getPrevious());
				temp.getPrevious().setNext(temp.getNext());
				nodeCount--;
			}
			temp = temp.getNext();
		}
		
		modcount++;
		if(originalNodeCount == nodeCount) {
			return false;
		} else {
			return true;
		}
	}
	
	public Object set(int index, Object data) {
		LinkedListNode<T> temp = head;
		@SuppressWarnings("unchecked")
		LinkedListNode<T> toReplace = new LinkedListNode<T>(null, null, (T) data);
		int i = 0;
		while(temp.getNext() != null && i <= index) {
			temp = temp.getNext();
			i++;
		}
		temp.getPrevious().setNext(toReplace);
		if(temp.getNext() != null) temp.getNext().setPrevious(toReplace);
		modcount++;
		return temp;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public List<T> subList(int left, int right) {
		int index = 0;
		LinkedListNode<T> temp = head;
		if(right > nodeCount) {
			throw new IndexOutOfBoundsException("Index: " + right);
		}
		// If left is bigger than right, switch the values (as opposed to just not 
		// return anything at all)
		if(left > right) {
			int tempLeft = left;
			left = right;
			right = tempLeft;
		}
		while(temp.getNext() != null) {
			temp = temp.getNext();
			if(index <= left) break;
		}
		
		MyLinkedList<T> toReturn = new MyLinkedList<T>();
		toReturn.add(temp.getData());
		while(temp.getNext() != null && index <= right) {
			temp = temp.getNext();
			toReturn.add(temp.getData());
			index++;
		}
		
		return toReturn;
	}
	
	public Object[] toArray() {
		Object[] toReturn = new Object[nodeCount];
		LinkedListNode<T> temp = head;
		for(int i = 0; i < toReturn.length; i++) {
			toReturn[i] = temp;
			temp = temp.getNext();
		}
		
		return toReturn;
	}
	
	public Object[] toArray(Object[] arr) {
		int index = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == null) {
				index = i; 
				break;
			}
		}
		
		LinkedListNode<T> temp = head;
		for(int i = index; i < arr.length; i++) {
			arr[i] = temp;
			temp = temp.getNext();
		}
		
		return arr;
	}
	
	private class LinkedListIterator implements Iterator<T> {
		LinkedListNode<T> currentNode = head; 
		int expectedModCount;
		
		public LinkedListIterator() {
			expectedModCount = modcount;
		}

		@Override
		public boolean hasNext() {
			if(expectedModCount != modcount) {
				throw new ConcurrentModificationException();
			}
			return currentNode != tail;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T temp = (T) currentNode.getData();
			currentNode = currentNode.getNext();
			return temp;
		}
	}
	
}