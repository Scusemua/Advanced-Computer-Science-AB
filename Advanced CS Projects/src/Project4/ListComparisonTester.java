package Project4;

import Project2.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListComparisonTester {
	
	public ListComparisonTester() {
		
	}
	
	public static void main(String[] args) {
		// MyArrayList<Integer> mArrList = new MyArrayList<Integer>();
		// MyLinkedList<Integer> mLinkedList = new MyLinkedList<Integer>();
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		LinkedList<Integer> linkList = new LinkedList<Integer>();
		
		// Warming up the JVM may or may not make a difference on my computer as it is already rather fast
		for(int i = 0; i < 45000; i++) {
			System.out.println("Warming up the JVM... " + i);
		}
		
		long arrayListAddStartTime = System.currentTimeMillis();
		int initialAmount = 1000000;
		
		for(int i = 0; i < initialAmount; i++) {
			// mArrList.add(i);
			arrList.add(i);
		}
		
		long arrayListAddEndTime = System.currentTimeMillis();
		long arrayListEllapsedAddTime = arrayListAddEndTime - arrayListAddStartTime;
		
		System.out.println("Time taken to add at the end " + initialAmount + " integers to the ArrayList: " + arrayListEllapsedAddTime + " milliseconds.");
		// Average of 5 times was 17 milliseconds 
		
		long linkedListAddStartTime = System.currentTimeMillis();
		
		for(int i = 0; i < initialAmount; i++) {
			// mArrList.add(i);
			linkList.add(i);
		}
		
		long linkedListAddEndTime = System.currentTimeMillis();
		long linkedListEllapsedAddTime = linkedListAddEndTime - linkedListAddStartTime;
		
		System.out.println("Time taken to add at the end " + initialAmount + " integers to the LinkedList: " + linkedListEllapsedAddTime + " milliseconds.");
		// Average of 5 times was 20.8 milliseconds
		
		long alAvgGetStart = System.currentTimeMillis();
		
		for(int i = 0; i < initialAmount; i++) {
			int temp = arrList.get(i);
			// System.out.println(arrList.get(i));
		}
		
		long alAvgGetEnd = System.currentTimeMillis();
		
		long alEllapsedTimeGet = alAvgGetEnd - alAvgGetStart;
		
		System.out.println("Time taken to get at the middle " + initialAmount + " integers from the ArrayList: " + alEllapsedTimeGet + " milliseconds.");
		// Average: 7.4 milliseconds
		
		long llAvgGetStart = System.currentTimeMillis();
		
		for(int i = 0; i < initialAmount / 10; i++) {
			int temp = linkList.get(i);
			// System.out.println(i);
		}
		
		long llAvgGetEnd = System.currentTimeMillis();
		
		long llEllapsedTimeGet = llAvgGetEnd - llAvgGetStart;
		
		System.out.println("Time taken to get at the middle " + initialAmount / 10 + " integers from the LinkedList: " + llEllapsedTimeGet + " milliseconds.");
		
		System.out.println("*****************************");
		
		long arrListAvgRemoveStart = System.currentTimeMillis();
		
		for(int i = 0; i < initialAmount; i++) {
			arrList.remove(arrList.size() - 1);
		}
		
		long arrListAvgRemoveEnd = System.currentTimeMillis();
		
		long calc1 = arrListAvgRemoveEnd - arrListAvgRemoveStart;
		
		System.out.println("Avg remove at end array list: " + calc1);
		
		long t1 = System.currentTimeMillis();
		
		for(int i = 0; i < initialAmount; i++) {
			linkList.removeLast();
		}
		
		long t2 = System.currentTimeMillis();
		
		long t3 = t2 - t1;
		
		System.out.println("Avg remove at end link list: " + t3);
		
		arrList.clear();
		linkList.clear();
		
		long arrayListAddMiddleStartTime = System.currentTimeMillis();
		
		for(int i = 0; i < initialAmount; i++) {
			// arrList.add(arrList.size() / 2, i);
		}
		
		long arrayListAddMiddleEndTime = System.currentTimeMillis();
		
		long arrayListAddMiddleEllapsedTime = arrayListAddMiddleEndTime - arrayListAddMiddleStartTime;
		
		System.out.println("Time taken to add at the middle " + initialAmount + " integers to the ArrayList: " + arrayListAddMiddleEllapsedTime + " milliseconds.");
		// Average: 44.65 *Seconds* (44650 milliseconds) 
		
		long linkedListAddMiddleStartTime = System.currentTimeMillis();
		
		for(int i = 0; i < initialAmount; i++) {
			// linkList.add(arrList.size() / 2, i);
		}
		
		long linkedListAddMiddleEndTime = System.currentTimeMillis();
		
		long linkedListAddMiddleEllapsedTime = linkedListAddMiddleEndTime - linkedListAddMiddleStartTime;
		
		System.out.println("Time taken to add at the middle " + initialAmount + " integers to the LinkedList: " + linkedListAddMiddleEllapsedTime + " milliseconds.");
		// Average: 24.8 milliseconds 
		
	}
}
