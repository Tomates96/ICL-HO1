
@SuppressWarnings("serial")
public class IDNotFound extends Exception{

	private static String error = "ID not found";
	
	public IDNotFound() {
		super(error);
	}
}
