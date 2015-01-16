import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import Data.Node;
import Data.NodeSet;
import Data.Transition;
import Data.TransitionList;
import Data.TransitionStore;


public class DataTransformer {
	
	
	
	/*public DataTransformer () {
			
	}
	
	
	// constructs the transitionset as well as the nodeSet.
	public static TransitionStore generateTransitionListFromFile (File file) throws IOException {
		
		System.out.println(file.getAbsolutePath());
		
		int transitionCounter = 0 ; 
		
		transitionStore = new TransitionStore() ; 
		
		BufferedReader br = null ;
		String line;
		
		Node node ; 
		Node target ; 
		double rate ; 
		
				try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found") ; 
			e.printStackTrace();
		}
		
		String firstLine =  br.readLine();  
		StringTokenizer st = new StringTokenizer(firstLine);
		
		number_of_states = Double.parseDouble(st.nextToken());
		number_of_transitions = Double.parseDouble(st.nextToken());
		
		System.out.println("number of states: " + number_of_states + "\nnumber of transitions: " + number_of_transitions+"\n");
		
		nodeSet = new NodeSet((int)number_of_states);
		
//		System.out.println(nodeSet);
		
		int node_index ; 
		
		while ((line = br.readLine()) != null) {
		
			st = new StringTokenizer(line);
						
			node = nodeSet.get(Integer.parseInt(st.nextToken())) ;
			
			target = nodeSet.get(Integer.parseInt(st.nextToken())) ; 
			
			rate = Double.parseDouble(st.nextToken());
 
			Transition transition = new Transition(target, rate);
			
			addToTransitionStore (node,transition) ;
			
			transitionCounter ++ ;
			
			System.out.printf("%d , %.2f \n",transitionCounter,(transitionCounter)/(number_of_transitions) * 100);
			 
			
		}
		
		br.close();		
				
		return transitionStore; 
	
	}
	
	
	public static void addToTransitionStore(Node start, Transition transition){
		if (transitionStore.containsKey(start)) {	 
			transitionStore.get(start).add(transition);						
		}
		else{				// it is the first the node is being visited.
			TransitionList newTransitionList = new TransitionList(); 
			newTransitionList.add(transition);
			transitionStore.put(start, newTransitionList);
			
		}
	} 
		
	public static Node getNode (String line){
		return null ; 
	}
	
	public static Node getTarget (String line){
		return null ; 
	}
	
	public static double getRate (String line){
		return 1.0 ; 
	}
	
	public double getNumberOfStates (){
		return number_of_states ; 
	}
	
	public double getNumberOfTransitions(){
		return number_of_transitions ; 
	}*/
	
}
