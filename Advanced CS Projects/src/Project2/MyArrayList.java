package Project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements List<E> {
	private int size; // Number of elements actually in the elements array
	private Object[] elements; // Array of elements
	private int modCount; // Used to check to see if the
	
	// Default capacity of elements array
	private final int DEFAULT_CAPACITY = 10;
	
	// array was modified whilst iterating through it
	
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
		elements = new Object[DEFAULT_CAPACITY]; // Initial size of 10;
		
		// Initial number of elements in the array is 0
		size = 0;
	}
	
	// Constructor that creates an ArrayList out of an existing collection
	// The "? extends E" in the < > means that the Collection is a collection of
	// any subclass of E
	public MyArrayList(Collection<? extends E> c) {
		
		// Initialize the elements array to to an array made out of the Collection c
		elements = c.toArray();
		
		// Set size to the number of elements now in the array
		size = elements.length;
		
		// length may be zero if the c collection is empty. 
		// If it is zero, we want to create the elements array with a 
		// initial capacity equal to ten instead of zero 
		if(elements.length == 0) {
			System.out.println("if statement executed");
			elements = new Object[DEFAULT_CAPACITY];
		}
		
		// Apparently, c.toArray(); may not correctly return an Array of objects (Bug 6260652)
		// Bug 6260652 is that, the Java documentation claims that collection.toArray()
		// is "identical in function" to collection.toArray(new Object[0]);
		// However, the implementation of this is not like that. If you create an array
		// of a subtype such as String[], its toArray() will return an array for the same time
		// as it uses clone() instead of Object[]
		// So if you later try to store a non-String in that Array, you'll get an ArrayStoreException 
		if(elements.getClass() != Object[].class) {	
			// In order to combat the bug, you can create a copy of the array using the
			// Arrays.copyOf(original array, new size, class that the Array will be)
			
			// If the length of the elements array is 0, we want to create the array with an 
			// initial capacity of 10. If it isn't 0, we create the array with the capacity equal to the\
			// number of elements in the c collection. The length may be zero if the c collection is empty. 
			if(elements.length == 0) {
				System.out.println("if statement executed");
				elements = Arrays.copyOf(elements,  DEFAULT_CAPACITY, Object[].class);
			} else {
				elements = Arrays.copyOf(elements,  size, Object[].class);
			}
		}
	}
	
	// Append an element to the array
	public boolean add(E e) {
		// Increment modCount
		modCount++;
		
		// Make sure there is room
		ensureCapacity(size + 1);
		
		// Add the new element to the Array
		elements[size] = e;
		
		// Increment the variable that stores the integer value of the number
		// of elements in the array
		size++;
		
		return true;
	}
	
	// Add an element to the array at a specific index
	public void add(int index, E element) {
		//Increment modCount
		modCount++;
		
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
	// The "? extends E" in the < > means that the Collection is a collection of
	// any subclass of E
	public boolean addAll(Collection<? extends E> c) {
		// Increment modCount
		modCount++;
		
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
			elements[size] = arr[i];
			size++;
		}
		
		return true;
	}

	// Add an existing collection to the ArrayList starting at a specific index
	// The "? extends E" in the < > means that the Collection is a collection of
	// any subclass of E
	public boolean addAll(int index, Collection<? extends E> c) {
		// Increment modCount
		modCount++;
		
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
		for(int i = 0; i < c.size(); i++, index++) {
			elements[index] = arr[i];
			size++;
		}
		
		return true;
	}

	// Removes all objects from the Array
	public void clear() {
		// Increment modCount
		modCount++;
		
		// Clear the elements array
		for(int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
		
		size = 0;
	}

	// Returns true if this list contains the specified element.
	public boolean contains(Object o) {
		for(int i = 0;i < size; i++) {
			
			// You cannot "dot" a null reference, so make sure that 
			// whatever value you're at in the array is not null
			if(elements[i] != null) {
				
				// Check to see if the current element in the array has the same value
				// as the object in question
				if(elements[i].equals(o)) {
					return true;
				}
			}
		}
		return false;
	}

	// The "? extends E" in the < > means that the Collection is a collection of any subclass of E
	public boolean containsAll(Collection<?> c) {
		
		// Create a BenIterator out of the iterator returned by c's iterator() method
		Iterator<?> topkek = c.iterator();
		
		// While there is another element in the c collection...
		while (topkek.hasNext()) {
			// Check to see if the elements array has the nexy element in the c collection
			// This is using MY contains method. If it doesn't contain the element 
			// in question, return false because that means that elements doesn't contain all
			// of the elements that are found in the c collection
			if (!contains(topkek.next())) {
				return false;
			}
		}
		
		// If you make it through the entire collection without returning false, that means
		// that all the elements of the c collection were found in the elements array, and
		// therefore, you return true 
		return true;
	}

	@SuppressWarnings("unchecked")
	// Return the element at the specified position
	public E get(int index) {
		
		// Ensure the index is legal
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Illegal index: " + index);
		}
		
		// If there is indeed something at that index, return it
		// Otherwise, return null
		if(elements[index] != null) {
			return (E) elements[index];
		} else {
			return null;
		}
	}

	// Return the index of the specified object
	// If the object is not in the array, return -1
	public int indexOf(Object o) {
		
		// Look through the array for the specified object
		for(int i = 0; i < elements.length; i++) {
			
			// Make sure you don't try to "dot" a null reference
			if(elements[i] != null) {
				// If the object is found, return the index
				if(elements[i].equals(o)) return i;
			}
		}
		
		// If the object isn't found, return -1
		return -1;
	}

	// Check whether or not the array is empty
	public boolean isEmpty() {
		return (size == 0);
	}

	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		return new BenIterator<E>();
	}

	public int lastIndexOf(Object o) {
		
		// Hunt backwards through the array for the object
		for(int i = elements.length - 1; i >= 0; i--) {
			
			// Make sure you don't "dot" a null reference
			if(elements[i] != null) {
				if(elements[i].equals(o)) return i;
			}		
		}
		
		// Return -1 if you don't find it
		return -1;
	}

	@SuppressWarnings("unchecked")
	// Return a new BenIterator
	public ListIterator<E> listIterator() {
		return new BenIterator<E>();
	}

	// Return a new BenIterator at a specific index
	@SuppressWarnings("unchecked")
	public ListIterator<E> listIterator(int index) {
		return new BenIterator<E>();
	}

	public boolean remove(Object o) {
		// Increment modCount
		modCount++;
		int index = -1; // Variable to hold index of object we want to remove
		
		// Walk through array looking for the object to remove
		for(int i = 0; i < size; i++) {
			if(elements[i] == o) {
				index = i; // Set the index
			}
		}
		
		// Shift array if we have a valid index
		if(index != -1) {
			for(int i = index; i < size - 1; i++) {
				elements[i] = elements[i+1];
			}
		}
		
		// Decrement size because we removed an object
		size--;
		
		// If the index was successfully changed from -1,
		// that means that the object was found and presumably removed
		return(index != -1);
	}

	public E remove(int index) {
		// Increment modCount
		modCount++;
		
		// Ensure the index is valid
		if(!(index >= 0) || !(index < size)) {
			throw new NoSuchElementException();
		}
		
		// Save the object that's being removed from the array so we
		// can return it at the end of the method
		@SuppressWarnings("unchecked")
		E removed = (E) elements[index];
		
		for(int i = index; i < size - 1; i++) {
			elements[i] = elements[i+1];
		}
		
		// Decrement size because we removed an object
		size--;
		
		// Return the removed object
		return removed;
	}

	// Remove all the objects in the given collection from the array
	public boolean removeAll(Collection<?> c) {
		// Increment modCount
		modCount++;
		
		// Create an array out of the collection
		Object[] cArray = c.toArray();
		
		// Ensure that the array was created correctly (see previous comments
		// for explanation of why we do this)
		if(cArray.getClass() != Object[].class) {
			cArray = Arrays.copyOf(cArray,  size, Object[].class);
		}
		
		// Outer loop walks through elements array
		// Middle loop walks through the array cArray
		// Inner loop removes objects from elements array
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < c.size(); j++) {
				if(elements[i] == cArray[j]) {
					for(int k = i; k < size - 1; k++) {
						elements[k] = elements[k+1];
					}
				}
			}
		}
		
		// Decrement size by the amount of objects we removed
		size -= cArray.length;
		
		return true;
	}

	// The "? extends E" in the < > means that the Collection is a collection of any subclass of E
	// Gets rid of everything in the Array aside from what is in the given collection
	public boolean retainAll(Collection<?> c) {
		// Create new array to hold only the specified elements
		Object[] newElements = new Object[c.size()];
		
		// Current index of the newElements array. Used to
		// place new elements into the correct position in the newElements array.
		int newIndex = 0;
		
		// Walk through the elements array, adding specific elements to the newElements array
		for(int i = 0;i < elements.length; i++) {
			if(c.contains(elements[i])) {
				newElements[newIndex] = elements[i]; // Add element to the Array
				newIndex++;
			}
		}
		
		// Reassign the elements array to the newElements array, effectively
		// getting rid of all elements that were not in the c collection
		elements = newElements;
		
		// CHANGE SIZE BECAUSE YOU GOT RID OF STUFF PROBABLY
		size = elements.length;
		
		return true;
	}

	public E set(int index, E element) {
		// Increment modCount
		modCount++;
		
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
			// Grab the correct element and cast it to E
			E element = (E) elements[i];
			// Add the specified element at the correct index
			newList.add(k,element);
		}
		return newList;
	}

	// Returns an array containing all of the elements 
	// in this list in proper sequence (from first to last element).
	public Object[] toArray() {
		// First index that has a null value
		int firstIndex = -1;
		
		// Look for where null values start
		for(int i = 0; i < elements.length; i++) {
			if(elements[i] == null) {
				firstIndex = i;
				break;
			}
		}
		
		// If there were never any null values, just return elements 
		if(firstIndex == -1) {
			return elements;
		}
		
		// Otherwise, create a new Array that only consists of actual values, not null values
		Object[] toReturn = new Object[firstIndex];
		for(int i = 0; i < firstIndex; i++) {
			toReturn[i] = elements[i];
		}
		return toReturn;
	}

	// Returns an array containing all of the elements in this list in 
	// proper sequence (from first to last element); 
	// the runtime type of the returned array is that of the specified array.
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		// Create a new array to be returned
		Object[] newArr = new Object[size];
		
		// If a is smaller than the amount of elements in the elements array
		// Return a bigger array with all of the elements of the type of a
		if(a.length < size) {
			return (T[]) Arrays.copyOf(elements, size, a.getClass());
		}
		
		// Iterate through elements. Cast each element in the array
		// to the runtime type and store it in the newArr
		System.arraycopy(elements, 0, a, 0, size);
		
		// Return a runtime type version of the new array
		return (T[]) newArr;
	}
	
	public String toString() {
		String toReturn = "";
		for(int i = 0; i < size; i++) {
			toReturn += elements[i] + " ";
		}
		return toReturn;
	}
	
	/**
	 * Internal method for testing
	 * @return elements.length
	 */
	private int getLength() {
		return elements.length;
	}
	
	public static void main(String[] args) {
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test One");
		
		// Test: Null Constructor
		MyArrayList<String> arr0 = new MyArrayList<String>();
		if(arr0.size() == 0) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Two");
		
		// Test: Construct with empty collection
		ArrayList<String> toAdd1 = new ArrayList<String>(); 
		MyArrayList<String> arr1 = new MyArrayList<String>(toAdd1);
		if(arr1.size() == 0) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Three");
		
		// Test: Constructor with Specific Initial Capacity 
		MyArrayList<String> arr2 = new MyArrayList<String>(25);
		if(arr2.getLength() == 25) { 
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Four");
		
		// Test: Add at end to empty MyArrayList
		MyArrayList<Integer> arr3 = new MyArrayList<Integer>();
		arr3.add(3);
		if(arr3.get(0).equals(3)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Five");
		
		// Test: Add at end to non-empty MyArrayList
		arr3.add(4);
		if(arr3.get(1).equals(4)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Six");
		
		// Test: Add at position
		MyArrayList<Integer> arr4 = new MyArrayList<Integer>();
		arr4.add(1); // Add to empty
		arr4.add(0,2); // Add at position 0 
		arr4.add(1,3); // Add at position 1
		if(arr4.get(0).equals(2) && arr4.get(1).equals(3) && arr4.get(2).equals(1)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Seven");
		
		// Test: Add all for empty collection
		ArrayList<String> toAdd2 = new ArrayList<String>(); 
		toAdd2.add("three"); 
		toAdd2.add("four"); 
		toAdd2.add("five"); 
		arr1.addAll(toAdd2); 
		for(Object o: arr1) {
			System.out.println(o);
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Eight");
		
		// Test: Add all at position
		MyArrayList<String> arr5 = new MyArrayList<String>();
		ArrayList<String> toAdd3 = new ArrayList<String>();
		toAdd3.add("six");
		toAdd3.add("seven");
		toAdd3.add("eight");
		arr5.add("one");
		arr5.add("two");
		arr5.addAll(0, toAdd2);
		arr5.addAll(2, toAdd3);
		for(Object o: arr5) {
			System.out.println(o);
		} 
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Nine");
		
		// Test: clear
		arr5.clear();
		if(arr5.size() == 0) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Ten");
		
		// Test: contains 
		MyArrayList<Integer> arr6 = new MyArrayList<Integer>();
		boolean pass1, pass2, pass3, pass4, pass5 = false;
		// Find in empty
		if(arr6.contains(1) == false) {
			pass1 = true;
		} else {
			pass1 = false;
		}
		arr6.add(1);
		// Find at front
		if(arr6.contains(1)) {
			pass2 = true;
		} else {
			pass2 = false;
		}
		arr6.add(0, 2);
		// Find at end
		if(arr6.contains(1)) {
			pass3 = true;
		} else {
			pass3 = false;
		}
		// Find middle
		arr6.add(3);
		if(arr6.contains(1)) {
			pass4 = true;
		} else {
			pass4 = false;
		}
		// Looking for something that isn't there in a non-empty list
		if(!arr6.contains(12419)) {
			pass5 = true;
		} else {
			pass5 = false;
		}
		
		if(pass1 && pass2 && pass3 && pass4 && pass5) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Test Eleven");
		
		// Get from outsite the Array
		MyArrayList<Double> listOfDoubles = new MyArrayList<Double>(15);
		listOfDoubles.add(2.4);
		listOfDoubles.add(3.4);
		try {
			System.out.println(listOfDoubles.get(5));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("pass");
		}
		try {
			System.out.println(listOfDoubles.get(20));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("pass");
		}
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "hiding" })
	private class BenIterator<E> implements ListIterator {
		private int lastRet = -1; // Last returned variable
		private int cursor = 0; // Iterator's current position 
		int expectedModCount = modCount; // What the modCount is supposed to be
		
		public void add(Object e) {
			System.out.println("What a dank meme you have there, bro!");
		}

		// Checks to see if there are more elements in the array
		// If the current position of cursor doesn't equal size,
		// that means there is at least one more element in the array
		public boolean hasNext() {
			return cursor != size;
		}

		// Checks to see if there are any elements before the current position in the array. 
		// If cursor is not equal to zero, that means that there are indeed elements 
		// left before the cursor position in the array.
		public boolean hasPrevious() {
			return cursor != 0;
		}

		// Return the object at the location of cursor 
		// Increment cursor to move it to the next object
		@SuppressWarnings("unchecked")
		public Object next() {
			// Make sure the array hasn't been modified whilst iterating
			checkIfModified();
			
			// Ensure that the cursor is where it is supposed to be, in that there is an element
			// at the location of the cursor variable
			if (cursor >= size) {
				throw new NoSuchElementException();
			}
			
			// Set the last returned value to the location of cursor
			lastRet = cursor;
			
			// Increment cursor
			cursor++;
		
			// Return the element at the location of lastRet 
			// (what cursor was at before it was incremented) 
			return (E) elements[lastRet];
		}

		// Return the index of what would be the next returned element in the array
		public int nextIndex() {
			return cursor;
		}

		// Return the previous element compared to cursor
		@SuppressWarnings("unchecked")
		public Object previous() {
			checkIfModified(); // Ensure the array hasn't been modified whilst iterating
			
			// Ensure that the index we want to return a variable at is valid
			if (cursor - 1 < 0) {
				throw new NoSuchElementException();
			}
			
			// Move the cursor backwards one
			cursor = cursor - 1;
			
			// Set the last-returned position to be at the index we're gonna return from
			lastRet = cursor;
			
			// Return the correct element
			return (E) elements[lastRet];
		}

		public int previousIndex() {
			return cursor - 1;
		}

		public void remove() {
			
		}

		public void set(Object e) {
			System.out.println("What base could this be in?");
			System.out.println("2500C23635B842A279C82947766208156714005C3" +
							   "B6877CBC79C70CABBB9148658493621398644A81C389AC221A23802C7123315");
		}
		
		private void checkIfModified() {
			if(expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
		}		
	}

}