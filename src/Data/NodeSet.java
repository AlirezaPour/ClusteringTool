package Data;

import java.util.HashMap;
import java.util.Iterator;

public class NodeSet extends HashMap<Integer, Node> {

	int number_of_clusters;	
	
	public NodeSet(int numberOfNodes){
		// state index starts from 0
		for (int i=0 ; i< numberOfNodes ; i++){
			this.put(i, new Node (Integer.toString(i)));
			number_of_clusters ++ ; 
		}
	}
	
	@Override
	public String toString() {
	
		String outputString = "\n";
		Iterator<Integer> myIter = this.keySet().iterator();
		while (myIter.hasNext()){
			int index = myIter.next();
			outputString = outputString + Integer.toString(index) + " -> " + this.get(index) + " -> "+ this.get(index).getCluster() + "\n" ; 
		}
		return outputString ;
		
	}
	
	public void combineClusters(Node start, Node target, Cluster startCluster, Cluster targetCluster){
		
		startCluster.addAll(targetCluster);
		
		Node node ; 
		
		Iterator<Node> myIter = targetCluster.iterator();
		while(myIter.hasNext()){
			node = myIter.next(); 
			node.setCluster(startCluster);
		}
		
		// whenever two clusters are combined, one cluster is lost.
		number_of_clusters -- ; 
		
	}
	
	public int getNumberOfClusters (){
		return number_of_clusters ;  
	}
}
