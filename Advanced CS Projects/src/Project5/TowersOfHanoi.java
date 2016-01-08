package Project5;

import java.util.Stack;
import java.util.Scanner;

public class TowersOfHanoi {
	
	// Peg 0 is not used. Only pegs 1, 2, 3 are used.
	public static final int NUM_PEGS = 3;
	
	public static MyStack[] stacks = new MyStack[NUM_PEGS + 1];
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the number of discs you'd like");
		int numDiscs = kb.nextInt();
		
		for(int i = 1; i <= NUM_PEGS; i++) {
			stacks[i] = new MyStack();
		}
		
		for(int i = numDiscs; i > 0; i--) {
			stacks[1].push(i);
		}
		
		System.out.println("Start...");
		printStacks();
		moveDiscs(numDiscs, 1, 3, 2);
		System.out.println("");
		System.out.println("");
		System.out.println("End...");
		printStacks();
		
	}
	
	public static void moveDiscs(int n, int start, int end, int temp) {
		
		if (n == 1) {
			move1(start, end);
			printStacks();
		} else {
			moveDiscs(n - 1, start, temp, end);
			printStacks();
			move1(start, end);
			printStacks();
			moveDiscs(n - 1, temp, end, start);
			printStacks();
		}
		
	}
	
	public static void move1(int start, int end) {
		stacks[end].push(stacks[start].pop());
	}
	
	public static void printStacks() {
		System.out.println("");
		System.out.println("");
		System.out.print("Stack 1: "); 
		MyStack s1 = stacks[1];
		MyStack s2 = stacks[2];
		MyStack s3 = stacks[3];
		
		MyStack tempStack = new MyStack();
		
		int size1 = s1.size();
		for(int i = 0; i < size1; i++) {
			tempStack.push(s1.pop());
		}
		
		int tempSize1 = tempStack.size();
		for(int i = 0; i < tempSize1; i++) {
			int x = (int) tempStack.pop();
			System.out.print(x);
			s1.push(x);
		}
		
		System.out.println(""); 
		System.out.print("Stack 2: "); 
		
		int size2 = s2.size();
		for(int i = 0; i < size2; i++) {
			tempStack.push(s2.pop());
		}
		
		int tempSize2 = tempStack.size();
		for(int i = 0; i < tempSize2; i++) {
			int x = (int) tempStack.pop();
			System.out.print(x);
			s2.push(x);
		}
		
		System.out.println("");
		System.out.print("Stack 3: "); 
		
		int size3 = s3.size();
		for(int i = 0; i < size3; i++) {
			tempStack.push(s3.pop());
		}
		
		int tempSize3 = tempStack.size();
		for(int i = 0; i < tempSize3; i++) {
			int x = (int) tempStack.pop();
			System.out.print(x);
			s3.push(x);
		}
		
		System.out.println("");
		System.out.println("");
	}
}
