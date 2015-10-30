package Project3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ArrayDeque;
import java.util.List;

public class MergeSort {
	
	private static <T> void mergeSort(List<T> list, Comparator<T> comp, int begin, int end) {
		
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
		// Using ArrayDeque, removing the first is not O(n) because you don't have to shift
		// which is what it is in an array. LinkedList is also possible but iterating thru LinkedList
		// is slower than iterating thru ArrayDeque (not that I'm iterating but if I wanted to iterate, it would be slower)
		// Give it a size so it doesn't have to expand it during runtime 
		ArrayDeque<T> leftList = new ArrayDeque<T>((list.size() / 2) + 1);
		ArrayDeque<T> rightList = new ArrayDeque<T>((list.size() / 2) + 1);
		
		System.out.println("*******************************************");
		
		// Populate left list
		for(int i = leftBegin; i <= leftEnd; i++) {
			leftList.add(list.get(i));
		}
		
		// Populate right list
		for(int i = rightBegin; i <= rightEnd; i++) {
			rightList.add(list.get(i));
		}
		
		System.out.println("");
		
		System.out.println("Left List: " + leftList.size());
		for(int i = 0; i < leftList.size(); i++) {
			//System.out.print(leftList.get(i)+ " ");
		}
		
		System.out.println("");
		
		System.out.println("Right List: " + rightList.size());
		for(int i = 0; i < rightList.size(); i++) {
			//System.out.print(rightList.get(i)+ " ");
		}
		
		System.out.println("");
		
		int iteration = 0;
		
		// Keep going until one of the lists is empty
		while(leftList.size() != 0 && rightList.size() != 0) {
			// If the left object is less than or equal to the right object
			if(comp.compare(leftList.getFirst(), rightList.getFirst()) <= 0) {
				list.set(leftBegin + iteration, leftList.getFirst());
				leftList.removeFirst();
			} else {
				list.set(leftBegin + iteration, rightList.getFirst());
				rightList.removeFirst();
			}
			iteration++;
		}
		
		System.out.println("");
		
		System.out.println("Left List 2: " + leftList.size());
		for(int i = 0; i < leftList.size(); i++) {
			//System.out.print(leftList.get(i)+ " ");
		}
		
		System.out.println("");
		
		System.out.println("Right List 2: " + rightList.size());
		for(int i = 0; i < rightList.size(); i++) {
			//System.out.print(rightList.get(i)+ " ");
		}
		
		System.out.println("");
		
		int leftListSize = leftList.size();
		int rightListSize = rightList.size();
		
		if(leftListSize == 0) {
			for(int i = 0; i < rightListSize; i++) {
				System.out.println("Setting list at position " + (leftBegin + iteration) + " the value of " + rightList.getFirst() + " from rightList");
				list.set(leftBegin + iteration, rightList.getFirst());
				rightList.removeFirst();
				iteration++;
			}
		} else {
			for(int i = 0; i < leftListSize; i++) {
				System.out.println("Setting list at position " + (leftBegin + iteration) + " the value of " + leftList.getFirst() + " from leftList");
				list.set(leftBegin + iteration, leftList.getFirst());
				leftList.removeFirst();
				iteration++;
			}
		}
		
		System.out.println();
		System.out.println("Resulting List: " + list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		
		intList.add(1);
		intList.add(3);
		intList.add(5);
		intList.add(4);
		intList.add(2);
		intList.add(6);
		intList.add(7);
		
		/* intList.add(14);
		intList.add(135);
		intList.add(1);
		
		intList.add(7);
		intList.add(13);
		intList.add(134);
		intList.add(5);
		
		intList.add(8);
		intList.add(164);
		intList.add(212);
		intList.add(1); */
		
		/* intList.add(1);
		intList.add(4);
		intList.add(2);
		//intList.add(11);
		intList.add(3);
		intList.add(5);
		intList.add(9);
		intList.add(134);
		intList.add(12);
		//intList.add(11);
		intList.add(13);
		intList.add(8);
		intList.add(164);
		intList.add(212);
		intList.add(1); */
		
		mergeSort(intList, new MyIntegerComparator(), 0, intList.size() - 1 );
		
		System.out.println("\n\n\n\nEnd Int Sort: ");
		for(int i = 0; i < intList.size(); i++) {
			System.out.println(intList.get(i));
		}
		
		ArrayList<String> stringList = new ArrayList<String>();
		
		/* stringList.add("A");
		stringList.add("C");
		stringList.add("B");
		stringList.add("D"); */
		
		/* stringList.add("A");
		stringList.add("B");
		stringList.add("C");
		stringList.add("D"); */
		
		/* stringList.add("A");
		stringList.add("B");
		stringList.add("C");
		stringList.add("D");
		stringList.add("E");
		stringList.add("F");
		stringList.add("A");
		stringList.add("H"); */
		
		stringList.add("A");
		stringList.add("B");
		stringList.add("C");
		stringList.add("D");
		stringList.add("E");
		stringList.add("F");
		stringList.add("A");
		stringList.add("H");
		stringList.add("G");
		
		mergeSort(stringList, new MyStringComparator(), 0, stringList.size() - 1 );
		System.out.println("\n\n\n\nEnd String Sort: ");
		for(int i = 0; i < stringList.size(); i++) {
			System.out.println(stringList.get(i));
		}
		
	}
}

	class MyStringComparator implements Comparator<String> {
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	}

	class MyIntegerComparator implements Comparator<Integer> {
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
	}
