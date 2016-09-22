package info.san.books.app.rest.exceptionmapper;

import info.san.books.app.exception.AuteurIsNotAssignedToLivreException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class AuteurIsNotAssignedToLivreExceptionMapper implements ExceptionMapper<AuteurIsNotAssignedToLivreException> {

	@Override
	public Response toResponse(AuteurIsNotAssignedToLivreException exception) {
		return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("BadRequest", exception.getMessage())).build();
	}


}
