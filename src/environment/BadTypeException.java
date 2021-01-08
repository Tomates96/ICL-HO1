package environment;
@SuppressWarnings("serial")
public class BadTypeException extends Exception {
		
		private static String error = "Invalid type, expected numbers";
		
		public BadTypeException() {
			super(error);
		}
	}