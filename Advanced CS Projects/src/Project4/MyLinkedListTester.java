package Project4;

import java.util.ArrayList;

public class MyLinkedListTester {
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
		
		printAll(intList);
	}
	
	public static void printAll(MyLinkedList<?> list) {
		System.out.println("Size: " + list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}