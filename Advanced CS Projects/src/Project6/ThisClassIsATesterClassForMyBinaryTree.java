package Project6;

import java.util.ArrayList;

public class ThisClassIsATesterClassForMyBinaryTree {
	public static void main(String[] args) {
		MyBinaryTree<Integer> oakTree = new MyBinaryTree<Integer>();
		oakTree.populateInts();
		
		System.out.println("PreOrder: ");
		ArrayList<Integer> preOrderAL = new ArrayList<Integer>();
		oakTree.preOrder(oakTree.getRoot(), preOrderAL);
		System.out.print(preOrderAL.toString());
		
		System.out.println();
		System.out.println("InOrder: ");
		ArrayList<Integer> inOrderAL = new ArrayList<Integer>();
		oakTree.inOrder(oakTree.getRoot(), inOrderAL);
		System.out.print(inOrderAL.toString());
		
		System.out.println();
		System.out.println("PostOrder: ");
		ArrayList<Integer> postOrderAL = new ArrayList<Integer>();
		oakTree.postOrder(oakTree.getRoot(), postOrderAL);
		System.out.print(postOrderAL.toString());
	}
}
