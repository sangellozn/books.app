package info.san.books.app.rest.exceptionmapper;

import info.san.books.app.exception.ObjectNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class ObjectNotFoundExceptionMapper implements ExceptionMapper<ObjectNotFoundException> {

	@Override
	public Response toResponse(ObjectNotFoundException exception) {
		return Response.status(Status.NOT_FOUND).entity(new BasicExceptionMessage("NotFound", exception.getMessage())).build();
	}


}
