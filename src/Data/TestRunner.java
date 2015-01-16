package Data;

public class TestRunner {

	
	public static void main(String[] args) {
		
		TransitionStore transitionStore = new TransitionStore() ;
		
		Node node_A = new Node("0");
		Node node_B = new Node("B");
		Node node_C = new Node("C");
		
		Transition transition_A_B = new Transition(node_B, 12.0);

		Transition transition_B_A = new Transition(node_A, 21.0);
		
		
		TransitionList node_A_transitions = new TransitionList();
		node_A_transitions.add(transition_A_B) ;
		//node_A_transitions.add(transition_A_C) ;
		
		TransitionList node_B_transitions = new TransitionList();
		node_B_transitions.add(transition_B_A);
		
		if (transitionStore.containsKey(node_A)){
			transitionStore.get(node_A).add(transition_A_B);
		}else{
			transitionStore.put(node_A, node_A_transitions);
		}
		
		Transition transition_A_C = new Transition(node_C, 13.0);
		
		if (transitionStore.containsKey(node_A)){
			transitionStore.get(node_A).add(transition_A_C);
			
		}else{
			transitionStore.put(node_A, node_A_transitions);
		}

		transitionStore.put(node_B, node_B_transitions);

		System.out.println(transitionStore);
		
		
		//System.out.println(transitionStore.containsKey(node_A));
		
		//TransitionList test_A = transitionStore.get(node_A); 
		//System.out.print(test_A.toString());	

	}

}
