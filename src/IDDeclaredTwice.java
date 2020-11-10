
@SuppressWarnings("serial")
public class IDDeclaredTwice extends Exception{

	private static String error = "ID delared twice";
	
	public IDDeclaredTwice() {
		super(error);
	}
}
