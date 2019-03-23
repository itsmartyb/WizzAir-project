
public class WrongEmailException extends Exception {

	private static final long serialVersionUID = 3911921403839933962L;
	
	public WrongEmailException() {}
	
	public WrongEmailException(String message) {
		super(message);
	}
	
	public WrongEmailException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public WrongEmailException(Throwable cause) {
		super(cause);
	}

}
