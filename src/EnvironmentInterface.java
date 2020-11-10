
public interface EnvironmentInterface {
	/*Interpreter class*/
	 
		/*push level*/
		Environment beginScope();
		/*pop top level*/
		Environment endScope();
		
		void assoc(String id, int val);
		
		int find (String id);
		
}
