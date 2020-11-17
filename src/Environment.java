import java.util.*;


/*Interpreter class*/
public class Environment implements EnvironmentInterface{

	private Stack<Map<String, Integer>> e;
	
	Environment (){
		e = new Stack<Map<String, Integer>>();
	}
	
	@Override
	public Environment beginScope() {
		Map<String, Integer> assocs = new HashMap<String, Integer>();
		e.add(assocs);
		return this;
	}

	@Override
	public Environment endScope() {
		e.pop();
		return this;
	}

	@Override
	public void assoc(String id, int val) throws IdentifierDeclaredTwice {
		if(e.get(0).containsKey(id))
			throw new IdentifierDeclaredTwice();
		e.get(0).put(id, val);
	}

	@Override
	public int find(String id) throws UndeclaredIdentifier {
		
		for(Map<String, Integer> m : e) {
			if(m.containsKey(id)) {
				return m.get(id);
			}
		}
		throw new UndeclaredIdentifier();
	}
 

	
}
