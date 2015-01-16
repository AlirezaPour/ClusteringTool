import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.crypto.Data;
import javax.xml.transform.Transformer;

import org.github.jamm.MemoryMeter;

import Data.Cluster;
import Data.ClustersCollection;
import Data.Node;
import Data.NodeSet;
import Data.Transition;
import Data.TransitionList;
import Data.TransitionStore;

public class Application {

	static double clusteringThreshold ; 
	
	static TransitionStore ts ;
	static NodeSet nodeSet; 
	static ClustersCollection cc;
	
	static double number_of_states ; 
	static double number_of_transitions ;
	
		
	public static void main(String[] args) throws IOException {
		
		clusteringThreshold = Double.parseDouble(args[0]);
		
		Date date = new Date();
		
		String fileName = "prismOutput";
		
		System.out.println("Analysing the transitions.\n");
		// generates the transition store using the input file which is derived from the PRISM tool.
		
		long loadingStartTime = System.currentTimeMillis();
		
		// will construct a transitionStore from the given file.
		generateTransitionStoreFromFile(fileName) ;
		
		System.out.println("TransitionStore of size " + ts.size() + " is constructed.\n");
		//System.out.println(ts);

		long loadingFinishTime = System.currentTimeMillis();
						
		//System.out.println(ts);
		
		long clusteringStartTime = System.currentTimeMillis();
		
		// the clusters are implicitly presented in the nodeSet.
		// this function will use the transitions and the initial clusters to find the clusters in their final shape.
		
		processTransitionsAndClusters(); 

		System.out.println("\nNumber of Clusters: " + nodeSet.getNumberOfClusters());
		
		long clusteringFinishTime = System.currentTimeMillis();		
		
		long loadingTime = loadingFinishTime - loadingStartTime ; 
		long clusteringTime = clusteringFinishTime - clusteringStartTime ;
		long totalTime = loadingTime + clusteringTime; 
		
		System.out.println("\nloading time: " + loadingTime);
		System.out.println("clustering time: " + clusteringTime);
		System.out.println("total time: " + totalTime);
		
		MemoryMeter meter = new MemoryMeter();
	    long size_of_ts  = meter.measureDeep(ts);
	    long size_of_nodeSet  = meter.measureDeep(nodeSet);
	    
	   // long deep_size = meter.measureDeep(myMap);
	   // long children = meter.countChildren(myMap);
	    
	    System.out.println("\nspace for the transitionStore " + size_of_ts );
	    System.out.println("space for the calculation of the clusters " + size_of_nodeSet );
	    
	}

	
	public static void processTransitionsAndClusters(){
		
		double transition_counter = 0.0 ;
		
		System.out.println("Clustering Process Started.\n");
		
		Iterator<Node> startNodeIterator = ts.keySet().iterator();
		
		while (startNodeIterator.hasNext()){
			
			Node startingNode = startNodeIterator.next(); 
			
			Iterator<Transition> transitionIterator = ts.get(startingNode).iterator(); 
			
			
			while (transitionIterator.hasNext()){
				
				Transition transition = transitionIterator.next(); 
				
				// user the startingNode + transition to update the clustersCollection
				updateClustersCollection (startingNode,transition ); 
				
				transition_counter ++ ; 
				
			}
			
			System.out.printf("%.2f , %.2f\n", transition_counter,(transition_counter)/(number_of_transitions)*100);
			
			
		}	// end of - while (startNodeIterator.hasNext()){ 
		
	}
	
	// given a transition, this function updates the clusters by considering the transition's rate.
	public static void updateClustersCollection (Node startingNode, Transition newTransition){
		
		Node targetNode = newTransition.getTarget();
		double rate = newTransition.getRate(); 
		if (rate < clusteringThreshold){
			// do nothing. discard the transition
			return ; 
		} else{	
			// the transition has a high rate. 
			
			Cluster startingCluster = startingNode.getCluster(); 
			Cluster targetCluster = targetNode.getCluster();
			
			// if they are equal, do nothing.
			// if they are not equal, combine them. 
			
			if (! startingCluster.equals(targetCluster)){	 
				
				nodeSet.combineClusters(startingNode,targetNode,startingCluster,targetCluster);
								
				//System.out.println("Cluster "+ startingCluster + " and " + targetCluster + " combined.");
			}
								
		}
	}
	
	
	
	
	public static void generateTransitionStoreFromFile (String fileName) throws IOException {
		
		File prismOutput = new File(fileName); 
	
		int transitionCounter = 0 ; 
		
		ts = new TransitionStore() ; 
		
		BufferedReader br = null ;
		String line;
		
		Node node ; 
		Node target ;
		
		double rate ; 
		
				try {
			br = new BufferedReader(new FileReader(prismOutput));
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
						
			node_index = Integer.parseInt(st.nextToken()) ; 
			node = nodeSet.get(node_index) ;
			
			target = nodeSet.get(Integer.parseInt(st.nextToken())) ; 
			
			rate = Double.parseDouble(st.nextToken());
 
			Transition transition = new Transition(target, rate);
			
			addToTransitionStore (node,transition) ;
			
			transitionCounter ++ ;			 
			
			System.out.printf("%d , %.2f \n",transitionCounter,(transitionCounter)/(number_of_transitions) * 100);
			
		}
		
		br.close();		

	}
	
	
	public static void addToTransitionStore(Node start, Transition transition){
		if (ts.containsKey(start)) {	 
			ts.get(start).add(transition);						
		}
		else{				// it is the first the node is being visited.
			TransitionList newTransitionList = new TransitionList(); 
			newTransitionList.add(transition);
			ts.put(start, newTransitionList);
			
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
	}
	
	
	/*public static ClustersCollection initialiseClustersCollection(){
		
		ClustersCollection cc = new ClustersCollection();
		
		Iterator<Node> keyIterator = ts.keySet().iterator();
		
		while (keyIterator.hasNext()){
			Node node = keyIterator.next(); 
			Cluster newCluster = new Cluster(node);
			cc.add(newCluster);
		}
		
		return cc ; 
		
	}*/
	

}
