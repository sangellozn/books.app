/**
 *
 */
package info.san.books.app.exception;

/**
 * Exception used for signaling error when validating input DTO from REST services.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = -5868484202667386454L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
