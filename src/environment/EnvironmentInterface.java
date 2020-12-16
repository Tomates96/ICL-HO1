package environment;

public interface EnvironmentInterface {
	/*Interpreter class*/
	 
		/*push level*/
		Environment beginScope();
		/*pop top level*/
		Environment endScope();
		
		void assoc(String id, int val) throws IdentifierDeclaredTwice;
		
		int find (String id) throws UndeclaredIdentifier;
		
}
