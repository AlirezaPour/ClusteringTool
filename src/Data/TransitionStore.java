package Data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TransitionStore extends HashMap<Node,TransitionList> {
 
	public TransitionStore () {
		
	}
	
	public String toString () { 
		String output = ""; 
		Iterator<Node> myiter = this.keySet().iterator(); 
		while (myiter.hasNext()){
			Node key = myiter.next();
			output = output + key.toString() + " " + this.get(key).toString() + "\n";  
		}
		return output;
	}
	
	
	
	@Override
	public boolean containsKey(Object key) {
		
		Iterator<Node> keyIterator = this.keySet().iterator();
		
		while (keyIterator.hasNext()) {
			Node currentKey = keyIterator.next(); 
			if (currentKey.equals(   (Node) key)   ){
				return true;
			}
		}
		return false; 		
	}
		
}
