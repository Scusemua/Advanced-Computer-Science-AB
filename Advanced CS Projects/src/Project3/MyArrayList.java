package Project3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {
	private int size; // Number of elements actually in the elements array
	private Object[] elements; // Array of elements
	
	// Constructor that sets the initial capacity to whatever
	// the user wants it to be
	public MyArrayList(int initialCapacity) {
		
		// If the user tries to create an ArrayList with a capacity less than 0
		// throw a IllegalArgumentException 
		if(initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
		
		// Create the array with the specified capacity
		elements = new Object[initialCapacity];
	}
	
	// Default constructor has initial size of 10
	public MyArrayList() {
		
		// Create array
		elements = new Object[10]; // Initial size of 10;
		
		// Initial number of elements in the array is 0
		size = 0;
	}
	
	// Constructor that creates an ArrayList out of an existing collection
	public MyArrayList(Collection<? extends E> c) {
		
		// Initialize the elements array to to an array made out of the Collection c
		elements = c.toArray();
		
		// Set size to the number of elements now in the array
		size = elements.length;
		
		// Apparently, c.toArray(); may not correctly return an Array of objects (Bug 6260652) so
		// you have to do this to ensure it works properly
		if(elements.getClass() != Object[].class) {
			
			// Fix it
			elements = Arrays.copyOf(elements,  size, Object[].class);
		}
	}
	
	// Append an element to the array
	public boolean add(E e) {
		
		// Make sure there is room
		ensureCapacity(size + 1);
		
		// Make sure there is room left in the array
		if(size + 1 > elements.length) {
			
			// If there is, add the new element to the array
			elements[size + 1] = e;
		}
		
		return true;
	}
	
	// Add an element to the array at a specific index
	public void add(int index, E element) {
		
		// Check to see if the index is okay
		if (index > size + 1 || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
		
		// Ensure there is room
		ensureCapacity(size + 1);
		
		// Shift everything forward around the index
		System.arraycopy(elements, index, elements, index + 1, size - index);

		// Place the new element at the correct spot
		elements[index] = element;
		
		// Increment size
		size++;
	}
	
	// Ensure there is space in the array
	// If there isn't, create space by making the array bigger
	public void ensureCapacity(int minCapacity) {
		
		// Store the old capacity of the elements array
		int oldCapacity = elements.length;
		
		// Check to see if room needs to be made  
		if(minCapacity < oldCapacity) {
			
			// Set the new capacity to be 1.5x bigger than the old capacity 
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			
			// Check to see if the 1.5x greater capacity is bigger than the 
			// requested new capacity
			if (newCapacity < minCapacity) {
				
				// If it isn't, set the new capacity to the requested new capacity
				newCapacity = minCapacity;
			}
			
			// Copy the elements array into a bigger version of itself
			elements = Arrays.copyOf(elements, newCapacity);
		}
	}

	// Add an existing collection to the ArrayList
	public boolean addAll(Collection<? extends E> c) {
		ensureCapacity(c.size() + 1); // Make sure there is room for the entire collection
		
		// Create an array out of the collection
		Object[] arr = c.toArray();
		
		// Make sure array arr was correctly created
		if(arr.getClass() != Object[].class) {
			
			// Fix it
			arr = Arrays.copyOf(arr,  size, Object[].class);
		}
		
		// Add all of the elements of the collection to the elements array
		for(int i = 0; i < c.size(); i++) {
			elements[size + 1] = arr[i];
			size++;
		}
		
		return true;
	}

	// Add an existing collection to the ArrayList starting at a specific index
	public boolean addAll(int index, Collection<? extends E> c) {
		
		// Make sure that the index is valid
		if(index > size + 1 || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
		
		// Ensure there is room in the elements array
		ensureCapacity(c.size() + 1);
		
		// Create an array out of the collection
		Object[] arr = c.toArray();
		
		// Ensure array arr was created correctly
		if(arr.getClass() != Object[].class) {
			
			// Fix it
			arr = Arrays.copyOf(arr,  size, Object[].class);
		}
		
		// Shift everything forward the size of the collection around the index
		System.arraycopy(elements, index, elements, index + c.size(), size - index);
		
		// Add the collection to the array
		for(int i = 0; i < c.size(); i++) {
			elements[size + 1] = arr[i];
			size++;
		}
		
		return true;
	}

	public void clear() {
		
		// Clear the elements array
		for(int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
		
		size = 0;
	}

	// Returns true if this list contains the specified element.
	public boolean contains(Object o) {
		for(int i = 0;i < size; i++) {
			if(elements[i] == o) {
				return true;
			}
		}
		return false;
	}

	// Gonna have to use an iterator 
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	// Return the element at the specified position
	public E get(int index) {
		
		// Ensure the index is legal
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
		
		// If there is indeed something at that index, return it
		// Otherwise, freak out because there should be something there
		if(elements[index] != null) {
			return (E) elements[index];
		} else {
			System.out.println("THERE IS NOTHING THERE SOMEHOW?!?!??!");
			System.out.println("THE MEMES ARE NOT DANK!!1");
			System.out.println("ABORT, ABORT, ABORT!");
			
			System.exit(0);
		}
		
		// Can't really get here
		return null;
	}

	// Return the index of the specified object
	// If the object is not in the array, return -1
	public int indexOf(Object o) {
		
		// Look through the array for the specified object
		for(int i = 0; i < elements.length; i++) {
			
			// If the object is found, return the index
			if(elements[i].equals(o)) return i;
		}
		
		// If the object isn't found, return -1
		return -1;
	}

	// Check whether or not the array is empty
	public boolean isEmpty() {
		return (size == 0);
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public int lastIndexOf(Object o) {
		
		// Hunt backwards through the array for the object
		for(int i = elements.length - 1; i >= 0; i--) {
			if(elements[i].equals(o)) return i;
		}
		
		// Return -1 if you don't find it
		return -1;
	}

	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public E set(int index, E element) {
		// Ensure the index is legal
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
		
		@SuppressWarnings("unchecked")
		// Store the element to be replaced
		E replaced = (E) elements[index];
		
		// Replace the element in the array
		elements[index] = element;
		
		// Return the replaced element
		return replaced;
	}

	// Returns the number of elements in this list.
	public int size() {
		return size;
	}

	// Returns a view of the portion of this list 
	// between the specified fromIndex, inclusive, and toIndex, exclusive.
	public List<E> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException{
		
		// Create the ArrayList to return
		MyArrayList<E> newList = new MyArrayList<E>();
		
		// Go through the elements array, adding elements from the specified range
		// to the ArrayList to be returned
		for(int i = fromIndex, k = 0; i < toIndex; i++, k++) {
			@SuppressWarnings("unchecked")
			E element = (E) elements[i];
			newList.add(k,element);
		}
		return newList;
	}

	// Returns an array containing all of the elements 
	// in this list in proper sequence (from first to last element).
	public Object[] toArray() {
		return elements;
	}

	// Returns an array containing all of the elements in this list in 
	// proper sequence (from first to last element); 
	// the runtime type of the returned array is that of the specified array.
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
}
