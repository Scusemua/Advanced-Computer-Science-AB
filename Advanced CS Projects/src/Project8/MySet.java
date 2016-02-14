package Project8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import Project7.*;

public class MySet<T extends Comparable<T>> extends MyBinarySearchTree<T> implements Set<T> {

	// Check to see if the Set contains the specified element
	public boolean contains(Object o) {
		if(get((T)o).equals(o)) {
			return true;
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	// Convert the Set to an array of objects
	public Object[] toArray() {
		ArrayList<Object> al = new ArrayList<Object>();
		inOrder(root, (List<T>) al);
		return al.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	// Remove the specified element from the Set
	public boolean remove(Object o) {
		remove((T) o);
		return true;
	}

	// Check to see if the Set contains all specified elements
	public boolean containsAll(Collection<?> c) {
		// Will use iterator for this
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

		
}
