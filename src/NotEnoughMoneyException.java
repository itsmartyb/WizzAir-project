
public class NotEnoughMoneyException extends Exception {
	private static final long serialVersionUID = 6850457811914268673L;

	public NotEnoughMoneyException() {
		super();

	}

	public NotEnoughMoneyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotEnoughMoneyException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public NotEnoughMoneyException(String message) {
		super(message);
	}

	public NotEnoughMoneyException(Throwable cause) {
		super(cause);
	}
	

}
