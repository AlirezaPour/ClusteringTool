package Data;

import java.util.ArrayList;
import java.util.Iterator;

public class ClustersCollection extends ArrayList<Cluster>{

	public ClustersCollection(){
		
	}
	
	// return the cluster to which the node belongs. 
	public Cluster findCluster (Node node){
		Cluster currentCluster = null ;
		
		Iterator<Cluster> clustersIterator = this.iterator();
		
		while (clustersIterator.hasNext()){
			currentCluster = clustersIterator.next(); 
			if (currentCluster.contains(node)){
				return currentCluster; 
			}		
		}
		
		return null ; 
	}
	
	public void combineClusters (Cluster cluster1, Cluster cluster2){
		cluster1.combineWith(cluster2);
		this.remove(cluster2);		
	}
	
}
