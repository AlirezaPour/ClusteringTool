package Data;

public class Node {

	String name ;
	Cluster cluster; 
	
	public Node (){
		
	}
			
	// when a node is initialised, it falls in its own cluster. 
	public Node(String name){
		this.name = name; 
		this.cluster = new Cluster();
		cluster.add(this);
	}
	
	public void setCluster (Cluster cluster){
		this.cluster = cluster; 
	}
	
	public Cluster getCluster (){
		return this.cluster; 
	}
	
	
	public void setName (String name ) {
		this.name = name; 		
	}
	
	public String toString () {
		return "Node_" + name ; 
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (   ( (Node) obj ).name.equals(this.name)  ) {
			return true;
		} else{
			return false; 
		}
	}
	
	@Override
	public int hashCode() {
		return name.hashCode() ;
	}
}
