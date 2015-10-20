package Project3;

/**
 * To do:
 * Use an ArrayDeque instead of a LinkedList
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSort {
	
	private static <T> void mergeSort(List<T> list, Comparator<T> comp, int begin, int end) {
		
		// Center of the list
		int center = 0;
		
		// If the array hasn't been "split up" into size-one blocks, split it up again
		if(!(begin == end)) {
			
			// The index where the split will occur
			center = (begin + end) / 2;
			
			// Left piece
			mergeSort(list, comp, begin, center);
			
			// Right piece
			mergeSort(list, comp, center + 1, end);
		
			// Merge the results
			merge(list, comp, begin, center, center + 1, end);
		}
	}
	
	private static <T> void merge(List<T> list, Comparator<T> comp, int leftBegin, int leftEnd, int rightBegin, int rightEnd) {
		// Left and right lists for the left and right halves of List<T> list
		LinkedList<T> leftList = new LinkedList<T>();
		LinkedList<T> rightList = new LinkedList<T>();
		
		// Populate left list
		for(int i = leftBegin; i <= leftEnd; i++) {
			leftList.add(list.get(i));
		}
		
		// Populate right list
		for(int i = rightBegin; i <= rightEnd; i++) {
			rightList.add(list.get(i));
		}
		
		// Used to help specify the index
		// to set elements in the List<T> list
		int iteration = 0;
		
		// Keep going until one of the lists is empty
		while(leftList.size() != 0 && rightList.size() != 0) {
		
			// If the left object is less than or equal to the right object
			if(comp.compare(leftList.getFirst(), rightList.getFirst()) <= 0) {
				
				// Grab the first element from the leftList LinkedList
				// and put it in the correct location in the List<T> list.
				// After that, remove the first item from the leftList LinkedList
				// so that next time, we grab a new item when we grab from the front
				list.set(leftBegin + iteration, leftList.get(0));
				leftList.removeFirst();
			} else {
				
				// Do the same thing except with the rightList LinkedList
				list.set(leftBegin + iteration, rightList.get(0));
				rightList.removeFirst();
			}
			
			// Increment the iteration variable 
			iteration++;
		}
		
		// If the leftList is empty, we're going to take all the elements from the rightList 
		// and put them into the List<T> list until we've added all remaining elements
		// We don't remove elements after adding them here because we no longer need to. Removing
		// elements would just waste time at this point. 
		
		// If the leftList isn't empty, it's the rightList that is empty. So we take all the 
		// elements remaining in the leftList and put them into the List<T> list. Again, we do 
		// not remove elements anymore. We just use a for-loop until all elements are added.
		if(leftList.size() == 0) {
			for(int i = 0; i < rightList.size(); i++) {
				list.set(leftBegin + iteration, rightList.get(i));
				iteration++;
			}
		} else {
			for(int i = 0; i < leftList.size(); i++) {
				list.set(leftBegin + iteration, leftList.get(i));
				iteration++;
			}
		}
	}
	
	// Main method 
	public static void main(String[] args) {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		intList.add(1);
		intList.add(4);
		intList.add(2);
		intList.add(3);
		intList.add(5);
		intList.add(9);
		intList.add(134);
		intList.add(12);
		intList.add(13);
		intList.add(11);
		intList.add(8);
		
		mergeSort(intList, new MyIntegerComparator(), 0, intList.size() - 1);
		
		for(int i = 0; i < intList.size(); i++) {
			System.out.println(intList.get(i));
		}
		
		ArrayList<String> stringList = new ArrayList<String>();
		
		stringList.add("topkek");
		stringList.add("doritos");
		stringList.add("mountain dew");
		stringList.add("dank memes");
		stringList.add("pepe the frog");
		stringList.add("comp sci rocks");
		
		mergeSort(stringList, new MyStringComparator(), 0, stringList.size() - 1);
		
		for(int i = 0; i < stringList.size(); i++) {
			System.out.println(stringList.get(i));
		}
	}
}

	class MyStringComparator implements Comparator<String> {
		
		// Compare Strings (returns -1, 0, or 1)
		// -1 means less, 0 means equal to, 1 means greater than
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	}

	class MyIntegerComparator implements Comparator<Integer> {
		
		// Compare Integers (returns -1, 0, or 1) 
		// -1 means less, 0 means equal to, 1 means greater than
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
	}
