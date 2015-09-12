package Project2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E> implements List<E> {
	private int size; // Number of elements in the elements array
	private Object[] elements; // Array of elements
	
	public MyArrayList() {
		elements = new Object[10]; // Initial size of 10;
		size = 0;
	}
	
	public boolean add(E e) {
		if(size < elements.length) {
			elements[size] = e;
		} else {
			// Allocate bigger Array...
		}
		++size;
		
		return false;
	}

	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
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

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	// Returns the number of elements in this list.
	public int size() {
		return size;
	}

	// Returns a view of the portion of this list 
	// between the specified fromIndex, inclusive, and toIndex, exclusive.
	public List<E> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException{
		int newSize = toIndex - fromIndex;
		MyArrayList<E> newList = new MyArrayList<E>();
		for(int i = fromIndex, k = 0; i < toIndex; i++, k++) {
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
