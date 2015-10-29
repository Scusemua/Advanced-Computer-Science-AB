package Project4;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List {
	// Head node
	private LinkedListNode<T> head;
	
	// Number of nodes
	private int nodeCount = 1;
	
	// Constructor 
	public MyLinkedList() {
		// Create the new MyLinkedList. The head does not hold any data,
		// nor does it hold a pointer to a previous node. It also currently does
		// not hold a pointer to the next node as the head node is currently the 
		// only node in the MyLinkedList
	}
	
	// Re
	public boolean addFirst(T data) {
		head = new LinkedListNode<T>(null, null, data);
		return true;
	}
	
	// Append a node to the end of the MyLinkedList
	// If the head node hasn't been initialized, initialize it
	public boolean add(Object data) {
		@SuppressWarnings("unchecked")
		
		// Cast the Object data to a T
		T dataCasted = (T) data;
		
		// This node will be used to get to the end of the MyLinkedList
		LinkedListNode<T> temp = head;
		
		// This will be the node that is added to the end of the MyLinkedList
		LinkedListNode<T> toAdd = new LinkedListNode<T>(null, null, dataCasted);
		
		// If this is the first time we're adding something, just 
		// set the first node to contain the data that we want
		if(head == null) {
			addFirst(dataCasted);
		} else {
			// Go from node to node until you reach the last node in this list
			while(temp.getNext() != null) { 
				temp = temp.getNext();
			}
			// Have the last node in the MyLinkedList point to the node that we're adding
			temp.setNext(toAdd);
			
			// The node that we're adding must have a previous pointer pointing to what was
			// the last node in the MyLinkedList, so set the previous pointer to temp
			toAdd.setPrevious(temp);
			
			// Increment the nodeCount variable, as a node was added to the list
			nodeCount++;
		}
		return true;
	}
	
	// Add a new node at a specific index
	public void add(int index, Object data) {
		
		@SuppressWarnings("unchecked")
		T dataCasted = (T) data;
		
		// If they're trying to add a node to the list when the head node
		// hasn't even been initialized, throw an IndexOutOfBoundsException
		// If the head node hasn't been initialized BUT they're trying to add at
		// index 0, initialize the head node to contain the data specified
		if(head == null && index != 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		} else if (head == null & index == 0) {
			addFirst(dataCasted);
		}
		
		// This node will be used to get to the correct node in the list
		LinkedListNode<T> temp = head;
		
		// This will be the node added, assuming all requirements are met
		LinkedListNode<T> toAdd = new LinkedListNode<T>(null, null, dataCasted);
		
		// Get to the correct node in the MyLinkedList
		// If the index is larger than the number of nodes,
		// throw an IndexOutOfBoundsException 
		for(int i = 0; i < index; i++) {
			if(temp.getNext() != null) {
				temp = head.getNext();
			} else {
				throw new IndexOutOfBoundsException("Illegal index: " + index);
			}
		}
		
		// The pointer before the correct index must now point to the new node
		temp.getPrevious().setNext(toAdd);
		
		// The node at the correct index is going to be shifted to the right,
		// so now it's previous pointer will be the node that is being added
		temp.setPrevious(toAdd);
		
		// Increment the nodeCount variable, as a node was added to the list
		nodeCount++;
	}
	
	@SuppressWarnings("unchecked")
	public boolean addAll(Collection data) {
		
		return true;
	}
	
	public boolean addAll(int arg0, Collection arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// Clear all the nodes
	public void clear() {
		head.setNext(null);
	}
	
	public boolean contains(Object obj) {
		
		// If the list is not empty
		if(head != null) {
			System.out.println("This list is empty so it does not contain the object in question.");
			return false;
		}
		
		// Used to go thru the list
		LinkedListNode<T> temp = head;
		
		// Look at each node
		while(temp.getNext() != null) {
			// Check to see if the node's data is the same as the object
			if(temp.getData().equals(obj)) {
				return true;
			}
			
			temp = temp.getNext();
		}
		
		// If it wasn't found
		return false;
	}
	
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Object get(int index) {
		LinkedListNode<T> temp;
		if(index > nodeCount) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		} else {
			temp = head;
			for(int i = 0; i <= index; i++) {
				temp = temp.getNext();
			}
		}
		
		return (Object) temp.getData();
	}
	
	// Return the index of the specified object (assuming it's in the list)
	public int indexOf(Object obj) {
		// If the list is not empty
		if(head != null) {
			System.out.println("This list is empty so it does not contain the object in question.");
			return -1;
		}
		
		// Used to go thru the list
		LinkedListNode<T> temp = head;
		
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
		
		return false;
	}
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
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
		LinkedListNode<T> temp = head;
		int index = 0;
		while(temp.getNext() != null) {
			temp = temp.getNext();
			index++;
			if(temp.getData().equals(data)) {
				System.out.println("Node removed at index " + index);
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				nodeCount--;
				return true;
			}
		}
		return false;
	}
	public Object remove(int i) {
		LinkedListNode<T> temp = head;
		int index = 0;
		while(temp.getNext() != null) {
			temp = temp.getNext();
			index++;
			if(index == i) {
				System.out.println("Node removed at index " + index);
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				nodeCount--;
			}
		}
		return temp;
	}
	
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Object set(int index, Object data) {
		LinkedListNode<T> temp = head;
		LinkedListNode<T> toReplace = new LinkedListNode<T>(null, null, (T) data);
		int i = 0;
		while(temp.getNext() != null && i <= index) {
			temp = temp.getNext();
			i++;
		}
		temp.getPrevious().setNext(toReplace);
		if(temp.getNext() != null) temp.getNext().setPrevious(toReplace);
		return temp;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public List subList(int left, int right) {
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
		toReturn.add(temp);
		while(temp.getNext() != null && index <= right) {
			temp = temp.getNext();
			toReturn.add(temp);
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
	
}

/*


// This node will be used to get to the end of the list
		LinkedListNode<T> temp;
		
		// Used to add data to the list
		@SuppressWarnings("unused")
		LinkedListNode<T> toAdd;
		
		// Convert the data collection to an array so we can get each element
		Object[] dataArr = data.toArray();
		
		// Has anything been added to the list yet?
		if(head == null) {
			
			// Ensure the data collection actually has elements
			if(data.isEmpty()) {
				return false; 
			} else {
				head = new LinkedListNode<T>(null, null, (T) dataArr[0]);
			}
			
			temp = head;
			
			for(int i = 1; i < dataArr.length; i++) {
				toAdd = new LinkedListNode<T>(null, temp, (T) dataArr[1]);
				temp.setNext(toAdd);
				temp = toAdd;
			}
			
		}
		
		// Initialize temp since it was only initialized within the if statement 
		temp = head;
		
		// Get to the correct node
		while(temp.getNext() != null) { 
			temp = temp.getNext();
		}
		
		return false;
		
*/
