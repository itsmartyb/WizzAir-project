
public class WrongRegistrationException extends Exception {

	
	private static final long serialVersionUID = 1472781495730124018L;
	
	public WrongRegistrationException() {}
	
	public WrongRegistrationException(String message) {
		super(message);
	}
	
	public WrongRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public WrongRegistrationException(Throwable cause) {
		super(cause);
	}




}
