import java.util.*;


/*Interpreter class*/
public class Environment implements EnvironmentInterface{

	private Stack e;
	
	
	
	Environment (){
		e = new Stack();
	}
	
	@Override
	public Environment beginScope() {
		e = new Stack();
		return null;
	}

	@Override
	public Environment endScope() {
		return null;
	}

	@Override
	public void assoc(String id, int val) {


		
	}

	@Override
	public int find(String id) {
		
		int value = e.search(id);
		
		return value;
	}
 

	
}
