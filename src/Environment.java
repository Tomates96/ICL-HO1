/*Interpreter class*/
public class Environment {
 
	/*push level*/
	Environment beginScope();
	/*pop top level*/
	Environment endScope();
	
	void assoc(String id, int val);
	int find (String id);
}
