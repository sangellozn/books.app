package info.san.books.app.rest.exceptionmapper;

/**
 * Small container for JSON result when an exception happen.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class BasicExceptionMessage {

	private String error;

	private String message;

	public BasicExceptionMessage(String error, String message) {
		this.error   = error;
		this.message = message;
	}

	public String getError() {
		return this.error;
	}

	public String getMessage() {
		return this.message;
	}
}
