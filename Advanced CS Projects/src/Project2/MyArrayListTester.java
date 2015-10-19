package Project2;

import java.util.ArrayList;

public class MyArrayListTester {

	private static MyArrayList<String> listOfSwag = new MyArrayList<String>();
	private static int printNumber = 0;
	
	public static void main(String[] args) {

		testStrings(); // ADDS THE STRINGS TO THE MYARRAYLIST
		printAll(); // PRINT SOME SHIT
		listOfSwag.remove(1); // REMOVE (INDEX) WORKS
		sop(); // EASIER THAN TYPING "System.out.println(""); every time
		printAll(); // PRINT MORE SHIT
		listOfSwag.remove(listOfSwag.size() - 1); // REMOVE (INDEX) WORKS AGAIN
		sop(); // EASIER THAN TYPING "System.out.println(""); every time
		printAll(); // PRINT EVEN MORE SHIT
		String toAdd = " Computer Science Rocks! ";
		listOfSwag.add(toAdd); // ADD(OBJECT) WORKS
		listOfSwag.add(0, toAdd); // ADD(INDEX, OBJECT) WORKS
		sop();
		printAll(); // PRINT SOME EVEN MORE SHIT
		sop(); // Move print line 
		//printAll();
		//printAllForLoop(); // Use a for-loop because the enhanced loop is messing up
		printAll(); // But I fixed it so this works again
		//listOfSwag.addAll(2, toAdd2); // ADD THE COLLECTION AT THE CORRECT INDEX
		sop(); // NEXT LINE BRO
		printAll(); // ADDALL(COLLECTION) AND ADDALL(INT, COLLECTION) BOTH WORK
		listOfSwag.clear(); // CLEAR THIS BITCH
		sop(); // NEXT LINE BRO
		System.out.println("Printing the supposedly clear list..."); // NECESSARY ALERT
		printAll(); // SHOULDN'T PRINT ANYTHING
		System.out.println("CLEAR() WORKS WOOHOO"); // IT DOESN'T
		testStrings(); // ADD MORE STRINGS
		String doesItContainThis = "are "; // STRING TO CHECK
		System.out.println(listOfSwag.contains(doesItContainThis)); // CONTAINS(OBJECT) WORKS
		ArrayList<String> toCheckIfContains = new ArrayList<String>(); // TO BE ADDED TO THE ARRAY
		toCheckIfContains.add("TOPKEK 1"); // ADD A STRING TO THE COLLECTION TO ADD
		toCheckIfContains.add("TOPKEK 2"); // ADD A STRING TO THE COLLECTION TO ADD
		toCheckIfContains.add("TOPKEK 3"); // ADD A STRING TO THE COLLECTION TO ADD
		listOfSwag.addAll(toCheckIfContains); // ADD THAT WHOLE COLLECTION BRUH
		massAddStrings("kek", 4); // ADD LOTS OF KEK
		listOfSwag.add(2, "kek"); // ADD A KEK AT A SPECIFIC SPOT
		printAll(); // PRINT EVERYTHING, ENSURE ALL IS IN RIGHT SPOTS (DOESN'T REALLY MATTER)
		sop(); // NEXT LINE
		System.out.println(listOfSwag.containsAll(toCheckIfContains)); // DOES IT WORK?
		// YES IT DOES WOOHOO
		sop(); // NEXT LINE 
		// I DON'T NEED TO TEST ENSURE CAPACITY 
		System.out.println(listOfSwag.get(3)); // THIS WORKS WOOHOO
		sop(); // NEXT LINE
		String are = "are "; // CREATE A STRING CALLED ARE THAT IS "ARE" 
		int index = listOfSwag.indexOf(are); // WHERE IS IT
		System.out.println(index); // THERE IT IS (IT WORKS)
		System.out.println(listOfSwag.isEmpty());  // IS IT EMPTY?
		listOfSwag.clear(); // NOW IT IS
		System.out.println(listOfSwag.isEmpty()); // ISEMPTY() WORKS
		testStrings(); // ADD STRINGS TO EMPTY ARRAY
		listOfSwag.add(are); // ADD THIS AT THE END
		System.out.println(listOfSwag.lastIndexOf(are)); // LASTINDEXOF() WORKS TOO
		listOfSwag.remove(5); // THIS WORKS WOOHOO
		listOfSwag.remove(are); // THIS WORKS WOOHOO
		printAll(); // THIS WORKS WOOHOO
		listOfSwag.addAll(toCheckIfContains); // Adding it just to remove it...
		testStrings(); // ADD MORE STRINGS
		listOfSwag.add(3, "kekekekek"); // ADD 3 SUPER KEKS
		sop(); // NEXT LINE
		printAll(); // PRINT THE WHOLE THING BEFORE WE REMOVE ALL THE TOPKEKS
		sop(); // NEXT LINE
		listOfSwag.removeAll(toCheckIfContains); // THIS BITCH WORKS TOO
		printAll(); // PRINT EVERYTHING NOW THAT THE TOPKEKS ARE GONE
		listOfSwag.addAll(toCheckIfContains);
		sop(); // NEXT LINE
		printAll(); // PRINT IT
		listOfSwag.retainAll(toCheckIfContains); // THIS WORKS
		sop(); // NEXT LINE
		listOfSwag.clear(); // CLEAR IT
		testStrings(); // ADD MORE STRINGS
		listOfSwag.set(1, "KEK"); // CHANGE IT TO KEK (IT WORKS)
		sop(); // NEXT LINE
		printAll(); // PRINT ALL
		System.out.println(listOfSwag.size()); // THIS WORKS
		testStrings(); // ADD SOME DANK STRINGS
		testStrings(); // ADD EVEN MORE DANK STRINGS
		MyArrayList<String> toppestKek = (MyArrayList<String>) listOfSwag.subList(2, 5);
		for(int i = 0; i < toppestKek.size(); i++) {
			System.out.println(toppestKek.get(i));
		} 
		// APPARENTLY SUBLIST WORKS?!
		Object[] kekekekekek = listOfSwag.toArray();
		System.out.println("Array time");
		for(int i = 0; i < kekekekekek.length; i++) {
			System.out.println(kekekekekek[i]);
		}
		// TO ARRAY() WORKS
		listOfSwag.clear();
		// New list of Integers
		MyArrayList<Integer> listOfInts = new MyArrayList<Integer>();
		// Add some Integers to the listOfInts ArrayList
		listOfInts.add(1);
		listOfInts.add(2);
		listOfInts.add(3);
		listOfInts.add(4);
		listOfInts.add(5);
		listOfInts.add(6);
		listOfInts.add(7);
		Integer[] topkek = new Integer[listOfInts.size()];
		listOfInts.toArray(topkek);
		sop();
		for(Object o: topkek) {
			System.out.println(o);
		} // toArray(T[] a) WORKS
		System.out.println(listOfInts.toString());
		System.out.println(listOfSwag.toString());
		
		listOfSwag.clear();
		sop();
		System.out.println(listOfSwag.size());
		ArrayList<Integer> arrayListInts = new ArrayList<Integer>();
		listOfInts.clear();
		listOfInts.addAll(arrayListInts);
		for(int i = 0; i < arrayListInts.size(); i++) {
			System.out.println(arrayListInts.get(i));
		}
		
		
		
	}

	// PRINT SHIT
	private static void printAll() {
		for (Object kek : listOfSwag) {
			System.out.print(printNumber + " " + kek.toString() + " ");
			printNumber++;
		}
	}
	
	@SuppressWarnings("unused")
	private static void printAllForLoop() {
		for(int i = 0; i < listOfSwag.size(); i++) {
			System.out.print(printNumber + " " + listOfSwag.get(i) + " ");
			printNumber++;
		}
	}

	// ADDS SOME STRINGS TO THE LIST THAT IS AN ARRAYLIST OF MINE
	private static void testStrings() {
		listOfSwag.add("Hello, ");
		listOfSwag.add("how ");
		listOfSwag.add("are ");
		listOfSwag.add("you ");
		listOfSwag.add("doing?");
	}
 
	// EASIER THAN TYPING "System.out.println(""); every time
	private static void sop() {
		System.out.println("");
	}
	
	private static void sop(String s) {
		System.out.println(s);
	}
	
	//@SuppressWarnings("unused")
	private static void massAddStrings(String toAdd, int amount) {
		for(int i = 0; i < amount; i++) {
			listOfSwag.add(toAdd);
		}
	}
}
