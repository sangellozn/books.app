package info.san.books.app.rest.exceptionmapper;

import info.san.books.app.exception.AuteurIsAlreadyAssignedToLivreException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class AuteurIsAlreadyAssignedToLivreExceptionMapper implements ExceptionMapper<AuteurIsAlreadyAssignedToLivreException> {

	@Override
	public Response toResponse(AuteurIsAlreadyAssignedToLivreException exception) {
		return Response.status(Status.CONFLICT).entity(new BasicExceptionMessage("Conflict", exception.getMessage())).build();
	}


}
