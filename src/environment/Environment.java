package environment;
import java.util.*;


/*Interpreter class*/
public class Environment<X> {

	private Stack<Map<String, X>> e;
	
	
	public Environment (){
		e = new Stack<Map<String, X>>();
	}
	
	public Environment<X> beginScope() {
		Map<String, X> assocs = new HashMap<String, X>();
		e.add(assocs);
		return this;
	}

	public Environment<X> endScope() {
		e.pop();
		return null;
	}

	public void assoc(String id, X val) throws IdentifierDeclaredTwice {
		for(Map<String, X> m : e) {
			if(m.containsKey(id)) {
				throw new IdentifierDeclaredTwice();
			}
		}
		Map<String, X> assocs = new HashMap<String, X>();
		assocs.put(id, val);
		e.add(assocs);
	}

	public X find(String id) throws UndeclaredIdentifier {
		Environment<X> current = this;
		while(current != null) {
			for(Map<String, X> m : current.e) {
				if(m.containsKey(id)) {
					return m.get(id);
				}
			}
		}
		throw new UndeclaredIdentifier();
	}
 
	public int depth() {
		return e.size();
	}
	
}
