@SuppressWarnings("serial")
public class UndeclaredIdentifier extends Exception {
	
	private static String error = "Undeclared Identifier";
	
	public UndeclaredIdentifier() {
		super(error);
	}
}
