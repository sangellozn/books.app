/**
 *
 */
package info.san.books.app.rest.exceptionmapper;

import info.san.books.app.exception.NameAlreadyTakenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
@Provider
public class NameAlreadyTakenExceptionMapper implements
		ExceptionMapper<NameAlreadyTakenException> {

	/**
	 *
	 */
	public NameAlreadyTakenExceptionMapper() {
		// Nothing.
	}

	@Override
	public Response toResponse(NameAlreadyTakenException exception) {
		return Response.status(Status.CONFLICT).entity(new BasicExceptionMessage("NameAlreadyTakenException", exception.getMessage())).build();
	}

}
