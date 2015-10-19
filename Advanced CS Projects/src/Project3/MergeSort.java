package Project3;

import java.util.ArrayList;
import java.util.Comparator;
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
			merge(list, comp, begin, center - 1, center + 1, end);
		}
	}
	
	private static <T> void merge(List<T> list, Comparator<T> comp, int leftBegin, int rightBegin, int leftEnd, int rightEnd) {

		String[] listArr = (String[]) list.toArray();
		
		comp = new MyStringComparator();
		
		// The number of elements that should be in the final list
		int numberOfElements = rightEnd - leftBegin + 1;
		
		// The (to-be) sorted list
		ArrayList<T> finalList = new ArrayList<T>();
		
		// Starting index of the finalList ArrayList
		int finalListStart = 0;
		
		while(leftBegin <= leftEnd && rightBegin <= rightEnd) {
			if((comp.compare(listArr[leftBegin], listArr[rightBegin])) == -1) {
				 
			} else if ((comp.compare(listArr[leftBegin], listArr[rightBegin]) == 0)) {
				
			} else {
				
			}
		}
	}
	
	class MyStringComparator implements Comparator {
		private int compare(String s1, String s2) {
			return 0;
		}

		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
	class MyIntegerComparator implements Comparator {
		private int compare(Integer i1, Integer i2) {
			return 0;
		}

		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
