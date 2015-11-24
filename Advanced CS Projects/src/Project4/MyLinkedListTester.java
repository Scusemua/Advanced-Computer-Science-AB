package Project4;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedListTester {
	
	// I did test all the methods, although I just deleted the code after testing
	// because it started to get incredibly confusing to figure out what was going
	// wrong when certain things didn't initially work 
	public static void main(String[] args) {
		MyLinkedList<Integer> intList =  new MyLinkedList<Integer>();
		
		intList.add(1);
		intList.add(2);
		
		ArrayList<Integer> arrListInt = new ArrayList<Integer>();
		
		arrListInt.add(13);
		arrListInt.add(14);
		arrListInt.add(15);
		arrListInt.add(16);
		
		intList.addAll(1, arrListInt);
		
		ArrayList<Integer> toRetain = new ArrayList<Integer>();
		toRetain.add(1);
		toRetain.add(2);
		toRetain.add(15);
		
		intList.retainAll(toRetain);
		
		intList.add(intList.size(), 7);
		
		intList.add(0, 44);
		
		ArrayList<Integer> intListSub = (ArrayList<Integer>) intList.subList(2, intList.size());
		
		System.out.println("intList: ");
		printAll(intList);
		System.out.println("*\n*\n*\n");
		System.out.println("intListSub: ");
		printAll(intListSub);
	}
	
	public static void printAll(List<?> list) {
		System.out.println("Size: " + list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}