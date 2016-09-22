package info.san.books.app.exception;

public class AuteurIsNotAssignedToLivreException extends RuntimeException {

	private static final long serialVersionUID = 515737586702249006L;

	public AuteurIsNotAssignedToLivreException() {
		// Nothing.
	}

	public AuteurIsNotAssignedToLivreException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuteurIsNotAssignedToLivreException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuteurIsNotAssignedToLivreException(String message) {
		super(message);
	}

	public AuteurIsNotAssignedToLivreException(Throwable cause) {
		super(cause);
	}

}
