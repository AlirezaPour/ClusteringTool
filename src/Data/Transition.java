package Data;

public class Transition {

	Node target; 
	double rate ; 
	
	public Transition () {
		
	}
	
	public Transition (Node target, double d){
		this.target = target ; 
		this.rate = d; 		
	}
		
	public void setTarget (Node target){
		this.target = target ; 
	}
	
	public void setRate (double rate){
		this.rate = rate;
	}
	
	public Node getTarget (){
		return target; 
	}
	
	public double getRate (){
		return rate; 
	}
	
	public String toString(){
		return ( "("+target.toString() + ", " + rate+")") ;  
	}
	
}
