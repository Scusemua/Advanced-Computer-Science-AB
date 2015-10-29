package Project0;

import java.util.Random;

public class Test {

	// Main Method
	public static void main(String[] args) {
		
		// Random Number Generator ("R-N-Jesus")
		Random RNGesus = new Random();
		
		// Declare variables
		int largest = 0, secondLargest = 0, smallest = 0, sum = 0;

		// Generate the very first random number
		int rand1 = RNGesus.nextInt ((27 - 7) + 1) + 7;

		// Print said number
		System.out.print(rand1 + " ");

		// Since it is the only number generated,
		// it is technically the largest number
		largest = rand1; 

		// Since it is the only number generated,
		// it is also technically the smallest number
		smallest = rand1;

		// Go thru the loop 16 times, NOT 17, because we already generated a number. 
		for(int i = 0; i < 15; i++) {
			
			// Generate a random number each time you go thru the loop
			int rand = RNGesus.nextInt ((27 - 7) + 1) + 7;
			
			// Figure out if the number generated is the smallest so far
			if(rand < smallest) {
				// If it is, set smallest to be the newly generated number
				smallest = rand;
			}
			
			// If the new number is LARGER than the largest so far...
			if(rand > largest) {
				// The second largest number is the old largest number
				// The new largest number is the randomly generated number
			    secondLargest = largest;
				largest = rand;
			// Let's find out of the randomly generated number is the second largest
			// First, check to see if the randomly generated number is bigger than the second number!
			// Then, ensure the randomly generated number isn't just the largest number regenerated.
			} else if (rand > secondLargest && rand != largest) {
				secondLargest = rand;
			}
			
			// Increment the sum variable
			sum += rand;

			// Print the number
			System.out.print(rand + " ");
		}

		// Compute the average using the sum and the number of numbers you generated
		double average = (double) sum / 17;

		// Print out info
		System.out.println(""); // Get everything to the new line
		System.out.println("Largest: " + largest);
		System.out.println("Second Largest: " + secondLargest);
		System.out.println("Smallest: " + smallest);
		System.out.println("Sum: " + sum);
		System.out.println("Average: " + average);
	}
	
}
