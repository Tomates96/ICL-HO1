package environment;
@SuppressWarnings("serial")
public class IdentifierDeclaredTwice extends Exception{

	private static String error = "Identifier already declared";
	
	public IdentifierDeclaredTwice() {
		super(error);
	}
}
