/**
 *
 */
package info.san.books.app.exception;

/**
 * If the name of an object must be unique and is already taken.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class NameAlreadyTakenException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -3248351965185523860L;

	/**
	 *
	 */
	public NameAlreadyTakenException() {
		super();
	}

	/**
	 * @param message
	 */
	public NameAlreadyTakenException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NameAlreadyTakenException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NameAlreadyTakenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NameAlreadyTakenException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
