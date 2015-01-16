package Data;

import java.util.ArrayList;
import java.util.Iterator;

public class Cluster extends ArrayList<Node>{

	public Cluster(){
		
	}
	
	
	// starting a cluster with a single node. 
	public Cluster(Node node){
		this.add(node);
	}
	
	// returns true if the node belongs to this cluster
	public boolean isClusterOf (Node node){
		return this.contains(node);
	}
	
	// combines this cluster with another cluster.
	public boolean combineWith (Cluster cluster){
		return this.addAll(cluster);
	}
	
	public String toString () {
		String output = "(";
		Iterator<Node> myIter = this.iterator();
		while(myIter.hasNext()){
			output = output + myIter.next().toString() ;
			if (myIter.hasNext()) output = output + " , ";
		}
		output = output + ")" ; 
		return output;
	}
	
}
