package info.san.books.app.rest.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.axonframework.repository.AggregateNotFoundException;

/**
 * Exception for an aggregate that is not available.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
@Provider
public class AggregateNotFoundExceptionMapper implements
		ExceptionMapper<AggregateNotFoundException> {

	@Override
	public Response toResponse(AggregateNotFoundException exception) {
		return Response.status(Status.NOT_FOUND).entity(new BasicExceptionMessage("NotFound", "The requested object cannot be found.")).build();
	}

}