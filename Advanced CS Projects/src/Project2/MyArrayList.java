package Project2;

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
		elements = new Object[10]; // Initial size of 10;
		
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
			elements = Arrays.copyOf(elements,  size, Object[].class);
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
		for(int i = 0; i < c.size(); i++) {
			elements[size + 1] = arr[i];
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