package Data;

import java.util.ArrayList;

public class TestRunner2 {

	
	public static void main(String[] args) {
		
		TransitionStore transitionStore = new TransitionStore() ;
		
		Node node_A = new Node("A");
		Node node_B = new Node("B");
		Node node_C = new Node("C");
		
		ArrayList<Node> list1 = new ArrayList<Node>();
		list1.add(node_A);
		
		ArrayList<Node> list2 = new ArrayList<Node>();
		list2.add(node_B);
		list2.add(node_C);
		
		System.out.println(list1);
		
		
		list1.addAll(list2);
		
		System.out.println(list1);
		
		
	}

}
